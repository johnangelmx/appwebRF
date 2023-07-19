package com.royaltyFlights.appwebRF.service;


import com.royaltyFlights.appwebRF.model.Pasajero;
import com.royaltyFlights.appwebRF.repository.PasajeroRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreacionCitaService {
    private final PasajeroRepository pasajeroRepository;


    @Value("${stripe.secretKey}") // Se debe configurar en el archivo application.properties
    private String stripeSecretKey;

    @Autowired
    public CreacionCitaService(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    // CREACION DE COMPRA
    public Map<String, String> urlPago(List<Pasajero> pasajeros) {

        try {
            // Configurar la clave secreta de Stripe
            Stripe.apiKey = stripeSecretKey;
            // Crear un objeto SessionCreateParams.Builder con los detalles de la compra
            SessionCreateParams.Builder builder = new SessionCreateParams.Builder();
            builder.setSuccessUrl("http://localhost:8080/suceso.html?")
                    .setCancelUrl("http://localhost:8080/citas.html")
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT);

            // Agregar los line-items al builder
            for (Pasajero pasajero : pasajeros) {
                SessionCreateParams.LineItem.PriceData.ProductData productData =
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(pasajero.getPaqueteId().getNombre_paquete())
                                .setDescription("Pasajero: " + pasajero.getNombres() + " " + pasajero.getApellidos() + " "
                                        + pasajero.getFecha() + " " + pasajero.getHora())
                                .build();

                SessionCreateParams.LineItem.PriceData priceData =
                        SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("GTQ")
                                .setUnitAmount((long) (pasajero.getTotal() * 100))
                                .setProductData(productData)
                                .build();

                SessionCreateParams.LineItem lineItem =
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(priceData)
                                .build();

                builder.addLineItem(lineItem);
            }

            // Crear la sesión de pago en Stripe
            SessionCreateParams createParams = builder.build();
            Session session = Session.create(createParams);
            // Obtener la URL de pago de la sesión
            String urlPago = session.getUrl();
            String sessionStripeId = session.getId();
            // Crear un mapa con sessionPedidosId y urlPago
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("sessionPedidosId", sessionStripeId);
            jsonResponse.put("urlPago", urlPago);


            if (!jsonResponse.get("urlPago").isEmpty()) {
                pasajeroRepository.saveAll(pasajeros);
                System.out.println("Pasajeros ingresados a la db");
            }

            // Devolver el mapa como respuesta
            return jsonResponse;
        } catch (
                StripeException e) {
            e.printStackTrace();
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("Error", e.getMessage());
            return jsonResponse;
        }
    }

    public Boolean verificarCompra(String sesionId) {
        try {
            Stripe.apiKey = stripeSecretKey;

            // Obtener la sesión de pago de Stripe
            Session session = Session.retrieve(sesionId);

            // Obtener el ID del PaymentIntent desde la sesión de pago
            String paymentIntentId = session.getPaymentIntent();

            // Obtener los detalles del PaymentIntent
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

            // Verificar el estado del pago
            return "succeeded".equals(paymentIntent.getStatus());
        } catch (StripeException e) {
            // Manejar la excepción de Stripe
            return ResponseEntity.status(500).body("Error al verificar el pago: " + e.getMessage()).hasBody();
        }
    }

    @Transactional
    public boolean compraExitosa(String id_viaje) {
        if (!id_viaje.isEmpty()) {
            List<Pasajero> pasajerosPagados = pasajeroRepository.findByIdViaje(Integer.valueOf(id_viaje));
            for (Pasajero pasajero : pasajerosPagados) {
                pasajero.setPagado(true);
            }
            pasajeroRepository.saveAll(pasajerosPagados);
            return true;
        }
        return false;
    }
}

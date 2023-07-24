package com.royaltyFlights.appwebRF.controller;

import com.royaltyFlights.appwebRF.model.Pasajero;
import com.royaltyFlights.appwebRF.service.CreacionCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/creacion-cita")
@CrossOrigin(origins = "https://www.royaltyflightsgt.com")
public class CreacionCitaController {
    private final CreacionCitaService creacionCitaService;

    @Autowired
    public CreacionCitaController(CreacionCitaService creacionCitaService) {
        this.creacionCitaService = creacionCitaService;
    }

    @PostMapping("/sesion-pago")
    public ResponseEntity<Object> crearSesionPago(@RequestBody List<Pasajero> pasajeros) {
        if (pasajeros == null || pasajeros.isEmpty()) {
            // Si no se proporciona ningún pasajero en la solicitud, devuelve un error 400 Bad Request.
            String mensajeError = "No se proporcionaron pasajeros válidos";
            return ResponseEntity.badRequest().body(mensajeError);
        }
        // Procesa los pasajeros y realiza la lógica necesaria...
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse = creacionCitaService.urlPago(pasajeros);

        // Si la operación fue exitosa, devuelve un código de estado 200 OK junto con los datos actualizados.
        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping("/verificar-pago")
    public ResponseEntity<Object> verificarPago(@RequestParam("sesionId") String sesionId, @RequestParam("id_viaje") String id_viaje) {
        // Lógica del otro método POST...
        boolean esExitoso = false;
        if (creacionCitaService.verificarCompra(sesionId)) {
            esExitoso = creacionCitaService.compraExitosa(id_viaje);
            return ResponseEntity.ok(esExitoso);
        }
        // Devuelve una respuesta apropiada según la lógica de tu aplicación.
        return ResponseEntity.ok(esExitoso);
    }

}

package com.royaltyFlights.appwebRF.service;

import com.royaltyFlights.appwebRF.model.Paquete;
import com.royaltyFlights.appwebRF.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaqueteService {
    private final PaqueteRepository paqueteRepository;

    @Autowired
    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    public List<Paquete> obtenerTodosPaquetes() {
        return paqueteRepository.findAll();
    }

    public Paquete obtenerPaquete(Integer packageID) {
        return paqueteRepository.findById(packageID).orElseThrow(() -> new IllegalArgumentException("El Paquete Con El ID: " + packageID + " No Existe"));
    }

    public Paquete agregarPaquete(Paquete paquete) {
        Paquete tmp = null;
        tmp = paqueteRepository.save(paquete);
        return tmp;
    }

    public Paquete removerPaquete(Integer packageID) {
        Paquete tmp = null;
        if (paqueteRepository.existsById(packageID)) {
            tmp = paqueteRepository.findById(packageID).get();
            paqueteRepository.deleteById(packageID);
        }
        return tmp;
    }

    public Paquete actualizarPaquete(Integer idPaquete, String nombrePaquete, String tipoVuelo, String descripcion, Integer numeroPersonas, Float precio, Float precioIndividual) {
        Paquete tmp = null;
        if (paqueteRepository.existsById(idPaquete)) {
            tmp = paqueteRepository.findById(idPaquete).get();
            if (nombrePaquete != null) tmp.setNombre_paquete(nombrePaquete);
            if (tipoVuelo != null) tmp.setTipo_vuelo(tipoVuelo);
            if (descripcion != null) tmp.setDescripcion(descripcion);
            if (numeroPersonas != null) tmp.setNumero_personas(numeroPersonas);
            if (precio != null) tmp.setPrecio(precio);
            if (precioIndividual != null) tmp.setPrecio_individual(precioIndividual);
            paqueteRepository.save(tmp);
        }
        return tmp;
    }
}

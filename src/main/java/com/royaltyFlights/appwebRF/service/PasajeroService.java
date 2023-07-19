package com.royaltyFlights.appwebRF.service;

import com.royaltyFlights.appwebRF.model.Pasajero;
import com.royaltyFlights.appwebRF.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class PasajeroService {
    private final PasajeroRepository pasajeroRepository;

    @Autowired
    public PasajeroService(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    public List<Pasajero> obtenerPasajeros() {
        return pasajeroRepository.findAll();
    }

    public Pasajero obtenerPasajero(Integer passangerID) {
        return pasajeroRepository.findById(passangerID).orElseThrow(() -> new IllegalArgumentException("El Pasajero Con El ID: " + passangerID + " No Existe"));
    }

    public Pasajero agregarPasajero(Pasajero pasajero) {
        Pasajero tmp = null;
        tmp = pasajeroRepository.save(pasajero);
        return tmp;
    }

    public Pasajero removerPasajero(Integer passangerID) {
        Pasajero tmp = null;
        if (pasajeroRepository.existsById(passangerID)) {
            tmp = pasajeroRepository.findById(passangerID).get();
            pasajeroRepository.deleteById(passangerID);
        }
        return tmp;
    }


    public Pasajero actualizarPasajero(Integer passangerID, Integer id_viaje, String nombres, String apellidos, String correo, Time hora, Boolean pagado, String dpi, Float peso, String fecha, Float total) {
        Pasajero tmp = null;
        if (pasajeroRepository.existsById(passangerID)) {
            tmp = pasajeroRepository.findById(passangerID).get();
            if (id_viaje != null) tmp.setId_viaje(id_viaje);
            if (nombres != null) tmp.setNombres(nombres);
            if (apellidos != null) tmp.setApellidos(apellidos);
            if (correo != null) tmp.setCorreo(correo);
            if (hora != null) tmp.setHora(hora);
            if (pagado != null) tmp.setPagado(pagado);
            if (dpi != null) tmp.setDpi(dpi);
            if (peso != null) tmp.setPeso(peso);
            if (fecha != null) tmp.setFecha(Date.valueOf(fecha));
            if (total != null) tmp.setTotal(total);
            pasajeroRepository.save(tmp);
        }
        return tmp;
    }
}

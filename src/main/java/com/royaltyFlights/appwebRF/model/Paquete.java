package com.royaltyFlights.appwebRF.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_paquete;
    private String nombre_paquete;
    private String tipo_vuelo;
    private String descripcion;
    private Integer numero_personas;
    private Float precio;
    private Float precio_individual;
}

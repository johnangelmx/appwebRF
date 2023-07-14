package com.royaltyFlights.appwebRF.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pasajero;
    @ManyToOne
    @JoinColumn(name = "id_paquete")
    private Paquete paqueteId;
    private Integer id_viaje;
    private String nombres;
    private String apellidos;
    private String correo;
    private Time hora;
    private Boolean pagado;
    private String dpi;
    private Float peso;
    private Date fecha;
}

package com.royaltyFlights.appwebRF.repository;

import com.royaltyFlights.appwebRF.model.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Integer> {
    @Query("SELECT p FROM Pasajero p WHERE p.id_viaje = :id")
    List<Pasajero> findByIdViaje(Integer id);
}

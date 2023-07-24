package com.royaltyFlights.appwebRF.controller;

import com.royaltyFlights.appwebRF.model.Pasajero;
import com.royaltyFlights.appwebRF.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/pasajeros")
@CrossOrigin(origins = "https://www.royaltyflightsgt.com")
public class PasajeroController {
    private final PasajeroService pasajeroService;

    @Autowired
    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    // ----------------------------------------------------------------
    // Obtener - GET and GET especific
    @GetMapping
    public List<Pasajero> getPassengers() {
        return pasajeroService.obtenerPasajeros();
    }

    @GetMapping(path = "{passangerID}")
    public Pasajero getPassenger(@PathVariable("passangerID") Integer passangerID) {
        return pasajeroService.obtenerPasajero(passangerID);
    }

    // ----------------------------------------------------------------
    // POST / passanger
    @PostMapping
    public Pasajero addPassanger(@RequestBody Pasajero pasajero) {
        return pasajeroService.agregarPasajero(pasajero);
    }

    // ----------------------------------------------------------------
    // DELETE / passanger
    @DeleteMapping(path = "{passangerID}")
    public Pasajero removePassanger(@PathVariable Integer passangerID) {
        return pasajeroService.removerPasajero(passangerID);
    }

    // ----------------------------------------------------------------
    // PUT / passanger
    @PutMapping(path = "{passangerID}")
    public Pasajero updatePassanger(@PathVariable Integer passangerID,
                                    @RequestParam(required = false) Integer id_viaje,
                                    @RequestParam(required = false) String nombres,
                                    @RequestParam(required = false) String apellidos,
                                    @RequestParam(required = false) String correo,
                                    @RequestParam(required = false) Time hora,
                                    @RequestParam(required = false) Boolean pagado,
                                    @RequestParam(required = false) String dpi,
                                    @RequestParam(required = false) Float peso,
                                    @RequestParam(required = false) String fecha,
                                    @RequestParam(required = false) Float total
    ) {
        return pasajeroService.actualizarPasajero(passangerID, id_viaje, nombres, apellidos, correo, hora, pagado, dpi, peso, fecha, total
        );
    }

}

package com.royaltyFlights.appwebRF.controller;

import com.royaltyFlights.appwebRF.model.Paquete;
import com.royaltyFlights.appwebRF.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/paquetes")
public class PaqueteController {
    private final PaqueteService paqueteService;

    @Autowired
    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    //    OBTENER
    @GetMapping
    public List<Paquete> getAllPackage() {
        return paqueteService.obtenerTodosPaquetes();
    }

    //    OBTENER por ID
    @GetMapping(path = "{packageID}")
    public Paquete getPackage(@PathVariable("packageID") Integer packageID) {
        return paqueteService.obtenerPaquete(packageID);
    }

    //    Agregar
    @PostMapping
    public Paquete addPackage(@RequestBody Paquete paquete) {
        return paqueteService.agregarPaquete(paquete);
    }

    // Eliminar Recursivo
    @DeleteMapping(path = "{packageID}")
    public Paquete removePackage(@PathVariable Integer packageID) {
        return paqueteService.removerPaquete(packageID);
    }


    @PutMapping(path = "{packageID}")
    public Paquete updatePackage(@PathVariable("packageID") Integer id_paquete,
                                 @RequestParam(required = false) String nombre_paquete,
                                 @RequestParam(required = false) String tipo_vuelo,
                                 @RequestParam(required = false) String descripcion,
                                 @RequestParam(required = false) Integer numero_personas,
                                 @RequestParam(required = false) Float precio,
                                 @RequestParam(required = false) Float precio_individual
    ) {
        return paqueteService.actualizarPaquete(id_paquete, nombre_paquete, tipo_vuelo, descripcion, numero_personas, precio, precio_individual);
    }

}

package com.example.ProjectQr_reader.controller;

import com.example.ProjectQr_reader.model.Servicio;
import com.example.ProjectQr_reader.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicios")
@CrossOrigin(origins = "http://localhost:3000")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    /**
     * Endpoint para obtener todos los servicios
     */
    @GetMapping
    public ResponseEntity<List<Servicio>> obtenerTodosLosServicios() {
        List<Servicio> servicios = servicioService.obtenerTodosLosServicios();
        return ResponseEntity.ok(servicios);
    }

    /**
     * Endpoint para obtener un servicio por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable Integer id) {
        Servicio servicio = servicioService.obtenerServicioPorId(id);
        return ResponseEntity.ok(servicio);
    }
}

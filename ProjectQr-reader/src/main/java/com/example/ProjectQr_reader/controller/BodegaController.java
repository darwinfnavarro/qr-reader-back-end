package com.example.ProjectQr_reader.controller;

import com.example.ProjectQr_reader.model.Bodega;
import com.example.ProjectQr_reader.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bodegas")
public class BodegaController {

    @Autowired
    private BodegaService bodegaService;

    /**
     * Endpoint para obtener todas las bodegas
     */
    @GetMapping
    public ResponseEntity<List<Bodega>> obtenerTodasLasBodegas() {
        List<Bodega> bodegas = bodegaService.obtenerTodasLasBodegas();
        return ResponseEntity.ok(bodegas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Bodega> obtenerBodegaPorId(@PathVariable Integer id) {
        Bodega bodega = bodegaService.obtenerBodegaPorId(id);
        return ResponseEntity.ok(bodega);
    }
}

package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.model.Bodega;
import com.example.ProjectQr_reader.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodegaService {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Método para obtener todas las bodegas
    public List<Bodega> obtenerTodasLasBodegas() {
        return bodegaRepository.findAll();  // Obtiene todas las bodegas de la base de datos
    }

    // Método para obtener una bodega por su ID
    public Bodega obtenerBodegaPorId(Integer id) {
        return bodegaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bodega no encontrada con ID: " + id));  // Lanza error si no se encuentra
    }
}

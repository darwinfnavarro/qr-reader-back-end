package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.model.Servicio;
import com.example.ProjectQr_reader.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    // Método para obtener todos los servicios
    public List<Servicio> obtenerTodosLosServicios() {
        return servicioRepository.findAll();  // Obtiene todos los servicios de la base de datos
    }

    // Método para obtener un servicio por su ID
    public Servicio obtenerServicioPorId(Integer id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con ID: " + id));  // Lanza error si no se encuentra
    }
}

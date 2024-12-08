package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.model.Producto;
import com.example.ProjectQr_reader.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto obtenerProductoPorImei(String imei) {
        Optional<Producto> producto = productoRepository.findByImei(imei);
        return producto.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con IMEI: " + imei));
    }
}

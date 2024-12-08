package com.example.ProjectQr_reader.controller;

import com.example.ProjectQr_reader.model.Producto;
import com.example.ProjectQr_reader.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Este endpoint recibe el IMEI como parámetro y retorna los datos del producto correspondiente.
     *
     * @param imei El IMEI del producto a buscar.
     * @return Los detalles del producto con el IMEI proporcionado.
     */
    @GetMapping("/producto/imei/{imei}")
    public ResponseEntity<Producto> obtenerProductoPorImei(@PathVariable String imei) {
        Producto producto = productoService.obtenerProductoPorImei(imei);
        return ResponseEntity.ok(producto);
    }
}

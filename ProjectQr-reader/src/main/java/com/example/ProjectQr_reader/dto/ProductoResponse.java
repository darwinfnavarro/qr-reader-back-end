package com.example.ProjectQr_reader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Genera el constructor con todos los argumentos
@NoArgsConstructor  // Genera el constructor vacío (opcional)
public class ProductoResponse {
    private String imei;
    private String bodegaNombre;
    private String servicioNombre;
}

package com.example.ProjectQr_reader.controller;

import com.example.ProjectQr_reader.dto.RegistroDTO;
import com.example.ProjectQr_reader.model.Registro;
import com.example.ProjectQr_reader.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registros")
public class RegistroController {

    @Autowired
    private RegistroService
            registroService;



    /**
     * Este método crea múltiples registros en la base de datos sin enviar el lote desde el cliente.
     *
     * @param registrosInfo Una lista de listas, cada una representando los parámetros
     *                      [productoId, bodegaId, servicioId].
     * @return Los registros creados.
     */
    @PostMapping("/bulk")
    public ResponseEntity<List<Registro>> crearRegistros(
            @RequestBody List<List<Integer>> registrosInfo) {

        try {
            // Llamar al servicio para crear los registros
            List<Registro> registros = registroService.crearRegistros(registrosInfo);
            // Devolver los registros creados en la respuesta con un código HTTP 201 (CREADO)
            return new ResponseEntity<>(registros, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Si ocurre un error, devolver un código HTTP 400 (BAD REQUEST)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/pendientes")
    public List<RegistroDTO> obtenerRegistrosPendientes() {
        return registroService.obtenerRegistrosPorEstadoPendiente();
    }




}

package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.model.Lote;
import com.example.ProjectQr_reader.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    /**
     * Crea un lote si no existe uno con la misma fecha actual.
     * Si ya existe, lo reutiliza.
     *
     * @return Lote creado o existente.
     */
    public Lote crearLote() {
        // Crear un nuevo lote con la fecha actual
        Lote lote = new Lote();
        lote.setFechaCreacion(LocalDateTime.now());
        lote.setEstado(Lote.ESTADO_PENDIENTE);// Asignar la fecha y hora actuales
        lote = loteRepository.save(lote);  // Guardar el nuevo lote en la base de datos

        return lote;  // Retornar el lote creado
    }

    public Lote autorizarLote(Integer loteId) {
        Lote lote = loteRepository.findById(loteId).orElseThrow(() -> new IllegalArgumentException("Lote no encontrado"));

        // Cambiar el estado del lote a "Autorizado" usando la constante de Lote
        lote.setEstado(Lote.ESTADO_AUTORIZADO);

        // Guardar el lote actualizado
        return loteRepository.save(lote);
    }

    public Lote rechazarLote(Integer loteId) {
        Lote lote = loteRepository.findById(loteId).orElseThrow(() -> new IllegalArgumentException("Lote no encontrado"));

        // Cambiar el estado del lote a "Rechazado" usando la constante de Lote
        lote.setEstado(Lote.ESTADO_RECHAZADO);

        // Guardar el lote actualizado
        return loteRepository.save(lote);
    }

}

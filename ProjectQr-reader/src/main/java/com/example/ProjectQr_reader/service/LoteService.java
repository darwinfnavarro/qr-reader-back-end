package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.model.Lote;
import com.example.ProjectQr_reader.model.Registro;
import com.example.ProjectQr_reader.model.RegistrosAutorizados;
import com.example.ProjectQr_reader.model.RegistrosRechazados;
import com.example.ProjectQr_reader.repository.LoteRepository;
import com.example.ProjectQr_reader.repository.RegistroRepository;
import com.example.ProjectQr_reader.repository.RegistrosAutorizadosRepository;
import com.example.ProjectQr_reader.repository.RegistrosRechazadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private RegistrosRechazadosRepository registrosRechazadosRepository;

    @Autowired
    private RegistrosAutorizadosRepository registrosAutorizadosRepository;

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
        lote.setEstado(Lote.ESTADO_PENDIENTE); // Asignar la fecha y hora actuales
        lote = loteRepository.save(lote);  // Guardar el nuevo lote en la base de datos

        return lote;  // Retornar el lote creado
    }

    /**
     * Autoriza un lote por su ID y cambia su estado a "Autorizado".
     *
     * @param loteId El ID del lote a autorizar.
     * @return Lote actualizado con estado "Autorizado".
     */
    public Lote autorizarLote(Integer loteId) {
        Lote lote = loteRepository.findById(loteId).orElseThrow(() -> new IllegalArgumentException("Lote no encontrado"));

        // Cambiar el estado del lote a "Autorizado" usando la constante de Lote
        lote.setEstado(Lote.ESTADO_AUTORIZADO);
        List<Registro> registrosPendientes = registroRepository.findRegistrosByLoteEstado("Pendiente");

        for (Registro registro : registrosPendientes) {
            // Crear el nuevo registro para la tabla de rechazados
            RegistrosAutorizados registroAutorizado = new RegistrosAutorizados();
            registroAutorizado.setProductoId(registro.getProducto().getId());
            registroAutorizado.setBodegaId(registro.getBodega().getId());
            registroAutorizado.setServicioId(registro.getServicio().getId());
            registroAutorizado.setLoteId(loteId);
            registroAutorizado.setFechaRegistro(LocalDateTime.now());

            // Guardar el registro en la tabla de rechazados
            registrosAutorizadosRepository.save(registroAutorizado);

            // Eliminar el registro de la tabla de registros
            registroRepository.delete(registro);
        }

        // Guardar el lote actualizado
        return loteRepository.save(lote);
    }

    /**
     * Rechaza un lote por su ID, cambia su estado a "Rechazado" y mueve los registros a la tabla de rechazados.
     *
     * @param loteId El ID del lote a rechazar.
     * @return Lote actualizado con estado "Rechazado".
     */
    public Lote rechazarLote(Integer loteId) {
        Lote lote = loteRepository.findById(loteId).orElseThrow(() -> new IllegalArgumentException("Lote no encontrado"));

        // Cambiar el estado del lote a "Rechazado"
        lote.setEstado(Lote.ESTADO_RECHAZADO);

        // Mover los registros pendientes a la tabla de rechazados
        List<Registro> registrosPendientes = registroRepository.findRegistrosByLoteEstado("Pendiente");

        for (Registro registro : registrosPendientes) {
            // Crear el nuevo registro para la tabla de rechazados
            RegistrosRechazados registroRechazado = new RegistrosRechazados();
            registroRechazado.setProductoId(registro.getProducto().getId());
            registroRechazado.setBodegaId(registro.getBodega().getId());
            registroRechazado.setServicioId(registro.getServicio().getId());
            registroRechazado.setLoteId(loteId);
            registroRechazado.setFechaRegistro(LocalDateTime.now());

            // Guardar el registro en la tabla de rechazados
            registrosRechazadosRepository.save(registroRechazado);

            // Eliminar el registro de la tabla de registros
            registroRepository.delete(registro);
        }

        // Guardar el lote con el estado actualizado
        return loteRepository.save(lote);
    }

}

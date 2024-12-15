package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.model.Lote;
import com.example.ProjectQr_reader.model.Producto;
import com.example.ProjectQr_reader.model.Registro;
import com.example.ProjectQr_reader.model.RegistrosAutorizados;
import com.example.ProjectQr_reader.model.RegistrosRechazados;
import com.example.ProjectQr_reader.repository.LoteRepository;
import com.example.ProjectQr_reader.repository.ProductoRepository;
import com.example.ProjectQr_reader.repository.RegistroRepository;
import com.example.ProjectQr_reader.repository.RegistrosAutorizadosRepository;
import com.example.ProjectQr_reader.repository.RegistrosRechazadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RegistrosRechazadosRepository registrosRechazadosRepository;

    @Autowired
    private RegistrosAutorizadosRepository registrosAutorizadosRepository;

    /**
     * Crea un lote nuevo con estado "Pendiente".
     *
     * @return Lote creado.
     */
    public Lote crearLote() {
        Lote lote = new Lote();
        lote.setFechaCreacion(LocalDateTime.now());
        lote.setEstado(Lote.ESTADO_PENDIENTE);
        return loteRepository.save(lote);
    }

    /**
     * Autoriza un lote, cambia su estado a "Autorizado" y mueve los registros de ese lote a la tabla de autorizados.
     * Además, actualiza la descripción del producto y el clienteId en la tabla Productos.
     *
     * @param loteId El ID del lote a autorizar.
     * @return Lote actualizado.
     */
    public Lote autorizarLote(Integer loteId) {
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new IllegalArgumentException("Lote no encontrado"));

        lote.setEstado(Lote.ESTADO_AUTORIZADO);
        List<Registro> registrosPendientes = registroRepository.findRegistrosByLoteId(loteId);

        for (Registro registro : registrosPendientes) {
            Producto producto = registro.getProducto();
            producto.setServicioId(registro.getServicio().getId());
            producto.setBodegaId(registro.getBodega().getId());
            productoRepository.save(producto);

            RegistrosAutorizados registroAutorizado = new RegistrosAutorizados();
            registroAutorizado.setProducto(producto);
            registroAutorizado.setBodega(registro.getBodega());
            registroAutorizado.setServicio(registro.getServicio());
            registroAutorizado.setLote(registro.getLote());
            registroAutorizado.setFechaRegistro(LocalDateTime.now());

            registrosAutorizadosRepository.save(registroAutorizado);
            registroRepository.delete(registro);
        }

        return loteRepository.save(lote);
    }

    /**
     * Rechaza un lote, cambia su estado a "Rechazado" y mueve los registros de ese lote a la tabla de rechazados.
     *
     * @param loteId El ID del lote a rechazar.
     * @return Lote actualizado.
     */
    public Lote rechazarLote(Integer loteId) {
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new IllegalArgumentException("Lote no encontrado"));

        lote.setEstado(Lote.ESTADO_RECHAZADO);
        List<Registro> registrosPendientes = registroRepository.findRegistrosByLoteId(loteId);

        for (Registro registro : registrosPendientes) {
            RegistrosRechazados registroRechazado = new RegistrosRechazados();
            registroRechazado.setProducto(registro.getProducto());
            registroRechazado.setBodega(registro.getBodega());
            registroRechazado.setServicio(registro.getServicio());
            registroRechazado.setLote(registro.getLote());
            registroRechazado.setFechaRegistro(LocalDateTime.now());

            registrosRechazadosRepository.save(registroRechazado);
            registroRepository.delete(registro);
        }

        return loteRepository.save(lote);
    }

    /**
     * Obtiene los registros rechazados agrupados por lote.
     *
     * @return Un mapa donde la clave es el lote y el valor es la lista de registros rechazados.
     */
    public Map<Lote, List<RegistrosRechazados>> obtenerRegistrosRechazadosPorLote() {
        List<RegistrosRechazados> registrosRechazados = registrosRechazadosRepository.findAll();

        return registrosRechazados.stream()
                .collect(Collectors.groupingBy(RegistrosRechazados::getLote));
    }

    /**
     * Obtiene los registros autorizados agrupados por lote.
     *
     * @return Un mapa donde la clave es el lote y el valor es la lista de registros autorizados.
     */
    public Map<Lote, List<RegistrosAutorizados>> obtenerRegistrosAutorizadosPorLote() {
        List<RegistrosAutorizados> registrosAutorizados = registrosAutorizadosRepository.findAll();

        return registrosAutorizados.stream()
                .collect(Collectors.groupingBy(RegistrosAutorizados::getLote));
    }
}

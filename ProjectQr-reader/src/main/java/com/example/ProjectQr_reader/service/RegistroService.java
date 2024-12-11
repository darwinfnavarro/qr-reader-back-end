package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.dto.RegistroDTO;
import com.example.ProjectQr_reader.model.*;
import com.example.ProjectQr_reader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistroService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private RegistrosRechazadosRepository registrosRechazadosRepository;

    @Autowired
    private RegistrosAutorizadosRepository registrosAutorizadosRepository;

    @Autowired
    private LoteService loteService;

    /**
     * Crea múltiples registros en la base de datos.
     */
    public List<Registro> crearRegistros(List<List<Integer>> registrosInfo) {
        List<Registro> registrosCreados = new ArrayList<>();

        for (List<Integer> info : registrosInfo) {
            Integer productoId = info.get(0);
            Optional<Registro> registroExistente = registroRepository.findByProductoId(productoId);
            if (registroExistente.isPresent()) {
                throw new IllegalArgumentException("El producto con ID " + productoId + " ya está registrado.");
            }
        }

        Lote lote = loteService.crearLote();

        for (List<Integer> info : registrosInfo) {
            Integer productoId = info.get(0);
            Integer bodegaId = info.get(1);
            Integer servicioId = info.get(2);

            Optional<Producto> producto = productoRepository.findById(productoId);
            Optional<Bodega> bodega = bodegaRepository.findById(bodegaId);
            Optional<Servicio> servicio = servicioRepository.findById(servicioId);

            if (!producto.isPresent() || !bodega.isPresent() || !servicio.isPresent()) {
                throw new IllegalArgumentException("Producto, Bodega o Servicio no encontrados");
            }

            Registro registro = new Registro();
            registro.setProducto(producto.get());
            registro.setBodega(bodega.get());
            registro.setServicio(servicio.get());
            registro.setLote(lote);
            registro.setFechaRegistro(LocalDateTime.now());

            registrosCreados.add(registroRepository.save(registro));
        }

        return registrosCreados;
    }

    /**
     * Obtiene registros pendientes según lógica existente.
     */
    public List<RegistroDTO> obtenerRegistrosPorEstadoPendiente() {
        List<Registro> registros = registroRepository.findRegistrosByLoteEstado("Pendiente");

        return registros.stream()
                .map(registro -> new RegistroDTO(
                        registro.getProducto().getId(),
                        registro.getProducto().getImei(),
                        registro.getProducto().getNombre(),
                        registro.getBodega().getNombre(),
                        registro.getServicio().getDescripcion(),
                        registro.getFechaRegistro().toString(),
                        registro.getLote().getId()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene registros autorizados como lista plana.
     */
    public List<RegistroDTO> obtenerRegistrosAutorizados() {
        List<RegistrosAutorizados> registrosAutorizados = registrosAutorizadosRepository.findAll();

        return registrosAutorizados.stream()
                .map(registroAprobado -> new RegistroDTO(
                        registroAprobado.getProducto().getId(),
                        registroAprobado.getProducto().getImei(),
                        registroAprobado.getProducto().getNombre(),
                        registroAprobado.getBodega().getNombre(),
                        registroAprobado.getServicio().getDescripcion(),
                        registroAprobado.getFechaRegistro().toString(),
                        registroAprobado.getLote().getId()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene registros rechazados como lista plana.
     */
    public List<RegistroDTO> obtenerRegistrosRechazados() {
        List<RegistrosRechazados> registrosRechazados = registrosRechazadosRepository.findAll();

        return registrosRechazados.stream()
                .map(registroRechazado -> new RegistroDTO(
                        registroRechazado.getProducto().getId(),
                        registroRechazado.getProducto().getImei(),
                        registroRechazado.getProducto().getNombre(),
                        registroRechazado.getBodega().getNombre(),
                        registroRechazado.getServicio().getDescripcion(),
                        registroRechazado.getFechaRegistro().toString(),
                        registroRechazado.getLote().getId()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene registros autorizados agrupados por lote.
     */
    public Map<Lote, List<RegistroDTO>> obtenerRegistrosAutorizadosPorLote() {
        List<Object[]> resultados = registrosAutorizadosRepository.findAutorizadosAgrupadosPorLote();

        return resultados.stream()
                .collect(Collectors.groupingBy(
                        resultado -> (Lote) resultado[0],
                        Collectors.mapping(
                                resultado -> new RegistroDTO(
                                        ((RegistrosAutorizados) resultado[1]).getProducto().getId(),
                                        ((RegistrosAutorizados) resultado[1]).getProducto().getImei(),
                                        ((RegistrosAutorizados) resultado[1]).getProducto().getNombre(),
                                        ((RegistrosAutorizados) resultado[1]).getBodega().getNombre(),
                                        ((RegistrosAutorizados) resultado[1]).getServicio().getDescripcion(),
                                        ((RegistrosAutorizados) resultado[1]).getFechaRegistro().toString(),
                                        ((RegistrosAutorizados) resultado[1]).getLote().getId()
                                ),
                                Collectors.toList()
                        )
                ));
    }

    /**
     * Obtiene registros rechazados agrupados por lote.
     */
    public Map<Lote, List<RegistroDTO>> obtenerRegistrosRechazadosPorLote() {
        List<Object[]> resultados = registrosRechazadosRepository.findRechazadosAgrupadosPorLote();

        return resultados.stream()
                .collect(Collectors.groupingBy(
                        resultado -> (Lote) resultado[0],
                        Collectors.mapping(
                                resultado -> new RegistroDTO(
                                        ((RegistrosRechazados) resultado[1]).getProducto().getId(),
                                        ((RegistrosRechazados) resultado[1]).getProducto().getImei(),
                                        ((RegistrosRechazados) resultado[1]).getProducto().getNombre(),
                                        ((RegistrosRechazados) resultado[1]).getBodega().getNombre(),
                                        ((RegistrosRechazados) resultado[1]).getServicio().getDescripcion(),
                                        ((RegistrosRechazados) resultado[1]).getFechaRegistro().toString(),
                                        ((RegistrosRechazados) resultado[1]).getLote().getId()
                                ),
                                Collectors.toList()
                        )
                ));
    }
}

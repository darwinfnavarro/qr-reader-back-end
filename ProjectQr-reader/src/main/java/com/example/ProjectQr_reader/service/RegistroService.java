package com.example.ProjectQr_reader.service;

import com.example.ProjectQr_reader.dto.RegistroDTO;
import com.example.ProjectQr_reader.model.*;
import com.example.ProjectQr_reader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
     * Este método crea múltiples registros en la base de datos,
     * asegurándose de que todos los registros sean asociados a un único lote.
     *
     * @param registrosInfo Una lista de listas, cada una representando los parámetros
     *                      [productoId, bodegaId, servicioId].
     * @return Los registros creados.
     */
    public List<Registro> crearRegistros(List<List<Integer>> registrosInfo) {
        List<Registro> registrosCreados = new ArrayList<>();

        // Paso 1: Validar que no haya productos duplicados antes de crear el lote
        for (List<Integer> info : registrosInfo) {
            Integer productoId = info.get(0);

            // Verificar si el producto ya está registrado
            Optional<Registro> registroExistente = registroRepository.findByProductoId(productoId);
            if (registroExistente.isPresent()) {
                // Si el producto ya está registrado, lanzamos una excepción y no procesamos este lote
                throw new IllegalArgumentException("El producto con ID " + productoId + " ya está registrado.");
            }
        }

        // Paso 2: Crear un lote único para la solicitud
        Lote lote = loteService.crearLote();  // Usamos el método para crear el lote

        // Paso 3: Crear los registros asociados al lote
        for (List<Integer> info : registrosInfo) {
            Integer productoId = info.get(0);
            Integer bodegaId = info.get(1);
            Integer servicioId = info.get(2);

            // Verificar que el producto, bodega y servicio existen
            Optional<Producto> producto = productoRepository.findById(productoId);
            Optional<Bodega> bodega = bodegaRepository.findById(bodegaId);
            Optional<Servicio> servicio = servicioRepository.findById(servicioId);

            if (!producto.isPresent() || !bodega.isPresent() || !servicio.isPresent()) {
                throw new IllegalArgumentException("Producto, Bodega o Servicio no encontrados");
            }

            // Crear el nuevo registro
            Registro registro = new Registro();
            registro.setProducto(producto.get());
            registro.setBodega(bodega.get());
            registro.setServicio(servicio.get());
            registro.setLote(lote);  // Relacionar el lote con el registro
            registro.setFechaRegistro(LocalDateTime.now()); // Fecha de registro

            // Guardar el registro en la base de datos
            registrosCreados.add(registroRepository.save(registro));
        }

        return registrosCreados;
    }


    public List<RegistroDTO> obtenerRegistrosPorEstadoPendiente() {
        // Obtener los registros con lote "Pendiente"
        List<Registro> registros = registroRepository.findRegistrosByLoteEstado("Pendiente");

        System.out.println("Número de registros encontrados: " + registros.size());

        // Convertir los registros a RegistroDTO
        return registros.stream()
                .map(registro -> new RegistroDTO(
                        registro.getProducto().getId(),                // productoId
                        registro.getProducto().getImei(),              // imei
                        registro.getProducto().getNombre(),            // nombreProducto
                        registro.getBodega().getNombre(),              // nombreBodega
                        registro.getServicio().getDescripcion(),      // descripcionServicio
                        registro.getFechaRegistro().toString(),       // fechaRegistro
                        registro.getLote().getId()                     // loteId
                ))
                .collect(Collectors.toList());
    }

    public List<RegistroDTO> obtenerRegistrosAutorizados() {
        // Obtener los registros autorizados
        List<RegistrosAutorizados> registrosAutorizados = registrosAutorizadosRepository.findAll();

        // Convertir los registros aprobados a RegistroDTO y devolver la lista
        return registrosAutorizados.stream()
                .map(registroAprobado -> new RegistroDTO(
                        registroAprobado.getProductoId(),
                        "",  // imei
                        "",  // nombreProducto
                        "",  // nombreBodega
                        "",  // descripcionServicio
                        registroAprobado.getFechaRegistro().toString(),
                        registroAprobado.getLoteId()
                ))
                .collect(Collectors.toList());
    }

    public List<RegistroDTO> obtenerRegistrosRechazados() {
        // Obtener los registros rechazados
        List<RegistrosRechazados> registrosRechazados = registrosRechazadosRepository.findAll();

        // Convertir los registros rechazados a RegistroDTO y devolver la lista
        return registrosRechazados.stream()
                .map(registroRechazado -> new RegistroDTO(
                        registroRechazado.getProductoId(),
                        "",  // imei
                        "",  // nombreProducto
                        "",  // nombreBodega
                        "",  // descripcionServicio
                        registroRechazado.getFechaRegistro().toString(),
                        registroRechazado.getLoteId()
                ))
                .collect(Collectors.toList());
    }




}

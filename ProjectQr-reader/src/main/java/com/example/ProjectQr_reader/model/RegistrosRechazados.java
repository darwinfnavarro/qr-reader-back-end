package com.example.ProjectQr_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "registros_rechazados")
public class RegistrosRechazados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "bodega_id")
    private Integer bodegaId;

    @Column(name = "servicio_id")
    private Integer servicioId;

    @Column(name = "lote_id")
    private Integer loteId;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}

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

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private Bodega bodega;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}

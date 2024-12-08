package com.example.ProjectQr_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Cambio de Long a Integer

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public static final String ESTADO_PENDIENTE = "Pendiente";
    public static final String ESTADO_AUTORIZADO = "Autorizado";
    public static final String ESTADO_RECHAZADO = "Rechazado";

    @Column(name = "estado")
    private String estado;
}

package com.example.ProjectQr_reader.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bodega_id", referencedColumnName = "Id", nullable = false)
    @JsonBackReference
    private Bodega bodega;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "Id", nullable = false)
    @JsonBackReference
    private Servicio servicio; // Cambiado a relacion ManyToOne con Servicio

    @Column(name = "Observacion")
    private String observacion;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "IMEI")
    private String imei;
}

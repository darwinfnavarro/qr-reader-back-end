package com.example.ProjectQr_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Productos")
public class Producto {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "bodega_id")
    private Integer bodegaId;

    @Column(name = "servicio_id")
    private Integer servicioId;

    @Column(name = "Observacion")
    private String observacion;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "IMEI")
    private String imei;




}

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

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "PhysId")
    private String physId;

    @Column(name = "NumeroGpsPlataforma")
    private Integer numeroGpsPlataforma;

    @Column(name = "Vehiculo")
    private String vehiculo;

    @Column(name = "Observacion")
    private String observacion;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "IMEI")
    private String imei;

    @Column(name = "Serie")
    private String serie;

    @Column(name = "FechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "FechaFin")
    private LocalDate fechaFin;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "PrecioMoneda")
    private BigDecimal precioMoneda;

    @Column(name = "ValorCambio")
    private BigDecimal valorCambio;

    @Column(name = "Activo")
    private Boolean activo;

    @Column(name = "clienteId")  // Cambiado de ClienteId a BodegaId
    private Integer bodegaId;

    @Column(name = "Moneda")
    private String moneda;

    @Column(name = "ProductoSerieId")
    private Integer productoSerieId;

    @Column(name = "Cantidad")
    private Integer cantidad;

    @Column(name = "MonedaId")
    private Integer monedaId;

    @Column(name = "ICCId")
    private String iccId;
}

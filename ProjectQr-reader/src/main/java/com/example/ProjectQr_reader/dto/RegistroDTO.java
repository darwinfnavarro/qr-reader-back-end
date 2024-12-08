package com.example.ProjectQr_reader.dto;

public class RegistroDTO {

    private Integer productoId;
    private String imei;
    private String nombreProducto;
    private String nombreBodega;
    private String descripcionServicio;
    private String fechaRegistro;
    private Integer loteId;  // Lote ID agregado

    // Constructor público
    public RegistroDTO(Integer productoId, String imei, String nombreProducto,
                       String nombreBodega, String descripcionServicio, String fechaRegistro,
                       Integer loteId) {
        this.productoId = productoId;
        this.imei = imei;
        this.nombreProducto = nombreProducto;
        this.nombreBodega = nombreBodega;
        this.descripcionServicio = descripcionServicio;
        this.fechaRegistro = fechaRegistro;
        this.loteId = loteId;
    }

    // Getters y Setters

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getLoteId() {
        return loteId;
    }

    public void setLoteId(Integer loteId) {
        this.loteId = loteId;
    }
}

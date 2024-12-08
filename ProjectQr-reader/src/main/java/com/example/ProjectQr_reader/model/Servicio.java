package com.example.ProjectQr_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Servicios")
public class Servicio {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Url")
    private String url;
}

package com.example.ProjectQr_reader.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Url")
    private String url;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Producto> productos;
}

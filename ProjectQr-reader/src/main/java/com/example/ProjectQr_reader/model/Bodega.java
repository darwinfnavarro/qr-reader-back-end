package com.example.ProjectQr_reader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Bodegas")
public class Bodega {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;
}
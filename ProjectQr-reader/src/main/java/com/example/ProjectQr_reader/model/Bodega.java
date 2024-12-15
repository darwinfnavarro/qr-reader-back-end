package com.example.ProjectQr_reader.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Bodegas")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Producto> productos;
}

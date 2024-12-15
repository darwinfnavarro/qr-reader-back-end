package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.Producto;
import com.example.ProjectQr_reader.dto.ProductoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByImei(String imei);


    @Query("SELECT new com.example.ProjectQr_reader.dto.ProductoResponse(p.imei, b.nombre, s.descripcion) " +
            "FROM Producto p " +
            "LEFT JOIN p.bodega b " +
            "LEFT JOIN p.servicio s")

    List<ProductoResponse> obtenerInformacionProductos();
}

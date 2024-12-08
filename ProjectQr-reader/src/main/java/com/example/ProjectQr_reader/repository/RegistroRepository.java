package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {

    Optional<Registro> findByProductoId(Integer productoId);
    // Consulta personalizada que devuelve los registros con el estado del lote "Pendiente"

    // Consulta para obtener los registros cuyo lote tiene el estado "Pendiente"
    @Query("SELECT r FROM Registro r " +
            "JOIN FETCH r.lote l " +
            "JOIN FETCH r.producto p " +
            "JOIN FETCH r.bodega b " +
            "JOIN FETCH r.servicio s " +
            "WHERE l.estado = :estado")
    List<Registro> findRegistrosByLoteEstado(String estado);
}



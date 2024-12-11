package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.RegistrosRechazados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrosRechazadosRepository extends JpaRepository<RegistrosRechazados, Integer> {
    @Query("SELECT rr.lote, rr FROM RegistrosRechazados rr " +
            "JOIN FETCH rr.lote l " +
            "ORDER BY l.id")
    List<Object[]> findRechazadosAgrupadosPorLote();
}

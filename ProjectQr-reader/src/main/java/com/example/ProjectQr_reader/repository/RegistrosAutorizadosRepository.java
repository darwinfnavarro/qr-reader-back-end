package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.RegistrosAutorizados;
import com.example.ProjectQr_reader.model.RegistrosRechazados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrosAutorizadosRepository extends JpaRepository<RegistrosAutorizados, Integer> {
    @Query("SELECT ra.lote, ra FROM RegistrosAutorizados ra " +
            "JOIN FETCH ra.lote l " +
            "ORDER BY l.id")
    List<Object[]> findAutorizadosAgrupadosPorLote();

}

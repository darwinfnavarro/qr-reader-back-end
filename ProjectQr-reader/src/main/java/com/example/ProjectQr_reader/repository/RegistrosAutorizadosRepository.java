package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.RegistrosAutorizados;
import com.example.ProjectQr_reader.model.RegistrosRechazados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrosAutorizadosRepository extends JpaRepository<RegistrosAutorizados, Integer> {

}

package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
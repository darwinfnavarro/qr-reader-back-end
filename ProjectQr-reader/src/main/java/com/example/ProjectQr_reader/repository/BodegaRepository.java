package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
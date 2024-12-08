package com.example.ProjectQr_reader.repository;

import com.example.ProjectQr_reader.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {


}

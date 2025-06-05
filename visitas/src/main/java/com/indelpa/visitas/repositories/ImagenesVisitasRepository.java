package com.indelpa.visitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indelpa.visitas.models.ImagenesVisitas;

public interface ImagenesVisitasRepository extends JpaRepository<ImagenesVisitas, Long> {

    List<ImagenesVisitas> findByVisitaId(Long visitaId);
}

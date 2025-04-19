package com.indelpa.visitas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indelpa.visitas.models.Visitas;

public interface VisitasRepository extends JpaRepository<Visitas, Long> {

}

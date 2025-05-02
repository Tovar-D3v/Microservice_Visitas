package com.indelpa.visitas.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indelpa.visitas.models.Visitas;
import com.indelpa.visitas.services.VisitasService;



@RestController
@RequestMapping("/visitas")
@CrossOrigin(origins = "*")
public class VisitasController {

    @Autowired
    private VisitasService visitasService;

    @GetMapping
    public List<Visitas> getAllVisitas() {
        return visitasService.getAllVisitas();
    }

    @GetMapping("/{id}")
    public Visitas getVisitaById(@PathVariable Long id) {
        return visitasService.getVisitaById(id);
    }

    @PostMapping
    public Visitas saveVisita(@RequestBody Visitas visita) {
        return visitasService.saveVisita(visita);
    }

    @DeleteMapping("/{id}")
    public boolean deleteVisita(@PathVariable Long id) {
        return visitasService.deleteVisita(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Visitas> updateVisita(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(visitasService.updateVisita(id, updates));
    }
}

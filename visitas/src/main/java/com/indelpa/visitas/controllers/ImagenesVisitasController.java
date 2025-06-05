package com.indelpa.visitas.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indelpa.visitas.models.ImagenesVisitas;
import com.indelpa.visitas.services.ImagenesVisitasService;

@RestController
@RequestMapping("/imagenes-visitas")
@CrossOrigin(origins = "*")
public class ImagenesVisitasController {


    private final ImagenesVisitasService imagenesVisitasService;

    public ImagenesVisitasController(ImagenesVisitasService imagenesVisitasService) {
        this.imagenesVisitasService = imagenesVisitasService;
    }

    @GetMapping
    public List<ImagenesVisitas> getAllImagenes() {
        return imagenesVisitasService.getAllImagenes();
    }

    @PostMapping
    public ImagenesVisitas saveImagenesVisitas(@RequestBody ImagenesVisitas imagenesVisitas) {
        return imagenesVisitasService.saveImagenesVisitas(imagenesVisitas);
    }

    @GetMapping("/visita/{visitaId}")
    public List<ImagenesVisitas> getImagenesPorVisita(@PathVariable Long visitaId) {
        return imagenesVisitasService.getImagenesByVisitaId(visitaId);
    }
}

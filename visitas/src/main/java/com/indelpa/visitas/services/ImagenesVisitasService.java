package com.indelpa.visitas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.indelpa.visitas.models.ImagenesVisitas;
import com.indelpa.visitas.repositories.ImagenesVisitasRepository;

@Service
public class ImagenesVisitasService {

    private final ImagenesVisitasRepository imagenesVisitasRepository;

    public ImagenesVisitasService(ImagenesVisitasRepository imagenesVisitasRepository) {
        this.imagenesVisitasRepository = imagenesVisitasRepository;
    }

    public List<ImagenesVisitas> getAllImagenes() {
        return imagenesVisitasRepository.findAll();
    }

    public ImagenesVisitas saveImagenesVisitas(ImagenesVisitas imagenesVisitas) {
        return imagenesVisitasRepository.save(imagenesVisitas);
    }



    public List<ImagenesVisitas> getImagenesByVisitaId(Long visitaId) {
        return imagenesVisitasRepository.findByVisitaId(visitaId);
    }
}

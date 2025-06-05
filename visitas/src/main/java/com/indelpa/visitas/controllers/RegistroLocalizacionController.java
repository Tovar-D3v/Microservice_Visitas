package com.indelpa.visitas.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indelpa.visitas.models.RegistroLocalizacion;
import com.indelpa.visitas.services.RegistroLocalizacionService;

@RestController
@RequestMapping("/registro-localizacion")
@CrossOrigin(origins = "*")
public class RegistroLocalizacionController {

    private final RegistroLocalizacionService registroLocalizacionService;

    public RegistroLocalizacionController(RegistroLocalizacionService registroLocalizacionService) {
        this.registroLocalizacionService = registroLocalizacionService;
    }

    @PostMapping
    public RegistroLocalizacion saveLocalizacion(@RequestBody RegistroLocalizacion localizacion) {
        return registroLocalizacionService.saveLocalizacion(localizacion);
    }
}

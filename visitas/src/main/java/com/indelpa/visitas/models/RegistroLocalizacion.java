package com.indelpa.visitas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "visitas_api_registro_localizacion")
@Getter
@Setter
public class RegistroLocalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String latitud;
    private String longitud;
    private String altitud;
    private String fecha;
    
    @ManyToOne
    @JoinColumn(name = "visita_id")
    private Visitas visita;
}

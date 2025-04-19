package com.indelpa.visitas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "visitas_api_visitas" )
@Getter
@Setter
public class Visitas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Integer id_vendedor;
    private Integer creador_visita_id;
    private Integer cliente_id;
    private String fecha_visita;

}

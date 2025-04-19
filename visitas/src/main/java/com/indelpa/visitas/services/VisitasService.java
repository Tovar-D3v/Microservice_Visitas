package com.indelpa.visitas.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.indelpa.visitas.models.Visitas;
import com.indelpa.visitas.repositories.VisitasRepository;
import com.indelpa.visitas.websocket.VisitaWebSocketHandler;

import jakarta.transaction.Transactional;

@Service
public class VisitasService {

    private final VisitasRepository visitasRepository;
    private final ApplicationContext applicationContext;
    
    public VisitasService(VisitasRepository visitasRepository, ApplicationContext applicationContext) {
        this.visitasRepository = visitasRepository;
        this.applicationContext = applicationContext;
    }

    private VisitaWebSocketHandler getVisitaWebSocketHandler() {
        return applicationContext.getBean(VisitaWebSocketHandler.class);
    }
    

    /**
     * @return todas las visitas
     */
    public List<Visitas> getAllVisitas() {
        return visitasRepository.findAll();
    }

    
    /**
     * @param id
     * @return la visita con el id dado
     */
    public Visitas getVisitaById(Long id) {
        return visitasRepository.findById(id).orElse(null);
    }

    /**
     * @param visita
     * @return la visita guardada
     */
    public Visitas saveVisita(Visitas visita) {
        Visitas nuevaVisita = visitasRepository.save(visita);
        getVisitaWebSocketHandler().enviarListarVisitas();
        return nuevaVisita;
    }

    /**
     * @param id
     * @return true si se elimino la visita, false si no existe
     */
    public boolean deleteVisita(Long id) {
        if (visitasRepository.existsById(id)) {
            visitasRepository.deleteById(id);
            getVisitaWebSocketHandler().enviarListarVisitas();
            
            return true;
        }
        return false;
    }

    /**
     * @param id
     * @return editar la visita path
     */
    @Transactional
    public Visitas updateVisita(Long id, Map<String, Object> updates) {
        Optional<Visitas> visitas = visitasRepository.findById(id);

        Visitas visitasActualizados = visitas.get();

        updates.forEach((campo, valor) -> {
            try {
                Field field = Visitas.class.getDeclaredField(campo);
                field.setAccessible(false);
                field.set(visitasActualizados, valor);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error al actualizar el campo: " + campo, e);
            }
        });

        visitasRepository.save(visitasActualizados);
        getVisitaWebSocketHandler().enviarListarVisitas();

        return visitasActualizados;
    }
}

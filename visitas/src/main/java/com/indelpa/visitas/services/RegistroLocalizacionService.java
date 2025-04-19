package com.indelpa.visitas.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.indelpa.visitas.models.RegistroLocalizacion;
import com.indelpa.visitas.repositories.RegistroLocalizacionRepository;

@Service
public class RegistroLocalizacionService {

    private final RegistroLocalizacionRepository registroLocalizacionRepository;

    public RegistroLocalizacionService(RegistroLocalizacionRepository registroLocalizacionRepository) {
        this.registroLocalizacionRepository = registroLocalizacionRepository;
    }


    /**
     * @return todas las localizaciones
     */
    public List<RegistroLocalizacion> getAllLocalizaciones() {
        return registroLocalizacionRepository.findAll();
    }

        /**
     * @param id
     * @return la visita con el id dado
     */
    public RegistroLocalizacion getLocalizacionById(Long id) {
        return registroLocalizacionRepository.findById(id).orElse(null);
    }

    /**
     * @param localizacion
     * @return la localizacion guardada
     */
    public RegistroLocalizacion saveLocalizacion(RegistroLocalizacion localizacion) {
        return registroLocalizacionRepository.save(localizacion);
    }

    /**
     * @param id
     * @return true si se elimino la localizacion, false si no existe
     */
    public boolean deleteLocalizacion(Long id) {
        if (registroLocalizacionRepository.existsById(id)) {
            registroLocalizacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * @param id
     * @param updates
     * @return la localizacion actualizada
     */
    public RegistroLocalizacion updateRegistroLocalizacion(Long id, Map<String, Object> updates) {
        Optional<RegistroLocalizacion> registroLocalizacion = registroLocalizacionRepository.findById(id);

        RegistroLocalizacion regitrosLocalizacionActualizado = registroLocalizacion.get();

        updates.forEach((campo, valor) -> {
            try {
                Field field = RegistroLocalizacion.class.getDeclaredField(campo);
                field.setAccessible(true);
                field.set(regitrosLocalizacionActualizado, valor);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error al actualizar el campo: " + campo, e);
            }
        });
        
        return registroLocalizacionRepository.save(regitrosLocalizacionActualizado);
    }
}

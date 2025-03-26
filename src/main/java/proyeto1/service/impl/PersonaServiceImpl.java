package proyeto1.service.impl;

import proyeto1.exception.ResourceNotFoundException;
import proyeto1.model.Persona;
import proyeto1.repository.PersonaRepository;
import proyeto1.service.PersonaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de personas.
 *
 * Esta clase implementa la interfaz PersonaService y proporciona
 * la lógica de negocio para gestionar personas en el sistema.
 *
 * Anotaciones utilizadas:
 * - @Service: Marca la clase como un servicio de Spring
 * - @RequiredArgsConstructor: Genera constructor para inyectar campos final
 * - @Slf4j: Proporciona un logger para la clase
 * - @Transactional: Gestiona transacciones de base de datos
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PersonaServiceImpl implements PersonaService {

    /**
     * Repositorio inyectado para acceder a la base de datos.
     * El campo es final para asegurar la inmutabilidad.
     */
    private final PersonaRepository personaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        log.info("Obteniendo todas las personas");
        return personaRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> getPersonaById(Long id) {
        log.info("Buscando persona con ID: {}", id);
        return personaRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> getPersonaByDocumentoIdentidad(String documentoIdentidad) {
        log.info("Buscando persona con Documento de Identidad: {}", documentoIdentidad);
        return personaRepository.findByDocumentoIdentidad(documentoIdentidad);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Persona savePersona(Persona persona) {
        log.info("Guardando nueva persona: {}", persona.getNombre());

        // Verificamos si ya existe una persona con el mismo documento de identidad
        if (personaRepository.existsByDocumentoIdentidad(persona.getDocumentoIdentidad())) {
            log.error("Ya existe una persona con el Documento de Identidad: {}", persona.getDocumentoIdentidad());
            throw new IllegalArgumentException("Ya existe una persona con el Documento de Identidad: " + persona.getDocumentoIdentidad());
        }

        return personaRepository.save(persona);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Persona updatePersona(Long id, Persona personaDetails) {
        log.info("Actualizando persona con ID: {}", id);

        // Buscamos la persona a actualizar
        Persona existingPersona = personaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Persona con ID: {} no encontrada", id);
                    return new ResourceNotFoundException("Persona", "id", id);
                });

        // Verificamos si estamos intentando cambiar el documento de identidad a uno que ya existe
        if (!existingPersona.getDocumentoIdentidad().equals(personaDetails.getDocumentoIdentidad()) &&
                personaRepository.existsByDocumentoIdentidad(personaDetails.getDocumentoIdentidad())) {
            log.error("Ya existe una persona con el Documento de Identidad: {}", personaDetails.getDocumentoIdentidad());
            throw new IllegalArgumentException("Ya existe una persona con el Documento de Identidad: " + personaDetails.getDocumentoIdentidad());
        }

        // Actualizamos los campos (manteniendo id)
        existingPersona.setNombre(personaDetails.getNombre());
        existingPersona.setApellido(personaDetails.getApellido());
        existingPersona.setDocumentoIdentidad(personaDetails.getDocumentoIdentidad());
        existingPersona.setFechaNacimiento(personaDetails.getFechaNacimiento());
        existingPersona.setCorreo(personaDetails.getCorreo());
        existingPersona.setTelefono(personaDetails.getTelefono());
        existingPersona.setDireccion(personaDetails.getDireccion());

        // Guardamos y devolvemos la persona actualizada
        return personaRepository.save(existingPersona);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deletePersona(Long id) {
        log.info("Eliminando persona con ID: {}", id);

        // Verificamos que la persona exista
        if (!personaRepository.existsById(id)) {
            log.error("Persona con ID: {} no encontrada", id);
            throw new ResourceNotFoundException("Persona", "id", id);
        }

        personaRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Persona> searchPersonasByName(String nameFragment) {
        log.info("Buscando personas que contienen en el nombre: {}", nameFragment);
        return personaRepository.findByNombreContainingIgnoreCase(nameFragment);
    }
}


package proyeto1.service;

import proyeto1.model.Persona;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el servicio de personas.
 *
 * Esta interfaz define el contrato de operaciones disponibles
 * para gestionar personas en el sistema.
 *
 * Seguir el patrón de interfaces para servicios nos permite:
 * - Desacoplar la implementación del contrato
 * - Facilitar la creación de mocks para pruebas
 * - Permitir múltiples implementaciones con diferentes estrategias
 *
 * @version 1.0
 */
public interface PersonaService {

    /**
     * Obtiene todas las personas.
     *
     * @return Lista de todas las personas
     */
    List<Persona> getAllPersonas();

    /**
     * Busca una persona por su ID.
     *
     * @param id ID de la persona a buscar
     * @return Optional con la persona si existe, vacío en caso contrario
     */
    Optional<Persona> getPersonaById(Long id);

    /**
     * Busca una persona por su documento de identidad.
     *
     * @param documentoIdentidad Documento de identidad de la persona
     * @return Optional con la persona si existe, vacío en caso contrario
     */
    Optional<Persona> getPersonaByDocumentoIdentidad(String documentoIdentidad);

    /**
     * Guarda una nueva persona.
     *
     * @param persona Persona a guardar
     * @return Persona guardada con su ID asignado
     */
    Persona savePersona(Persona persona);

    /**
     * Actualiza una persona existente.
     *
     * @param id ID de la persona a actualizar
     * @param personaDetails Nuevos datos de la persona
     * @return Persona actualizada
     * @throws RuntimeException si la persona no existe
     */
    Persona updatePersona(Long id, Persona personaDetails);

    /**
     * Elimina una persona por su ID.
     *
     * @param id ID de la persona a eliminar
     * @throws RuntimeException si la persona no existe
     */
    void deletePersona(Long id);

    /**
     * Busca personas cuyo nombre contenga el texto especificado.
     *
     * @param nameFragment Texto a buscar en el nombre
     * @return Lista de personas que coinciden con la búsqueda
     */
    List<Persona> searchPersonasByName(String nameFragment);
}


package proyeto1.model.response;

import proyeto1.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para enviar información de personas a los clientes.
 *
 * Esta clase representa la estructura de datos que se enviará como respuesta
 * en las peticiones relacionadas con personas. Separar la entidad de base de datos
 * de los objetos de transferencia nos permite:
 *
 * - Desacoplar el modelo de base de datos de la API pública
 * - Controlar qué campos son visibles para los clientes
 * - Optimizar la transferencia de datos (serializando solo lo necesario)
 *
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;
    private String direccion;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    /**
     * Método estático para convertir una entidad Persona a un objeto PersonaResponse.
     * Este patrón facilita la conversión entre la entidad y el DTO.
     *
     * @param persona Entidad persona a convertir
     * @return Objeto PersonaResponse con los datos de la persona
     */
    public static PersonaResponse fromEntity(Persona persona) {
        return PersonaResponse.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .apellido(persona.getApellido())
                .documentoIdentidad(persona.getDocumentoIdentidad())
                .fechaNacimiento(persona.getFechaNacimiento())
                .correo(persona.getCorreo())
                .telefono(persona.getTelefono())
                .direccion(persona.getDireccion())
                .creadoEn(persona.getCreadoEn())
                .actualizadoEn(persona.getActualizadoEn())
                .build();
    }
}


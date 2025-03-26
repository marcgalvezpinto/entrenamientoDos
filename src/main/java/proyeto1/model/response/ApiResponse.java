package proyeto1.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para enviar respuestas genéricas de la API, parametrizado con cualquier tipo de datos (T).
 *
 * Esta clase es utilizada para enviar tanto respuestas de éxito como de error.
 *
 * @param <T> Tipo de datos que se enviará en la respuesta.
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    /**
     * Indicador de si la respuesta fue exitosa o no.
     */
    @Builder.Default
    private boolean success = true;

    /**
     * Mensaje adicional con información de la respuesta.
     */
    private String message;

    /**
     * Datos de la respuesta, puede ser cualquier tipo.
     */
    private T data;

    /**
     * Fecha y hora en la que se generó la respuesta.
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * Código de error (si existe) en caso de que la respuesta no sea exitosa.
     */
    private String errorCode;

    /**
     * Método estático para respuesta exitosa sin datos.
     *
     * @param message Mensaje de éxito.
     * @return ApiResponse con éxito.
     */
    public static ApiResponse<Void> success(String message) {
        return ApiResponse.<Void>builder()
                .success(true)
                .message(message)
                .build();
    }

    /**
     * Método estático para respuesta exitosa con datos.
     *
     * @param message Mensaje de éxito.
     * @param data Datos a incluir en la respuesta.
     * @param <T> Tipo de datos.
     * @return ApiResponse con éxito y datos.
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Método estático para respuesta con error.
     *
     * @param message Mensaje de error.
     * @param errorCode Código de error.
     * @param <T> Tipo de datos (se puede usar Void si no hay datos).
     * @return ApiResponse con error.
     */
    public static <T> ApiResponse<T> error(String message, String errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .build();
    }

    /**
     * Método estático para respuesta exitosa con un objeto PersonaResponse.
     *
     * @param message Mensaje de éxito.
     * @param personaResponse Objeto PersonaResponse con los datos de la persona.
     * @return ApiResponse con éxito y datos de persona.
     */
    public static ApiResponse<PersonaResponse> successPersona(String message, PersonaResponse personaResponse) {
        return ApiResponse.<PersonaResponse>builder()
                .success(true)
                .message(message)
                .data(personaResponse)
                .build();
    }

    /**
     * Método estático para respuesta con error específico para la clase PersonaResponse.
     *
     * @param message Mensaje de error.
     * @param errorCode Código de error.
     * @return ApiResponse con error.
     */
    public static ApiResponse<PersonaResponse> errorPersona(String message, String errorCode) {
        return ApiResponse.<PersonaResponse>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .build();
    }
}


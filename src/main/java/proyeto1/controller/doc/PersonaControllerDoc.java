package proyeto1.controller.doc;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import proyeto1.model.Persona;
import proyeto1.model.response.PersonaResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Personas", description = "API para gestionar personas en el catálogo")
public interface PersonaControllerDoc {

    @Operation(
            summary = "Obtener todas las personas",
            description = "Retorna una lista de todas las personas registradas"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Personas encontradas correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonaResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta",
                                    value = """
                                    {
                                        "success": true,
                                        "message": "Personas encontradas correctamente",
                                        "data": [
                                            {
                                                "id": 1,
                                                "name": "Juan Pérez",
                                                "age": 30,
                                                "email": "juan@example.com",
                                                "address": "Calle Ficticia 123"
                                            }
                                        ]
                                    }
                                    """
                            )
                    )
            )
    })
    @GetMapping
    ResponseEntity<proyeto1.model.response.ApiResponse<List<PersonaResponse>>> getAllPersons();

    @Operation(
            summary = "Obtener persona por ID",
            description = "Busca y retorna una persona según su ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Persona encontrada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonaResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta",
                                    value = """
                                    {
                                        "success": true,
                                        "message": "Persona encontrada correctamente",
                                        "data": {
                                            "id": 1,
                                            "name": "Juan Pérez",
                                            "age": 30,
                                            "email": "juan@example.com",
                                            "address": "Calle Ficticia 123"
                                        }
                                    }
                                    """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Persona no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de error",
                                    value = """
                                    {
                                        "success": false,
                                        "message": "No se encontró la persona con ID: 999",
                                        "data": null
                                    }
                                    """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<proyeto1.model.response.ApiResponse<PersonaResponse>> getPersonById(
            @Parameter(description = "ID de la persona", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Crear nueva persona",
            description = "Crea una nueva persona en el sistema"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Persona creada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonaResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta",
                                    value = """
                                    {
                                        "success": true,
                                        "message": "Persona creada correctamente",
                                        "data": {
                                            "id": 11,
                                            "name": "Ana Gómez",
                                            "age": 25,
                                            "email": "ana@example.com",
                                            "address": "Avenida Principal 456"
                                        }
                                    }
                                    """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de error",
                                    value = """
                                    {
                                        "success": false,
                                        "message": "Los datos proporcionados son inválidos",
                                        "data": null
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping
    ResponseEntity<proyeto1.model.response.ApiResponse<PersonaResponse>> createPerson(
            @Parameter(description = "Datos de la persona a crear", required = true, content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(name = "Ejemplo de persona", value = """
                    {
                        "name": "Ana Gómez",
                        "age": 25,
                        "email": "ana@example.com",
                        "address": "Avenida Principal 456"
                    }
                    """))
            )
            @Valid @RequestBody Persona person
    );

    @Operation(
            summary = "Actualizar persona",
            description = "Actualiza los datos de una persona existente"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Persona actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonaResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta",
                                    value = """
                                    {
                                        "success": true,
                                        "message": "Persona actualizada correctamente",
                                        "data": {
                                            "id": 1,
                                            "name": "Juan Pérez",
                                            "age": 31,
                                            "email": "juanperez@example.com",
                                            "address": "Calle Actualizada 123"
                                        }
                                    }
                                    """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de error",
                                    value = """
                                    {
                                        "success": false,
                                        "message": "El email ya está en uso",
                                        "data": null
                                    }
                                    """
                            )
                    )
            )
    })
    @PutMapping("/{id}")
    ResponseEntity<proyeto1.model.response.ApiResponse<PersonaResponse>> updatePerson(
            @Parameter(description = "ID de la persona", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos de la persona", required = true, content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(name = "Ejemplo de actualización", value = """
                    {
                        "name": "Juan Pérez",
                        "age": 31,
                        "email": "juanperez@example.com",
                        "address": "Calle Actualizada 123"
                    }
                    """))
            )
            @Valid @RequestBody Persona personDetails
    );

    @Operation(
            summary = "Eliminar persona",
            description = "Elimina una persona del sistema"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Persona eliminada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta",
                                    value = """
                                    {
                                        "success": true,
                                        "message": "Persona eliminada correctamente",
                                        "data": null
                                    }
                                    """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Persona no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de error",
                                    value = """
                                    {
                                        "success": false,
                                        "message": "No se encontró la persona con ID: 999",
                                        "data": null
                                    }
                                    """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<proyeto1.model.response.ApiResponse<Void>> deletePerson(
            @Parameter(description = "ID de la persona", required = true, example = "1")
            @PathVariable Long id
    );
}

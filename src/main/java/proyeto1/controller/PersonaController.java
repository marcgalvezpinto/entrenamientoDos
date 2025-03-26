package proyeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyeto1.model.Persona;
import proyeto1.model.response.PersonaResponse;
import proyeto1.model.response.ApiResponse;
import proyeto1.service.PersonaService;
import proyeto1.controller.doc.PersonaControllerDoc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController implements PersonaControllerDoc {

    @Autowired
    private PersonaService personaService;  // Servicio que maneja la lógica de negocio

    /**
     * Obtiene todas las personas registradas.
     */
    @Override
    public ResponseEntity<ApiResponse<List<PersonaResponse>>> getAllPersons() {
        List<PersonaResponse> personas = personaService.getAllPersons();
        return ResponseEntity.ok(new ApiResponse<>(true, "Personas encontradas correctamente", personas));
    }

    /**
     * Obtiene una persona por ID.
     */
    @Override
    public ResponseEntity<ApiResponse<PersonaResponse>> getPersonById(@PathVariable Long id) {
        PersonaResponse persona = personaService.getPersonById(id);
        if (persona != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Persona encontrada correctamente", persona));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Persona no encontrada", null));
        }
    }

    /**
     * Crea una nueva persona en el sistema.
     */
    @Override
    public ResponseEntity<ApiResponse<PersonaResponse>> createPerson(@Valid @RequestBody Persona person) {
        PersonaResponse createdPerson = personaService.createPerson(person);
        return ResponseEntity.status(201).body(new ApiResponse<>(true, "Persona creada correctamente", createdPerson));
    }

    /**
     * Actualiza los datos de una persona existente.
     */
    @Override
    public ResponseEntity<ApiResponse<PersonaResponse>> updatePerson(@PathVariable Long id, @Valid @RequestBody Persona personDetails) {
        PersonaResponse updatedPerson = personaService.updatePerson(id, personDetails);
        if (updatedPerson != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Persona actualizada correctamente", updatedPerson));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse<>(false, "Datos inválidos", null));
        }
    }

    /**
     * Elimina una persona del sistema.
     */
    @Override
    public ResponseEntity<ApiResponse<Void>> deletePerson(@PathVariable Long id) {
        boolean isDeleted = personaService.deletePerson(id);
        if (isDeleted) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Persona eliminada correctamente", null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Persona no encontrada", null));
        }
    }
}

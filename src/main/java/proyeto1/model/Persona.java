package proyeto1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "personas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false)
    private String nombre;


    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres")
    @Column(nullable = false)
    private String apellido;


    @NotBlank(message = "El documento de identificación es obligatorio")
    @Column(unique = true, nullable = false)
    private String documentoIdentidad;


    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Column(nullable = false)
    private LocalDate fechaNacimiento;


    @NotBlank(message = "El correo electrónico es obligatorio")
    @Column(nullable = false, unique = true)
    private String correo;


    @Size(max = 20, message = "El número de teléfono debe tener como máximo 20 caracteres")
    private String telefono;


    private String direccion;

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime creadoEn = LocalDateTime.now();


    @Column(name = "updated_at")
    private LocalDateTime actualizadoEn;


    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }
}


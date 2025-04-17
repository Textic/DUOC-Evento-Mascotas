package com.pets.events.evento_mascotas.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "eventos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "El ID debe ser un número positivo.")
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Min(value = 1, message = "El nombre debe tener al menos 1 carácter.")
    @Max(value = 50, message = "El nombre no puede tener más de 50 caracteres.")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "La fecha no puede estar vacía.")
    @Size(max = 20, message = "La fecha no puede tener más de 20 caracteres.")
    private String fecha;

    @Column(nullable = false)
    @NotBlank(message = "La ubicación no puede estar vacía.")
    @Min(value = 1, message = "La ubicación debe tener al menos 1 carácter.")
    @Max(value = 100, message = "La ubicación no puede tener más de 100 caracteres.")
    private String ubicacion;

    @Column(nullable = false)
    @NotBlank(message = "La descripción no puede estar vacía.")
    @Min(value = 1, message = "La descripción debe tener al menos 1 carácter.")
    @Max(value = 255, message = "La descripción no puede tener más de 255 caracteres.")
    private String descripcion;
}

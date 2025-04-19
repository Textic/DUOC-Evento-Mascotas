package com.pets.events.evento_mascotas.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "El ID debe ser un número positivo.")
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "La fecha no puede estar vacía.")
    @Size(max = 20, message = "La fecha no puede tener más de 20 caracteres.")
    private String fecha;

    @Column(nullable = false)
    @NotBlank(message = "La ubicación no puede estar vacía.")
    @Size(min = 1, max = 100, message = "La ubicación debe tener entre 1 y 100 caracteres.")
    private String ubicacion;

    @Column(nullable = false)
    @NotBlank(message = "La descripción no puede estar vacía.")
    @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres.")
    private String descripcion;
}

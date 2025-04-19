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
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres.")
    private String nombre;

    @Column(nullable = false)
    @Positive(message = "La edad debe ser un número positivo.")
    @Max(value = 100, message = "La edad no puede ser mayor a 100 años.")
    private int edad;

    @Column(nullable = false)
    @NotBlank(message = "El tipo no puede estar vacío.")
    @Size(min = 1, max = 30, message = "La especie debe tener entre 1 y 30 caracteres.")
    private String tipo;

    @Column(nullable = false)
    @NotBlank(message = "La raza no puede estar vacía.")
    @Size(min = 1, max = 30, message = "La raza debe tener entre 1 y 30 caracteres.")
    private String raza;

    @Column(nullable = false)
    @NotBlank(message = "El dueño no puede estar vacío.")
    @Size(max = 50, message = "El nombre del dueño no puede tener más de 50 caracteres.")
    private String duenno;
}

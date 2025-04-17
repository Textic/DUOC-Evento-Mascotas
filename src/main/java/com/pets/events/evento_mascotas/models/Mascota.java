package com.pets.events.evento_mascotas.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "mascotas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
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
    @Positive(message = "La edad debe ser un número positivo.")
    @Max(value = 100, message = "La edad no puede ser mayor a 100 años.")
    private int edad;

    @Column(nullable = false)
    @NotBlank(message = "El tipo no puede estar vacío.")
    @Min(value = 1, message = "La especie debe tener al menos 1 carácter.")
    @Max(value = 30, message = "La especie no puede tener más de 30 caracteres.")
    private String tipo;

    @Column(nullable = false)
    @NotBlank(message = "La raza no puede estar vacía.")
    @Min(value = 1, message = "La raza debe tener al menos 1 carácter.")
    @Max(value = 30, message = "La raza no puede tener más de 30 caracteres.")
    private String raza;

    @Column(nullable = false)
    @NotBlank(message = "El dueño no puede estar vacío.")
    @Size(max = 50, message = "El nombre del dueño no puede tener más de 50 caracteres.")
    private String duenno;
}

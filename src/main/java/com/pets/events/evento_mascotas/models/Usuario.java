package com.pets.events.evento_mascotas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
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
    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "El correo debe tener un formato válido.")
    private String correo;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Min(value = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "El rol no puede estar vacío.")
    @Min(value = 1, message = "El rol debe tener al menos 1 carácter.")
    @Max(value = 20, message = "El rol no puede tener más de 20 caracteres.")
    private String rol; // "duenno" o "conductor"
}

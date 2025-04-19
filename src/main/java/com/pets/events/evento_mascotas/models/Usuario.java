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
    private int id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    @Size(min = 4, max = 30, message = "El nombre de usuario debe tener entre 4 y 30 caracteres.")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres.")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "El correo debe ser válido.")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres.")
    private String correo;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres.")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "El rol no puede estar vacío.")
    @Size(min = 1, max = 20, message = "El rol debe tener entre 1 y 20 caracteres.")
    private String rol; // "duenno" o "conductor"
}

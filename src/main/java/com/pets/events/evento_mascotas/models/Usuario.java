package com.pets.events.evento_mascotas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String password;
    private String rol; // "duenno" o "conductor"
}

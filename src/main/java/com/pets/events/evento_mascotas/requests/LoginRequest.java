package com.pets.events.evento_mascotas.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String password;
}

package com.pets.events.evento_mascotas.controllers;

import com.pets.events.evento_mascotas.models.Usuario;
import com.pets.events.evento_mascotas.services.UsuarioService;
import com.pets.events.evento_mascotas.requests.LoginRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> allUsuarios() {
        return usuarioService.allUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario findUsuario(@PathVariable int id) {
        return usuarioService.findUsuario(id);
    }

    @GetMapping("/login")
    public Usuario login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.login(loginRequest.getCorreo(), loginRequest.getPassword());
    }
}
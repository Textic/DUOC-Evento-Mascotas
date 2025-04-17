package com.pets.events.evento_mascotas.controllers;

import com.pets.events.evento_mascotas.models.Usuario;
import com.pets.events.evento_mascotas.services.UsuarioService;
import com.pets.events.evento_mascotas.requests.LoginRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
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
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        return usuarioService.findUsuario(id);
    }

    @GetMapping("/login")
    public Usuario login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.login(loginRequest.getCorreo(), loginRequest.getPassword());
    }

    @PostMapping
    public Usuario addUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public Usuario deleteUsuario(@PathVariable int id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        Usuario usuario = usuarioService.deleteUsuario(id);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el usuario con ID: " + id);
        }
        return usuario;
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable int id, @Valid @RequestBody Usuario usuario) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo del usuario no puede estar vacío.");
        }
        return usuarioService.updateUsuario(id, usuario);
    }
}
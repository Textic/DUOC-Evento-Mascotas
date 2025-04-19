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
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> allUsuarios() {
        log.info("GET /usuarios - Listando todos los usuarios");
        return usuarioService.allUsuarios();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        log.info("POST /usuarios/login - Intentando iniciar sesión con correo: {}", loginRequest.getCorreo());
        String token = usuarioService.login(loginRequest.getCorreo(), loginRequest.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Usuario findUsuario(@PathVariable int id) {
        log.info("GET /usuarios/{} - Buscando usuario con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        return usuarioService.findUsuario(id);
    }

    @PostMapping
    public Usuario addUsuario(@Valid @RequestBody Usuario usuario) {
        log.info("POST /usuarios - Agregando nuevo usuario: {}", usuario.getCorreo());
        return usuarioService.addUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public Usuario deleteUsuario(@PathVariable int id) {
        log.info("DELETE /usuarios/{} - Eliminando usuario con ID: {}", id, id);
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
        log.info("PUT /usuarios/{} - Actualizando usuario con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo del usuario no puede estar vacío.");
        }
        return usuarioService.updateUsuario(id, usuario);
    }
}
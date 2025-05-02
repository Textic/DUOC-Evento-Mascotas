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

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    public CollectionModel<EntityModel<Usuario>> allUsuarios() {
        log.info("GET /usuarios - Listando todos los usuarios");
        List<EntityModel<Usuario>> usuarios = usuarioService.allUsuarios().stream()
            .map(usuario -> EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).findUsuario(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).allUsuarios()).withRel("usuarios")
            ))
            .toList();
        return CollectionModel.of(usuarios,
            linkTo(methodOn(UsuarioController.class).allUsuarios()).withSelfRel()
        );
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
    public EntityModel<Usuario> findUsuario(@PathVariable int id) {
        log.info("GET /usuarios/{} - Buscando usuario con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        Usuario usuario = usuarioService.findUsuario(id);
        return EntityModel.of(usuario,
            linkTo(methodOn(UsuarioController.class).findUsuario(id)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).allUsuarios()).withRel("usuarios"),
            linkTo(methodOn(UsuarioController.class).deleteUsuario(id)).withRel("delete"),
            linkTo(methodOn(UsuarioController.class).updateUsuario(id, usuario)).withRel("update")
        );
    }

    @PostMapping
    public EntityModel<Usuario> addUsuario(@Valid @RequestBody Usuario usuario) {
        log.info("POST /usuarios - Agregando nuevo usuario: {}", usuario.getCorreo());
        Usuario nuevo = usuarioService.addUsuario(usuario);
        return EntityModel.of(nuevo,
            linkTo(methodOn(UsuarioController.class).findUsuario(nuevo.getId())).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).allUsuarios()).withRel("usuarios")
        );
    }

    @DeleteMapping("/{id}")
    public EntityModel<Usuario> deleteUsuario(@PathVariable int id) {
        log.info("DELETE /usuarios/{} - Eliminando usuario con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        Usuario usuario = usuarioService.deleteUsuario(id);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el usuario con ID: " + id);
        }
        return EntityModel.of(usuario,
            linkTo(methodOn(UsuarioController.class).allUsuarios()).withRel("usuarios")
        );
    }

    @PutMapping("/{id}")
    public EntityModel<Usuario> updateUsuario(@PathVariable int id, @Valid @RequestBody Usuario usuario) {
        log.info("PUT /usuarios/{} - Actualizando usuario con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo del usuario no puede estar vacío.");
        }
        Usuario actualizado = usuarioService.updateUsuario(id, usuario);
        return EntityModel.of(actualizado,
            linkTo(methodOn(UsuarioController.class).findUsuario(id)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).allUsuarios()).withRel("usuarios")
        );
    }
}
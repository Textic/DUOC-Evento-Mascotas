package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Usuario;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.pets.events.evento_mascotas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> allUsuarios() {
        return repo.findAll();
    }

    public Usuario findUsuario(int id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el usuario con ID: " + id));
    }
    
    public Usuario login(String correo, String password) {
        Usuario usuario = repo.findByCorreo(correo);
        if (usuario == null || !usuario.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
        return usuario;
    }
}
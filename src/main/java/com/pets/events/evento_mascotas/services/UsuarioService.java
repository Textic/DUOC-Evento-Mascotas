package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario(1, "Camila Duarte", "camila@dueno.com", "abc123", "duenno"));
        usuarios.add(new Usuario(2, "Marco Torres", "marco@dueno.com", "pass456", "duenno"));
        usuarios.add(new Usuario(3, "Lucia Reyes", "lucia@conductor.com", "drive789", "conductor"));
        usuarios.add(new Usuario(4, "Jorge Salinas", "jorge@conductor.com", "ruta321", "conductor"));
        usuarios.add(new Usuario(5, "Ana Prado", "ana@dueno.com", "luna321", "duenno"));
        usuarios.add(new Usuario(6, "Oscar Vidal", "oscar@dueno.com", "mascota007", "duenno"));
        usuarios.add(new Usuario(7, "Claudia Ríos", "claudia@conductor.com", "petmove", "conductor"));
        usuarios.add(new Usuario(8, "Tomás Figueroa", "tomas@dueno.com", "travelpet", "duenno"));
    }

    public List<Usuario> allUsuarios() {
        return usuarios;
    }

    public Usuario findUsuario(int id) {
		Optional<Usuario> optionalUsuario = usuarios.stream().filter(u -> u.getId() == id).findFirst();
		if (optionalUsuario.isPresent()) {
			return optionalUsuario.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el usuario con ID: " + id);
		}
    }

    public Usuario login(String correo, String password) {
		Optional<Usuario> optionalUsuario = usuarios.stream()
		.filter(u -> u.getCorreo().equalsIgnoreCase(correo) && u.getPassword().equals(password))
		.findFirst();
		if (optionalUsuario.isPresent()) {
			return optionalUsuario.get();
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
		}
    }
}
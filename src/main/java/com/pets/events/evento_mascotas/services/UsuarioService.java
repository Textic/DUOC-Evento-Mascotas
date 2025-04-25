package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Usuario;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.pets.events.evento_mascotas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.repo = usuarioRepository;
    }

    @Value("${jwt.secret}")
    private String jwtSecret;

    public List<Usuario> allUsuarios() {
        return repo.findAll();
    }

    public Usuario findUsuario(int id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el usuario con ID: " + id));
    }
    
    public String login(String correo, String password) {
        Usuario usuario = repo.findByCorreo(correo);
        if (usuario == null || !usuario.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        // SecretKey key = Jwts.SIG.HS256.key().build();
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .subject(usuario.getId() + "")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día de expiración
                .signWith(key)
                .compact();

        return jwt;
    }

    public Usuario addUsuario(Usuario usuario) {
        if (usuario.getId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no debe contener un ID.");
        }
        return repo.save(usuario);
    }

    public Usuario updateUsuario(int id, Usuario usuario) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el usuario con ID: " + id);
        }
        usuario.setId(id);
        return repo.save(usuario);
    }

    public Usuario deleteUsuario(int id) {
        Usuario usuario = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el usuario con ID: " + id));
        repo.deleteById(id);
        return usuario;
    }
}
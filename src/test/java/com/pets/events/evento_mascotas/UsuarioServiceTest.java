package com.pets.events.evento_mascotas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pets.events.evento_mascotas.models.Usuario;
import com.pets.events.evento_mascotas.repository.UsuarioRepository;
import com.pets.events.evento_mascotas.services.UsuarioService;
import org.springframework.web.server.ResponseStatusException;

public class UsuarioServiceTest {
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    public void testAllUsuarios() {
        Usuario u1 = new Usuario(1, "user1", "Juan", "juan@mail.com", "pass1", "Admin");
        Usuario u2 = new Usuario(2, "user2", "Maria", "maria@mail.com", "pass2", "Usuario");
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));
        List<Usuario> result = usuarioService.allUsuarios();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindUsuarioFound() {
        Usuario u = new Usuario(1, "user1", "Juan", "juan@mail.com", "pass1", "Admin");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(u));
        Usuario result = usuarioService.findUsuario(1);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    public void testFindUsuarioNotFound() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> usuarioService.findUsuario(99));
    }

    @Test
    public void testAddUsuario() {
        Usuario u = new Usuario(0, "user3", "Ana", "ana@mail.com", "pass3", "Usuario");
        Usuario saved = new Usuario(10, "user3", "Ana", "ana@mail.com", "pass3", "Usuario");
        when(usuarioRepository.save(u)).thenReturn(saved);
        Usuario result = usuarioService.addUsuario(u);
        assertEquals(10, result.getId());
    }

    @Test
    public void testAddUsuarioWithIdShouldFail() {
        Usuario u = new Usuario(5, "user3", "Ana", "ana@mail.com", "pass3", "Usuario");
        assertThrows(ResponseStatusException.class, () -> usuarioService.addUsuario(u));
    }

    @Test
    public void testDeleteUsuarioFound() {
        Usuario u = new Usuario(2, "user2", "Maria", "maria@mail.com", "pass2", "Usuario");
        when(usuarioRepository.findById(2)).thenReturn(Optional.of(u));
        Usuario deleted = usuarioService.deleteUsuario(2);
        assertEquals("Maria", deleted.getNombre());
        verify(usuarioRepository).deleteById(2);
    }

    @Test
    public void testDeleteUsuarioNotFound() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> usuarioService.deleteUsuario(99));
    }

    @Test
    public void testUpdateUsuarioFound() {
        Usuario u = new Usuario(0, "user4", "Pedro", "pedro@mail.com", "pass4", "Usuario");
        when(usuarioRepository.existsById(3)).thenReturn(true);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(i -> {
            Usuario us = i.getArgument(0);
            return new Usuario(us.getId(), us.getUsername(), us.getNombre(), us.getCorreo(), us.getPassword(), us.getRol());
        });
        Usuario result = usuarioService.updateUsuario(3, u);
        assertEquals(3, result.getId());
        assertEquals("Pedro", result.getNombre());
    }

    @Test
    public void testUpdateUsuarioNotFound() {
        Usuario u = new Usuario(0, "user4", "Pedro", "pedro@mail.com", "pass4", "Usuario");
        when(usuarioRepository.existsById(99)).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> usuarioService.updateUsuario(99, u));
    }
}

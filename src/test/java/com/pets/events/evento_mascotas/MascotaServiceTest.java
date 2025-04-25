package com.pets.events.evento_mascotas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pets.events.evento_mascotas.models.Mascota;
import com.pets.events.evento_mascotas.repository.MascotaRepository;
import com.pets.events.evento_mascotas.services.MascotaService;
import org.springframework.web.server.ResponseStatusException;

public class MascotaServiceTest {
    private MascotaService mascotaService;
    private MascotaRepository mascotaRepository;

    @BeforeEach
    public void setUp() {
        mascotaRepository = mock(MascotaRepository.class);
        mascotaService = new MascotaService(mascotaRepository);
    }

    @Test
    public void testAllMascotas() {
        Mascota m1 = new Mascota(1, "Rocky", 3, "Perro", "Bulldog", "Juan");
        Mascota m2 = new Mascota(2, "Misha", 5, "Gato", "Siames", "Maria");
        when(mascotaRepository.findAll()).thenReturn(Arrays.asList(m1, m2));
        List<Mascota> result = mascotaService.allMascotas();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindMascotaFound() {
        Mascota m = new Mascota(1, "Rocky", 3, "Perro", "Bulldog", "Juan");
        when(mascotaRepository.findById(1)).thenReturn(Optional.of(m));
        Mascota result = mascotaService.findMascota(1);
        assertEquals("Rocky", result.getNombre());
    }

    @Test
    public void testFindMascotaNotFound() {
        when(mascotaRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> mascotaService.findMascota(99));
    }

    @Test
    public void testAddMascota() {
        Mascota m = new Mascota(0, "Luna", 2, "Gato", "Angora", "Ana");
        Mascota saved = new Mascota(10, "Luna", 2, "Gato", "Angora", "Ana");
        when(mascotaRepository.save(m)).thenReturn(saved);
        Mascota result = mascotaService.addMascota(m);
        assertEquals(10, result.getId());
    }

    @Test
    public void testAddMascotaWithIdShouldFail() {
        Mascota m = new Mascota(5, "Luna", 2, "Gato", "Angora", "Ana");
        assertThrows(ResponseStatusException.class, () -> mascotaService.addMascota(m));
    }

    @Test
    public void testDeleteMascotaFound() {
        Mascota m = new Mascota(2, "Misha", 5, "Gato", "Siames", "Maria");
        when(mascotaRepository.findById(2)).thenReturn(Optional.of(m));
        Mascota deleted = mascotaService.deleteMascota(2);
        assertEquals("Misha", deleted.getNombre());
        verify(mascotaRepository).deleteById(2);
    }

    @Test
    public void testDeleteMascotaNotFound() {
        when(mascotaRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> mascotaService.deleteMascota(99));
    }

    @Test
    public void testUpdateMascotaFound() {
        Mascota m = new Mascota(0, "Kiwi", 7, "Perro", "Poodle", "Emilia");
        when(mascotaRepository.existsById(3)).thenReturn(true);
        when(mascotaRepository.save(any(Mascota.class))).thenAnswer(i -> {
            Mascota ma = i.getArgument(0);
            return new Mascota(ma.getId(), ma.getNombre(), ma.getEdad(), ma.getTipo(), ma.getRaza(), ma.getDuenno());
        });
        Mascota result = mascotaService.updateMascota(3, m);
        assertEquals(3, result.getId());
        assertEquals("Kiwi", result.getNombre());
    }

    @Test
    public void testUpdateMascotaNotFound() {
        Mascota m = new Mascota(0, "No existe", 1, "Perro", "Quiltro", "Nadie");
        when(mascotaRepository.existsById(99)).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> mascotaService.updateMascota(99, m));
    }

    @Test
    public void testExistsById() {
        when(mascotaRepository.existsById(1)).thenReturn(true);
        assertTrue(mascotaService.existsById(1));
        when(mascotaRepository.existsById(2)).thenReturn(false);
        assertFalse(mascotaService.existsById(2));
    }
}

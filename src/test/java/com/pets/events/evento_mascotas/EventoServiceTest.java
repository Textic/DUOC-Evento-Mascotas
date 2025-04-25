package com.pets.events.evento_mascotas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import com.pets.events.evento_mascotas.models.Evento;
import com.pets.events.evento_mascotas.repository.EventoRepository;
import com.pets.events.evento_mascotas.services.EventoService;

public class EventoServiceTest {
	private EventoService eventoService;
	private EventoRepository eventoRepository;

	@BeforeEach
	public void setUp() {
		eventoRepository = mock(EventoRepository.class);
		eventoService = new EventoService(eventoRepository);
	}

	@Test
	public void testGetAllEventos() {
		Evento evento1 = new Evento(
			1,
			"Evento de prueba",
			"04-04-2025",
			"Ubicacion de prueba",
			"Descripcion de prueba"
			);
		Evento evento2 = new Evento(
			2,
			"Evento de prueba 2",
			"05-05-2025",
			"Ubicacion de prueba 2",
			"Descripcion de prueba 2"
			);
		
		when(eventoRepository.findAll()).thenReturn(Arrays.asList(evento1, evento2));

		// este da error porque no se puede usar Sort en el mock de findAll
		// when(eventoRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(evento1, evento2));

		List<Evento> result = eventoService.allEventos();

		assertEquals(2, result.size());
		assertEquals("Evento de prueba", result.get(0).getNombre());
		assertEquals("Evento de prueba 2", result.get(1).getNombre());
	}

	@Test
	public void testFindEventoByIdFound() {
		Evento evento = new Evento(1, "Evento Test", "01-01-2025", "Lugar", "Descripcion");
		when(eventoRepository.findById(1)).thenReturn(java.util.Optional.of(evento));
		Evento result = eventoService.findEvento(1);
		assertEquals("Evento Test", result.getNombre());
	}

	@Test
	public void testFindEventoByIdNotFound() {
		when(eventoRepository.findById(99)).thenReturn(java.util.Optional.empty());
		org.junit.jupiter.api.Assertions.assertThrows(org.springframework.web.server.ResponseStatusException.class, () -> {
			eventoService.findEvento(99);
		});
	}

	@Test
	public void testAddEvento() {
		Evento evento = new Evento(0, "Nuevo Evento", "10-10-2025", "Lugar", "Descripcion");
		Evento saved = new Evento(5, "Nuevo Evento", "10-10-2025", "Lugar", "Descripcion");
		when(eventoRepository.save(evento)).thenReturn(saved);
		Evento result = eventoService.addEvento(evento);
		assertEquals(5, result.getId());
	}

	@Test
	public void testAddEventoWithIdShouldFail() {
		Evento evento = new Evento(10, "Evento con ID", "10-10-2025", "Lugar", "Descripcion");
		org.junit.jupiter.api.Assertions.assertThrows(org.springframework.web.server.ResponseStatusException.class, () -> {
			eventoService.addEvento(evento);
		});
	}

	@Test
	public void testDeleteEventoFound() {
		Evento evento = new Evento(2, "Evento Borrar", "01-01-2025", "Lugar", "Descripcion");
		when(eventoRepository.findById(2)).thenReturn(java.util.Optional.of(evento));
		Evento deleted = eventoService.deleteEvento(2);
		assertEquals("Evento Borrar", deleted.getNombre());
		verify(eventoRepository).deleteById(2);
	}

	@Test
	public void testDeleteEventoNotFound() {
		when(eventoRepository.findById(99)).thenReturn(java.util.Optional.empty());
		org.junit.jupiter.api.Assertions.assertThrows(org.springframework.web.server.ResponseStatusException.class, () -> {
			eventoService.deleteEvento(99);
		});
	}

	@Test
	public void testUpdateEventoFound() {
		Evento evento = new Evento(0, "Actualizado", "01-01-2025", "Lugar", "Descripcion");
		when(eventoRepository.existsById(3)).thenReturn(true);
		when(eventoRepository.save(any(Evento.class))).thenAnswer(i -> {
			Evento e = i.getArgument(0);
			return new Evento(e.getId(), e.getNombre(), e.getFecha(), e.getUbicacion(), e.getDescripcion());
		});
		Evento result = eventoService.updateEvento(3, evento);
		assertEquals(3, result.getId());
		assertEquals("Actualizado", result.getNombre());
	}

	@Test
	public void testUpdateEventoNotFound() {
		Evento evento = new Evento(0, "No existe", "01-01-2025", "Lugar", "Descripcion");
		when(eventoRepository.existsById(99)).thenReturn(false);
		org.junit.jupiter.api.Assertions.assertThrows(org.springframework.web.server.ResponseStatusException.class, () -> {
			eventoService.updateEvento(99, evento);
		});
	}
}

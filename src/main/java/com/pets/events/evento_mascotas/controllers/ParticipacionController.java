package com.pets.events.evento_mascotas.controllers;

import java.util.List;
import com.pets.events.evento_mascotas.models.Participacion;
import com.pets.events.evento_mascotas.services.ParticipacionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/participaciones")
public class ParticipacionController {
	private final ParticipacionService participacionService;

	public ParticipacionController(ParticipacionService participacionService) {
		this.participacionService = participacionService;
	}

	@GetMapping
	public List<Participacion> allParticipaciones() {
		return participacionService.allParticipaciones();
	}

	@GetMapping("/{id}")
	public Participacion findParticipacion(@PathVariable int id) {
		return participacionService.findParticipacion(id);
	}

	@DeleteMapping("/{id}")
	public Participacion deleteParticipacion(@PathVariable int id) {
		Participacion participacion = participacionService.findParticipacion(id);
		boolean deleted = participacionService.deleteParticipacion(id);
		if (!deleted) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo borrar la participacion con ID: " + id);
		}
		return participacion;
	}

	@PostMapping
	public Participacion addParticipacion(@RequestBody Participacion participacion) {
		return participacionService.addParticipacion(participacion);
	}
}

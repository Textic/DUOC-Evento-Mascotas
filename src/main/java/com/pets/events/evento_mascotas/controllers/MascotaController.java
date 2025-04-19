package com.pets.events.evento_mascotas.controllers;

import java.util.List;
import com.pets.events.evento_mascotas.models.Mascota;
import com.pets.events.evento_mascotas.services.MascotaService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mascotas")
public class MascotaController {
	private final MascotaService mascotaService;

	public MascotaController(MascotaService mascotaService) {
		this.mascotaService = mascotaService;
	}

	@GetMapping
	public List<Mascota> allMascotas() {
		log.info("GET /mascotas - Listando todas las mascotas");
		return mascotaService.allMascotas();
	}
	
	@GetMapping("/{id}")
	public Mascota findMascota(@PathVariable int id) {
		log.info("GET /mascotas/{} - Buscando mascota con ID: {}", id, id);
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
		}
		return mascotaService.findMascota(id);
	}

	@DeleteMapping("/{id}")
	public Mascota deleteMascota(@PathVariable int id) {
		log.info("DELETE /mascotas/{} - Eliminando mascota con ID: {}", id, id);
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
		}
		Mascota mascota = mascotaService.deleteMascota(id);
		if (mascota == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar la mascota con ID: " + id);
		}
		return mascota;
	}

	@PostMapping
	public Mascota addMascota(@Valid @RequestBody Mascota mascota) {
		log.info("POST /mascotas - Agregando nueva mascota: {}", mascota.getNombre());
		return mascotaService.addMascota(mascota);
	}

	@PutMapping("/{id}")
	public Mascota updateMascota(@PathVariable int id, @Valid @RequestBody Mascota mascota) {
		log.info("PUT /mascotas/{} - Actualizando mascota con ID: {}", id, id);
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
		}
		if (mascota.getNombre() == null || mascota.getNombre().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la mascota no puede estar vacío.");
		}
		return mascotaService.updateMascota(id, mascota);
	}
}

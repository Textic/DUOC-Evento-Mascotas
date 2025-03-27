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


@RestController
@RequestMapping("/mascotas")
public class MascotaController {
	private final MascotaService mascotaService;

	public MascotaController(MascotaService mascotaService) {
		this.mascotaService = mascotaService;
	}

	@GetMapping
	public List<Mascota> allMascotas() {
		return mascotaService.allMascotas();
	}
	
	@GetMapping("/{id}")
	public Mascota findMascota(@PathVariable int id) {
		return mascotaService.findMascota(id);
	}

	@DeleteMapping("/{id}")
	public Mascota deleteMascota(@PathVariable int id) {
		Mascota mascota = mascotaService.findMascota(id);
		boolean deleted = mascotaService.deleteMascota(id);
		if (!deleted) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo borrar la mascota con ID: " + id);
		}
		return mascota;
	}

	@PostMapping
	public Mascota addMascota(@RequestBody Mascota mascota) {
		return mascotaService.addMascota(mascota);
	}
}

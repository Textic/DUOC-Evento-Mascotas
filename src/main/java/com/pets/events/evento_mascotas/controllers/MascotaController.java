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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/mascotas")
public class MascotaController {
	private final MascotaService mascotaService;

	public MascotaController(MascotaService mascotaService) {
		this.mascotaService = mascotaService;
	}

	@GetMapping
	public CollectionModel<EntityModel<Mascota>> allMascotas() {
		log.info("GET /mascotas - Listando todas las mascotas");
		List<EntityModel<Mascota>> mascotas = mascotaService.allMascotas().stream()
			.map(mascota -> EntityModel.of(mascota,
				linkTo(methodOn(MascotaController.class).findMascota(mascota.getId())).withSelfRel(),
				linkTo(methodOn(MascotaController.class).allMascotas()).withRel("mascotas")
			))
			.toList();
		return CollectionModel.of(mascotas,
			linkTo(methodOn(MascotaController.class).allMascotas()).withSelfRel()
		);
	}
	
	@GetMapping("/{id}")
	public EntityModel<Mascota> findMascota(@PathVariable int id) {
		log.info("GET /mascotas/{} - Buscando mascota con ID: {}", id, id);
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
		}
		Mascota mascota = mascotaService.findMascota(id);
		return EntityModel.of(mascota,
			linkTo(methodOn(MascotaController.class).findMascota(id)).withSelfRel(),
			linkTo(methodOn(MascotaController.class).allMascotas()).withRel("mascotas"),
			linkTo(methodOn(MascotaController.class).deleteMascota(id)).withRel("delete"),
			linkTo(methodOn(MascotaController.class).updateMascota(id, mascota)).withRel("update")
		);
	}

	@DeleteMapping("/{id}")
	public EntityModel<Mascota> deleteMascota(@PathVariable int id) {
		log.info("DELETE /mascotas/{} - Eliminando mascota con ID: {}", id, id);
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
		}
		Mascota mascota = mascotaService.deleteMascota(id);
		if (mascota == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar la mascota con ID: " + id);
		}
		return EntityModel.of(mascota,
			linkTo(methodOn(MascotaController.class).allMascotas()).withRel("mascotas")
		);
	}

	@PostMapping
	public EntityModel<Mascota> addMascota(@Valid @RequestBody Mascota mascota) {
		log.info("POST /mascotas - Agregando nueva mascota: {}", mascota.getNombre());
		Mascota nueva = mascotaService.addMascota(mascota);
		return EntityModel.of(nueva,
			linkTo(methodOn(MascotaController.class).findMascota(nueva.getId())).withSelfRel(),
			linkTo(methodOn(MascotaController.class).allMascotas()).withRel("mascotas")
		);
	}

	@PutMapping("/{id}")
	public EntityModel<Mascota> updateMascota(@PathVariable int id, @Valid @RequestBody Mascota mascota) {
		log.info("PUT /mascotas/{} - Actualizando mascota con ID: {}", id, id);
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
		}
		if (mascota.getNombre() == null || mascota.getNombre().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la mascota no puede estar vacío.");
		}
		Mascota actualizada = mascotaService.updateMascota(id, mascota);
		return EntityModel.of(actualizada,
			linkTo(methodOn(MascotaController.class).findMascota(id)).withSelfRel(),
			linkTo(methodOn(MascotaController.class).allMascotas()).withRel("mascotas")
		);
	}
}

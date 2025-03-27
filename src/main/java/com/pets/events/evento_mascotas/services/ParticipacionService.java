package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Participacion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ParticipacionService {
	private List<Participacion> participaciones = new ArrayList<Participacion>();
	private final MascotaService mascotaService;
	private final EventoService eventoService;

	public ParticipacionService(MascotaService mascotaService, EventoService eventoService) {
		this.mascotaService = mascotaService;
		this.eventoService = eventoService;
		participaciones.add(new Participacion(1, 1, 1, "Agilida"));
		participaciones.add(new Participacion(2, 1, 2, "Agilida"));
		participaciones.add(new Participacion(3, 2, 2, "Bellaza"));
		participaciones.add(new Participacion(4, 2, 2, "Bellaza"));
		participaciones.add(new Participacion(5, 3, 3, "Velocidad"));
		participaciones.add(new Participacion(6, 4, 3, "Velocidad"));
		participaciones.add(new Participacion(7, 4, 3, "Habilidad"));
		participaciones.add(new Participacion(8, 5, 3, "Habilidad"));
	}

	public Participacion addParticipacion(Participacion participacion) {
		if (participacion.getId() != 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no debe contener un ID.");
		}
		if (participacion.getIdMascota() == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la mascota es requerido.");
		}
		if (participacion.getIdEvento() == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del evento es requerido.");
		}
		if (participacion.getCategoria() == null || participacion.getCategoria().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoria de la participacion es requerida.");
		}
		if (!mascotaService.existsById(participacion.getIdMascota())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La mascota con el ID proporcionado no existe.");
		}
		if (!eventoService.existsById(participacion.getIdEvento())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El evento con el ID proporcionado no existe.");
		}
		int maxId = participaciones.stream().mapToInt(Participacion::getId).max().orElse(0); // esto lo hice para que el id sea autoincrementable y no tener problemas con ids repetidos
		participacion.setId(maxId + 1);
		participaciones.add(participacion);
		return participacion;
	}

	public Participacion findParticipacion(int id) {
		Optional<Participacion> optionalParticipacion = participaciones.stream().filter(p -> p.getId() == id).findFirst();
		if (optionalParticipacion.isPresent()) {
			return optionalParticipacion.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar la participacion con ID: " + id);
		}
	}

	public List<Participacion> allParticipaciones() {
		return participaciones;
	}

	public boolean deleteParticipacion(int id) {
		Optional<Participacion> optionalParticipacion = participaciones.stream().filter(p -> p.getId() == id).findFirst();
		if (optionalParticipacion.isPresent()) {
			participaciones.remove(optionalParticipacion.get());
			return true;
		} else {
			return false;
		}
	}
}

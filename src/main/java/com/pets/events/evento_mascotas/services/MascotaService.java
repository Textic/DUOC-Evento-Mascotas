package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Mascota;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class MascotaService {
	private final List<Mascota> mascotas = new ArrayList<>();

	public MascotaService() {
		mascotas.add(new Mascota(1, "Firulais", 3, "Perro", "Labrador", "Juan Perez"));
		mascotas.add(new Mascota(2, "Pelusa", 2, "Gato", "Persa", "Camila Rojas"));
		mascotas.add(new Mascota(3, "Rayo", 4, "Perro", "Border Collie", "Felipe Gonzalez"));
		mascotas.add(new Mascota(4, "Chispita", 1, "Conejo", "Cabeza de Leon", "Valentina Silva"));
		mascotas.add(new Mascota(5, "Manchas", 5, "Perro", "Dalmata", "Ricardo Soto"));
		mascotas.add(new Mascota(6, "Bigotes", 6, "Gato", "Siames", "Lorena Ramirez"));
		mascotas.add(new Mascota(7, "Luna", 3, "Perro", "Poodle", "Ignacio Morales"));
		mascotas.add(new Mascota(8, "Coco", 2, "Erizo", "Africano", "Paula Castillo"));
	}
	
	public Mascota addMascota(Mascota mascota) {
		if (mascota.getId() != 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no debe contener un ID.");
		}
		if (mascota.getNombre() == null || mascota.getNombre() == "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la mascota es requerido.");
		}
		if (mascota.getTipo() == null || mascota.getTipo() == "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de mascota es requerido.");
		}
		if (mascota.getRaza() == null || mascota.getRaza() == "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La raza de la mascota es requerida.");
		}
		if (mascota.getDuenno() == null || mascota.getDuenno() == "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del dueño de la mascota es requerido.");
		}
		int maxId = mascotas.stream().mapToInt(Mascota::getId).max().orElse(0); // esto lo hice para que el id sea autoincrementable y no tener problemas con ids repetidos
		mascota.setId(maxId + 1);
		mascotas.add(mascota);
		return mascota;
	}
	
	public Mascota findMascota(int id) {
		Optional<Mascota> optionalMascota = mascotas.stream().filter(m -> m.getId() == id).findFirst();
		if (optionalMascota.isPresent()) {
			return optionalMascota.get();
		} else {
			return null;
		}
	}
	
	public List<Mascota> allMascotas() {
		return mascotas;
	}
	
	public boolean deleteMascota(int id) {
		Optional<Mascota> optionalMascota = mascotas.stream().filter(m -> m.getId() == id).findFirst();
		if (optionalMascota.isPresent()) {
			mascotas.remove(optionalMascota.get());
			return true;
		} else {
			return false;
		}
	}

	public boolean existsById(int id) {
		return mascotas.stream().anyMatch(mascota -> mascota.getId() == id);
	}
}

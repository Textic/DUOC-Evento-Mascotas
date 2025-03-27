package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Evento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class EventoService {
    private final List<Evento> eventos = new ArrayList<>();
	private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$"); // esto lo hice para validar el formato de la fecha

	public EventoService() {
		eventos.add(new Evento(1, "Concurso de Perros", "01-11-2024", "Parque Forestal"));
		eventos.add(new Evento(2, "Feria de Gatos", "15-12-2024", "Centro Cultural La Moneda"));
		eventos.add(new Evento(3, "Exposicion de Aves", "20-01-2024", "Auditorio Nacional"));
		eventos.add(new Evento(4, "Carrera de Tortugas", "10-02-2025", "Parque Metropolitano"));
		eventos.add(new Evento(5, "Festival de Peces", "05-03-2025", "Acuario de Santiago"));
		eventos.add(new Evento(6, "Competencia de Conejos", "25-04-2025", "Jardin Botanico Chagual"));
		eventos.add(new Evento(7, "Desfile de Caballos", "15-05-2025", "Club Hipico de Santiago"));
		eventos.add(new Evento(8, "Feria de Reptiles", "30-06-2025", "Centro de Exposiciones Estacion Mapocho"));
	}

	public Evento addEvento(Evento evento) {
        if (evento.getId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no debe contener un ID.");
        }
        if (evento.getNombre() == null || evento.getNombre().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del evento es requerido.");
        }
        if (evento.getFecha() == null || evento.getFecha().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha del evento es requerida.");
        }
        if (!DATE_PATTERN.matcher(evento.getFecha()).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha debe tener el formato DD-MM-YYYY.");
        }
        if (evento.getLugar() == null || evento.getLugar().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El lugar del evento es requerido.");
        }
        int maxId = eventos.stream().mapToInt(Evento::getId).max().orElse(0);
        evento.setId(maxId + 1);
        eventos.add(evento);
        return evento;
    }

    public Evento findEvento(int id) {
        Optional<Evento> optionalEvento = eventos.stream().filter(e -> e.getId() == id).findFirst();
        if (optionalEvento.isPresent()) {
            return optionalEvento.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el evento con ID: " + id);
        }
    }

    public List<Evento> allEventos() {
        return eventos;
    }

    public boolean deleteEvento(int id) {
        Optional<Evento> optionalEvento = eventos.stream().filter(e -> e.getId() == id).findFirst();
        if (optionalEvento.isPresent()) {
            eventos.remove(optionalEvento.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean existsById(int id) {
        return eventos.stream().anyMatch(evento -> evento.getId() == id);
    }
}

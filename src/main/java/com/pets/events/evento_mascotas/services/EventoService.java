package com.pets.events.evento_mascotas.services;

import com.pets.events.evento_mascotas.models.Evento;
import com.pets.events.evento_mascotas.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository repo;

    public EventoService(EventoRepository eventoRepository) {
        this.repo = eventoRepository;
    }

    public Evento addEvento(Evento evento) {
        if (evento.getId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no debe contener un ID.");
        }
        return repo.save(evento);
    }

    public Evento findEvento(int id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el evento con ID: " + id));
    }

    public List<Evento> allEventos() {
        return repo.findAll();
    }

    public Evento deleteEvento(int id) {
        Evento evento = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el evento con ID: " + id));
        repo.deleteById(id);
        return evento;
    }

    public boolean existsById(int id) {
        return repo.existsById(id);
    }

    public Evento updateEvento(int id, Evento evento) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el evento con ID: " + id);
        }
        evento.setId(id);
        return repo.save(evento);
    }
}

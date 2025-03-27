package com.pets.events.evento_mascotas.controllers;

import java.util.List;
import com.pets.events.evento_mascotas.models.Evento;
import com.pets.events.evento_mascotas.services.EventoService;
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
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> allEventos() {
        return eventoService.allEventos();
    }

    @GetMapping("/{id}")
    public Evento findEvento(@PathVariable int id) {
        return eventoService.findEvento(id);
    }

    @DeleteMapping("/{id}")
    public Evento deleteEvento(@PathVariable int id) {
        Evento evento = eventoService.findEvento(id);
        boolean deleted = eventoService.deleteEvento(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo borrar el evento con ID: " + id);
        }
        return evento;
    }

    @PostMapping
    public Evento addEvento(@RequestBody Evento evento) {
        return eventoService.addEvento(evento);
    }
}

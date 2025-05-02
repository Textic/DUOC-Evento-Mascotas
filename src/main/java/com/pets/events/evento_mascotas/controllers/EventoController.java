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
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Evento>> allEventos() {
        log.info("GET /eventos - Listando todos los eventos");
        List<EntityModel<Evento>> eventos = eventoService.allEventos().stream()
            .map(evento -> EntityModel.of(evento,
                linkTo(methodOn(EventoController.class).findEvento(evento.getId())).withSelfRel(),
                linkTo(methodOn(EventoController.class).allEventos()).withRel("eventos")
            ))
            .toList();
        return CollectionModel.of(eventos,
            linkTo(methodOn(EventoController.class).allEventos()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Evento> findEvento(@PathVariable int id) {
        log.info("GET /eventos/{} - Buscando evento con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        Evento evento = eventoService.findEvento(id);
        return EntityModel.of(evento,
            linkTo(methodOn(EventoController.class).findEvento(id)).withSelfRel(),
            linkTo(methodOn(EventoController.class).allEventos()).withRel("eventos"),
            linkTo(methodOn(EventoController.class).deleteEvento(id)).withRel("delete"),
            linkTo(methodOn(EventoController.class).updateEvento(id, evento)).withRel("update")
        );
    }

    @DeleteMapping("/{id}")
    public EntityModel<Evento> deleteEvento(@PathVariable int id) {
        log.info("DELETE /eventos/{} - Eliminando evento con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        Evento evento = eventoService.deleteEvento(id);
        if (evento == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo encontrar el evento con ID: " + id);
        }
        return EntityModel.of(evento,
            linkTo(methodOn(EventoController.class).allEventos()).withRel("eventos")
        );
    }

    @PostMapping
    public EntityModel<Evento> addEvento(@Valid @RequestBody Evento evento) {
        log.info("POST /eventos - Agregando nuevo evento: {}", evento.getNombre());
        Evento nuevo = eventoService.addEvento(evento);
        return EntityModel.of(nuevo,
            linkTo(methodOn(EventoController.class).findEvento(nuevo.getId())).withSelfRel(),
            linkTo(methodOn(EventoController.class).allEventos()).withRel("eventos")
        );
    }

    @PutMapping("/{id}")
    public EntityModel<Evento> updateEvento(@PathVariable int id, @Valid @RequestBody Evento evento) {
        log.info("PUT /eventos/{} - Actualizando evento con ID: {}", id, id);
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo.");
        }
        if (evento.getNombre() == null || evento.getNombre().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del evento no puede estar vacío.");
        }
        Evento actualizado = eventoService.updateEvento(id, evento);
        return EntityModel.of(actualizado,
            linkTo(methodOn(EventoController.class).findEvento(id)).withSelfRel(),
            linkTo(methodOn(EventoController.class).allEventos()).withRel("eventos")
        );
    }
}

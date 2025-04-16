package com.pets.events.evento_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pets.events.evento_mascotas.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
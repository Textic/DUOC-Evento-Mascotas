package com.pets.events.evento_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pets.events.evento_mascotas.models.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}
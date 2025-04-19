package com.pets.events.evento_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pets.events.evento_mascotas.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByCorreo(String correo);
}

package com.pets.events.evento_mascotas.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participacion {
	private int id;
	private int idMascota;
	private int idEvento;
	private String categoria;
}

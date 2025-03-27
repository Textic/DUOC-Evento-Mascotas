package com.pets.events.evento_mascotas.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
	private int id;
	private String nombre;
	private String fecha;
	private String lugar;
	
}

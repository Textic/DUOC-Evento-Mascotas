package com.pets.events.evento_mascotas.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
	private int id;
	private String nombre;
	private int edad;
	private String tipo;
	private String raza;
	private String duenno;
}

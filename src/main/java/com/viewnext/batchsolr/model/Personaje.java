package com.viewnext.batchsolr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personaje {
	private Long id;
	private String nombre;
	private int vida;
	private int movimiento;
	private int armadura;
	
}

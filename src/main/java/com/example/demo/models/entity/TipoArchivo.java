package com.example.demo.models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tipo_archivos")
public class TipoArchivo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_archivo")
	private Long id;

	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

	private static final long serialVersionUID = 1L;
}

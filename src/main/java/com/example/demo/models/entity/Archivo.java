package com.example.demo.models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "archivos")
public class Archivo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_archivo")
	private Long id;

	private String descripcion;

	private String url;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_solicitud")
	private Solicitud solicitud;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_archivo")
	private TipoArchivo tipoArchivo;

	public Archivo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public TipoArchivo getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(TipoArchivo tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	private static final long serialVersionUID = 1L;

}

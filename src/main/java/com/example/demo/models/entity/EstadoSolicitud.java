package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "estado_solicitudes")
public class EstadoSolicitud implements Serializable {

	@EmbeddedId
	private EstadoSolicitudPK idEstadoSolicitud;

	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EstadoSolicitudPK getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}

	public void setIdEstadoSolicitud(EstadoSolicitudPK idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}

	private static final long serialVersionUID = 1L;
}

package com.example.demo.models.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EstadoSolicitudPK implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_solicitud")
	private Solicitud solicitud;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado")
	private Estado estado;

	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((solicitud == null) ? 0 : solicitud.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoSolicitudPK other = (EstadoSolicitudPK) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (solicitud == null) {
			if (other.solicitud != null)
				return false;
		} else if (!solicitud.equals(other.solicitud))
			return false;
		return true;
	}

}

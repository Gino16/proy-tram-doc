package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "solicitudes")
public class Solicitud implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSolicitud;
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_persona_sol")
	private Persona solicitante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_persona_dest")
	private Persona destinatario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_secretaria")
	private Persona secretaria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_solicitud")
	private TipoSolicitud tipoSolicitud;

	@OneToMany(mappedBy = "solicitud", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Archivo> archivos;

	@OneToMany(mappedBy = "solicitud", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Voucher> vouchers;

	
	
	public Solicitud() {
		archivos = new ArrayList<>();
		vouchers = new ArrayList<>();
	}

	public Long getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Persona getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Persona solicitante) {
		this.solicitante = solicitante;
	}

	public Persona getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Persona destinatario) {
		this.destinatario = destinatario;
	}

	public Persona getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Persona secretaria) {
		this.secretaria = secretaria;
	}

	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}

	public void addArchivo(Archivo archivo) {
		this.archivos.add(archivo);
	}

	public List<Voucher> getVouchers() {
		return vouchers;
	}

	public void setVouchers(List<Voucher> vouchers) {
		this.vouchers = vouchers;
	}

	public void addVoucher(Voucher voucher) {
		this.vouchers.add(voucher);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSolicitud == null) ? 0 : idSolicitud.hashCode());
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
		Solicitud other = (Solicitud) obj;
		if (idSolicitud == null) {
			if (other.idSolicitud != null)
				return false;
		} else if (!idSolicitud.equals(other.idSolicitud))
			return false;
		return true;
	}

}

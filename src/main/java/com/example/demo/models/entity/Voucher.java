package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "vouchers")
public class Voucher implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_voucher")
	private Long id;
	
	@NotEmpty
	//@Size(max = 3, min = 3)
	private String concepto;

	@NotEmpty
	//@Size(max = 7, min = 7)
	@Column(name = "num_secuencia")
	private String numSecuencia;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "fecha_pago")
	private Date fechaPago;
	
	@NotEmpty
	//@Size(max = 4, min = 4)
	@Column(name = "cod_agencia")
	private String codAgencia;
	
	@NotNull
	@Column(precision = 6, scale = 2)
	private Long monto;

	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_solicitud")
	private Solicitud solicitud;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getConcepto() {
		return concepto;
	}


	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}


	public String getNumSecuencia() {
		return numSecuencia;
	}


	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public String getCodAgencia() {
		return codAgencia;
	}


	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}


	public Long getMonto() {
		return monto;
	}


	public void setMonto(Long monto) {
		this.monto = monto;
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


	private static final long serialVersionUID = 1L;

}

package br.com.condominio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Unidade")
public class Unidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer	id;

	@Column(name = "nr_ap")
	@NotNull
	private Integer	nrAp;

	@Column(name = "nm_proprietario")
	@NotNull
	@Size(min = 1, max = 120)
	private String	nmProprietario;

	@Column(name = "locado")
	@NotNull
	private boolean	locado;

	@Column(name = "nm_locador")
	@Size(min = 1, max = 120)
	private String	nmLocador;

	public Unidade(Integer id, Integer nrAp, String nmProprietario, boolean locado, String nmLocador) {
		super();
		this.id = id;
		this.nrAp = nrAp;
		this.nmProprietario = nmProprietario;
		this.locado = locado;
		this.nmLocador = nmLocador;
	}

	public Unidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNrAp() {
		return nrAp;
	}

	public void setNrAp(Integer nrAp) {
		this.nrAp = nrAp;
	}

	public String getNmProprietario() {
		return nmProprietario;
	}

	public void setNmProprietario(String nmProprietario) {
		this.nmProprietario = nmProprietario;
	}

	public boolean isLocado() {
		return locado;
	}

	public void setLocado(boolean locado) {
		this.locado = locado;
	}

	public String getNmLocador() {
		return nmLocador;
	}

	public void setNmLocador(String nmLocador) {
		this.nmLocador = nmLocador;
	}
	  


}

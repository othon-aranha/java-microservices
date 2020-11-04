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

import br.com.condominio.entity.SimNaoType;

@Entity
@Table(name = "local")
public class Local implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true)
	private Integer	id;

	@Column(name = "ds_local")
	@NotNull
	@Size(min = 3, max = 60)
	private String	dsLocal;
	
	@Column(name = "locavel")
	@NotNull
	private SimNaoType	locavel;

	@Column(name = "vlr_locacao")
	@NotNull
	private float vlrLocacao;
	
	public Local() {
		super();
	}
	

	public Local(Integer id, @NotNull @Size(min = 3, max = 60) String dsLocal, @NotNull SimNaoType locavel,
			@NotNull float vlrLocacao) {
		super();
		this.id = id;
		this.dsLocal = dsLocal;
		this.locavel = locavel;
		this.vlrLocacao = vlrLocacao;
	}


	public String getDsLocal() {
		return dsLocal;
	}

	public void setDsLocal(String dsLocal) {
		this.dsLocal = dsLocal;
	}

	public SimNaoType getLocavel() {
		return locavel;
	}

	public void setLocavel(SimNaoType locavel) {
		this.locavel = locavel;
	}

	public float getVlrLocacao() {
		return vlrLocacao;
	}

	public void setVlrLocacao(float vlrLocacao) {
		this.vlrLocacao = vlrLocacao;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

}

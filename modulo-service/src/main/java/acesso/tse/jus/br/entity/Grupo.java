package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import acesso.tse.jus.br.AcessoConstants;

@Entity
@Table(name = "grupo", schema = "admacesso")
public class Grupo implements Serializable {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "sq_grupo", unique = true)
	private Integer				id;

	@Column(name = "nm_grupo", nullable = false)
	private String				nome;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "sq_secao_orgao", referencedColumnName = "sq_secao_orgao", nullable = true)
	private Area				area;
	
	public Grupo() {
		super();
	}


	public Grupo(Integer id, String nome, Area area) {
		super();
		this.id = id;
		this.nome = nome;
		this.area = area;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}	


	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}


}

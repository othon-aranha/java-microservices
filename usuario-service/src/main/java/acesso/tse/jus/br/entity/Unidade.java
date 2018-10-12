package acesso.tse.jus.br.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;

@Entity
@Table(name = "vw_unidade_tse", schema = "admacesso")
public class Unidade implements Serializable {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "cd", unique = true)
	private Integer				id;

	@Column(name = "sigla_unid_tse", nullable = false)
	private String				sigla;

	@Column(name = "ds", nullable = false)
	private String				nome;

	//@Column(name = "e_mail", nullable = false)
	//private String				email;

	@Column(name = "sit_unid", nullable = false)
	private String				status;

	//@OneToOne(optional = true, fetch = FetchType.LAZY)
	//@JoinColumn(name = "cod_unid_super", referencedColumnName = "cd", nullable = true)
	//private Unidade		pai;
	@Column(name = "cod_unid_super", nullable = true)
	private Integer pai;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pai")
	@OrderBy(value = "nome")
	@Where(clause = "sit_unid = 'C' OR sit_unid LIKE 'O%'")
	private Set<Unidade>		filhos;

	public Unidade() {
		super();
	}

	/*
	  public Unidade(Integer id, String sigla, String nome, String status, Unidade pai,
			Set<Unidade> filhos) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.status = status;
		this.pai = pai;
		this.filhos = filhos;
	}
    */
	
	
	  public Unidade(Integer id, String sigla, String nome, String status, Integer pai,
			Set<Unidade> filhos) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.status = status;
		this.pai = pai;
		this.filhos = filhos;
	}
	
	public Integer getId() {
		return this.id;
	}

	
	public void setId(final Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(final String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	
	/*
	 public Unidade getPai() {
	 
		return this.pai;
	}

	public void setPai(final Unidade pai) {
		this.pai = pai;
	}
    */
	
	 public Integer getPai() {
		 
		return this.pai;
	}

	public void setPai(final Integer pai) {
		this.pai = pai;
	}	
	
	@JsonIgnore
	public Set<Unidade> getFilhos() {
		return this.filhos;
	}


	public void setFilhos(final Set<Unidade> filhos) {
		this.filhos = filhos;
	}

}

package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.OrderBy;
import javax.persistence.Table;

// import org.hibernate.annotations.Where;

import acesso.tse.jus.br.AcessoConstants;

@Entity
@Table(name = "secao_orgao", schema = "admsadp")
public class Area implements Serializable {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "sq_secao_orgao", unique = true)
	private Integer				id;

	@Column(name = "sg_secao", nullable = false)
	private String				sigla;

	@Column(name = "no_secao", nullable = false)
	private String				nome;

	@Column(name = "ds_email", nullable = false)
	private String				email;

	@Column(name = "st_ativo", nullable = false, columnDefinition = "NUMBER")
	private Boolean				status;

	@Column(name = "st_zona", nullable = false, columnDefinition = "NUMBER")
	private Boolean				zona;

	@Column(name = "nr_zona", nullable = false, columnDefinition = "NUMBER")
	private Integer				numeroZona;

	@Column(name = "tp_secao", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoArea			tipo;

	@Column(name = "sq_secao_sup", nullable=true, unique = false)
	private Integer	id_secao_sup;
	
	/* @ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "sq_secao_sup", referencedColumnName = "sq_secao_orgao", nullable = true)
	private Area				pai; */

	/* @OneToMany(fetch = FetchType.LAZY, mappedBy = "pai")
	@OrderBy(value = "nome")
	@Where(clause = "st_ativo = 1")
	private Set<Area>			filhos; */


	/*
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "vw_gerente_area", schema = "admacesso", joinColumns = @JoinColumn(name = "sq_secao_orgao", referencedColumnName = "sq_secao_orgao"), inverseJoinColumns = @JoinColumn(name = "sq_usuario", referencedColumnName = "sq_usuario"))
	private Set<Usuario>		gerentes;
    */
	
	public Area() {
		super();
	}

	public Area(Integer id, String sigla, String nome, String email, Boolean status, Boolean zona, Integer numeroZona,
			TipoArea tipo,
			Integer id_secao_sup
			// Area pai, 
			// Set<Area> filhos, 
			// Set<Usuario> gerentes
			) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.email = email;
		this.id_secao_sup = id_secao_sup;
		this.status = status;
		this.zona = zona;
		this.numeroZona = numeroZona;
		this.tipo = tipo;
		// this.pai = pai;
		// this.filhos = filhos;
		// this.gerentes = gerentes;
	}
	

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}	

	public Integer getId_secao_sup() {
		return id_secao_sup;
	}

	public void setId_secao_sup(Integer id_secao_sup) {
		this.id_secao_sup = id_secao_sup;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(final Boolean status) {
		this.status = status;
	}

	public Boolean getZona() {
		return this.zona;
	}

	public void setZona(final Boolean zona) {
		this.zona = zona;
	}

	public Integer getNumeroZona() {
		return this.numeroZona;
	}

	public void setNumeroZona(final Integer numeroZona) {
		this.numeroZona = numeroZona;
	}

	public TipoArea getTipo() {
		return this.tipo;
	}

	public void setTipo(final TipoArea tipo) {
		this.tipo = tipo;
	}

	/*
	public Area getPai() {
		return this.pai;
	}

	public void setPai(final Area pai) {
		this.pai = pai;
	}

	
	  public Set<Area> getFilhos() {	 
		return this.filhos;
	}

	public void setFilhos(final Set<Area> filhos) {
		this.filhos = filhos;
	}
    
	
	public Set<Usuario> getGerentes() {
		return this.gerentes;
	}

	public void setGerentes(final Set<Usuario> gerentes) {
		this.gerentes = gerentes;
	}
    */
}

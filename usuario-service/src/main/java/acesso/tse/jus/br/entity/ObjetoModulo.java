package acesso.tse.jus.br.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;

@Entity
@Table(name = "objeto_modulo", schema = "admacesso")
@SequenceGenerator(name = "sq_objeto_modulo", sequenceName = "admacesso.sq_objeto_modulo", allocationSize = 1)
public class ObjetoModulo {

	@SuppressWarnings("unused")
	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_objeto_modulo")
	@Column(name = "sq_objeto_modulo", unique = true)
	private Integer				id;

	@Column(name = "nm_objeto")
	@Size(max = 250)
	private String				nome;

	@Column(name = "nm_classe")
	@Size(max = 50)
	private String				classe;

	@Column(name = "ds_url_acao")
	@Size(max = 255)
	private String				url;

	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "tp_objeto", referencedColumnName = "id", nullable = false)
	@NotNull
	private TipoObjeto			tipo;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo", nullable = false)
	@NotNull
	private Modulo				modulo;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_objeto", referencedColumnName = "cd_objeto", nullable = false)
	@NotNull
	private Objeto				objeto;

	@ManyToMany(mappedBy = "objetos", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<Perfil>			perfis;

	@ManyToMany(mappedBy = "objetos", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<UsuarioArea>	usuarios;
    
	
	public ObjetoModulo() {
		super();
	}

		
	public ObjetoModulo(Integer id, String nome, String classe, String url
			, TipoObjeto tipo, Modulo modulo,
			Objeto objeto, Set<Perfil> perfis, Set<UsuarioArea> usuarios
			) {
		super();
		this.id = id;
		this.nome = nome;
		this.classe = classe;
		this.url = url;
		/*
		this.tipo = tipo;
		this.modulo = modulo;
		this.objeto = objeto;
		this.perfis = perfis;
		this.usuarios = usuarios;
		*/
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

	public String getClasse() {
		return this.classe;
	}

	public void setClasse(final String classe) {
		this.classe = classe;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
    
	
	public TipoObjeto getTipo() {
		return this.tipo;
	}

	public void setTipo(final TipoObjeto tipo) {
		this.tipo = tipo;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(final Modulo modulo) {
		this.modulo = modulo;
	}

	public Objeto getObjeto() {
		return this.objeto;
	}

	public void setObjeto(final Objeto objeto) {
		this.objeto = objeto;
	}

	public Set<Perfil> getPerfis() {
		return this.perfis;
	}

	public void setPerfis(final Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Set<UsuarioArea> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(final Set<UsuarioArea> usuarios) {
		this.usuarios = usuarios;
	}
	 

}

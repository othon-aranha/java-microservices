package acesso.tse.jus.br.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;

@Entity
@Table(name = "usuario", schema = "admacesso")
@SequenceGenerator(name = "sq_usuario", sequenceName = "admacesso.sq_usuario", allocationSize = 1)
public class Usuario  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario")
	@Column(name = "sq_usuario", unique = true)
	private Integer				id;

	@Column(name = "cd_usuario", nullable = false)
	@NotNull
	@Size(min = 1, max = 64)
	private String				login;

	@Column(name = "nom_usuario")
	@Size(max = 50)
	private String				nome;

	@Column(name = "mat_servidor")
	@Size(max = 8)
	private String				matriculaServidor;

	@Column(name = "mat_func")
	@Size(max = 8)
	private String				matriculaFuncionario;

	@Column(name = "email")
	@Size(max = 60)
	private String				email;

	@Column(name = "senha")
	@NotNull
	@Size(max = 64)
	private String				senha;

	@Column(name = "nr_cpf")
	private String				numeroCpf;

	@Column(name = "st_usuario", nullable = false)
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private StatusUsuario		status;

	@Column(name = "tp_usuario", nullable = false)
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private TipoUsuario			tipo;
	
	//@ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	//private Set<Modulo>			usuarioModulos;
	

	/*
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_unidade", referencedColumnName = "cd", nullable = true)
	private Unidade				unidade;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<UsuarioArea>	areas;

	@ManyToMany(mappedBy = "gerentes", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<Area>			gerenteAreas;

	@ManyToMany(mappedBy = "gerentes", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<Modulo>			gerenteModulos;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "grupo_usuario", schema = "admacesso", joinColumns = @JoinColumn(name = "sq_usuario", referencedColumnName = "sq_usuario"), inverseJoinColumns = @JoinColumn(name = "sq_grupo", referencedColumnName = "sq_grupo"))
	private Set<Grupo>			grupos;
    */
	
	public Usuario() {
		super();
	}

	
	public Usuario(Integer id, String login, String nome, String matriculaServidor, String matriculaFuncionario,
			String email, String senha, String numeroCpf, StatusUsuario status, TipoUsuario tipo, Unidade unidade,
			Set<UsuarioArea> areas, Set<Area> gerenteAreas, Set<Modulo> gerenteModulos, Set<Modulo> usuarioModulos) {
		super();
		this.id = id;
		this.login = login;
		this.nome = nome;
		this.matriculaServidor = matriculaServidor;
		this.matriculaFuncionario = matriculaFuncionario;
		this.email = email;
		this.senha = senha;
		this.numeroCpf = numeroCpf;
		this.status = status;
		this.tipo = tipo;		
	}


	public Integer getId() {
		return this.id;
	}

	
	public void setId(final Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getMatriculaServidor() {
		return this.matriculaServidor;
	}

	public void setMatriculaServidor(final String matriculaServidor) {
		this.matriculaServidor = matriculaServidor;
	}

	public String getMatriculaFuncionario() {
		return this.matriculaFuncionario;
	}

	public void setMatriculaFuncionario(final String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public String getNumeroCpf() {
		return this.numeroCpf;
	}

	public void setNumeroCpf(final String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public StatusUsuario getStatus() {
		return this.status;
	}

	public void setStatus(final StatusUsuario status) {
		this.status = status;
	}

	public TipoUsuario getTipo() {
		return this.tipo;
	}

	public void setTipo(final TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	/*@JsonIgnore
	public Set<Modulo> getUsuarioModulos() {
		return this.usuarioModulos;
	}

	public void setUsuarioModulos(final Set<Modulo> usuarioModulos) {
		this.usuarioModulos = usuarioModulos;
	}	

	
	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(final Unidade unidade) {
		this.unidade = unidade;
	}

	public Set<UsuarioArea> getAreas() {
		return this.areas;
	}

	public void setAreas(final Set<UsuarioArea> areas) {
		this.areas = areas;
	}

	public Set<Area> getGerenteAreas() {
		return this.gerenteAreas;
	}

	public void setGerenteAreas(final Set<Area> gerenteAreas) {
		this.gerenteAreas = gerenteAreas;
	}

	public Set<Modulo> getGerenteModulos() {
		return this.gerenteModulos;
	}

	public void setGerenteModulos(final Set<Modulo> gerenteModulos) {
		this.gerenteModulos = gerenteModulos;
	}

	public Set<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(final Set<Grupo> grupos) {
		this.grupos = grupos;
	}
*/
}

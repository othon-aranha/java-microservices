package acesso.tse.jus.br.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;

@Entity
@Table(name = "usuario_area", schema = "admacesso")
@SequenceGenerator(name = "sq_usuario_area", sequenceName = "admacesso.sq_usuario_area", allocationSize = 1)
public class UsuarioArea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sq_usuario_area", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario_area")
	private Integer				id;

	@Column(name = "st_default", nullable = false, columnDefinition = "CHAR")
	@Enumerated(EnumType.STRING)
	private SimNaoType			padrao;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_unidade", referencedColumnName = "sq_secao_orgao", nullable = true)
	private Area				area;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "sq_usuario", referencedColumnName = "sq_usuario", nullable = true)
	private Usuario				usuario;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "usu_area_perfil", schema = "admacesso", joinColumns = @JoinColumn(name = "sq_usuario_area", referencedColumnName = "sq_usuario_area"), inverseJoinColumns = @JoinColumn(name = "cd_perfil", referencedColumnName = "cd_perfil"))
	private Set<Perfil>			perfis;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "usu_area_objeto", schema = "admacesso", joinColumns = @JoinColumn(name = "sq_usuario_area", referencedColumnName = "sq_usuario_area"), inverseJoinColumns = @JoinColumn(name = "sq_objeto_modulo", referencedColumnName = "sq_objeto_modulo"))
	private Set<ObjetoModulo>	objetos;

	public UsuarioArea() {
		super();
	}

	
	public UsuarioArea(Integer id, SimNaoType padrao, Area area, Usuario usuario, Set<Perfil> perfis,
			Set<ObjetoModulo> objetos) {
		super();
		this.id = id;
		this.padrao = padrao;
		this.area = area;
		this.usuario = usuario;
		this.perfis = perfis;
		this.objetos = objetos;
	}

	public Integer getId() {
		return this.id;
	}

	
	public void setId(final Integer id) {
		this.id = id;
	}

	public SimNaoType getPadrao() {
		return this.padrao;
	}

	public void setPadrao(final SimNaoType padrao) {
		this.padrao = padrao;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(final Area area) {
		this.area = area;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	@JsonIgnore
	public Set<Perfil> getPerfis() {
		return this.perfis;
	}

	public void setPerfis(final Set<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	@JsonIgnore
	public Set<ObjetoModulo> getObjetos() {
		return this.objetos;
	}

	public void setObjetos(final Set<ObjetoModulo> objetos) {
		this.objetos = objetos;
	}

}

package acesso.tse.jus.br.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractStringEntity;

@Entity
@Table(name = "perfil", schema = "admacesso")
public class Perfil  {

	@SuppressWarnings("unused")
	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "cd_perfil", unique = true)
	@Size(min = 2, max = 30)
	private String				id;

	@Column(name = "ds_perfil")
	@Size(max = 50)
	private String				nome;
    
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo", nullable = true)
	private Modulo				modulo;

	// @ManyToMany(mappedBy = "perfis", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	// private Set<UsuarioArea>	usuarios;
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "perfil_objeto", schema = "admacesso", joinColumns = @JoinColumn(name = "cd_perfil", referencedColumnName = "cd_perfil"), inverseJoinColumns = @JoinColumn(name = "sq_objeto_modulo", referencedColumnName = "sq_objeto_modulo"))
	private Set<ObjetoModulo>	objetos;
	
	public Perfil() {
		super();
	}

	public Perfil(String id, String nome, Modulo modulo 
			//,Set<UsuarioArea> usuarios
			,Set<ObjetoModulo> objetos
			) {
		super();
		this.id = id;
		this.nome = nome;		
		this.modulo = modulo;
		// this.usuarios = usuarios;
		this.objetos = objetos;
	}

	public String getId() {
		return this.id;
	}

	
	public void setId(final String id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	
	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(final Modulo modulo) {
		this.modulo = modulo;
	}

	/*public Set<UsuarioArea> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(final Set<UsuarioArea> usuarios) {
		this.usuarios = usuarios;
	}
    */
	
	public Set<ObjetoModulo> getObjetos() {
		return this.objetos;
	}

	public void setObjetos(final Set<ObjetoModulo> objetos) {
		this.objetos = objetos;
	}
      
}

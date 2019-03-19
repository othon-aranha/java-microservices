package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.AbstractEntity;

@Entity
@Table(name = "maquina_servidora", schema = "admacesso")
public class MaquinaServidora implements Serializable  {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@NotNull
	@Column(name = "sq_maquina_servidora", nullable = false)
	private Integer	id;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "cd_trib", referencedColumnName = "cd_trib", nullable = false)
	@NotNull
	private Tribunal tribunal;
	
	@Column(name = "alias")
	@NotNull
	@Size(min = 1, max = 20)
	private String				alias;

	@Column(name = "descricao", nullable = false)
	@NotNull
	@Size(min = 2, max = 200)
	private String				descricao;

	@Column(name = "usuario", nullable = false)
	@NotNull
	@Size(min = 2, max = 20)
	private String				usuario;

	@Column(name = "senha", nullable = false)
	@NotNull
	@Size(min = 2, max = 128)
	private String				senha;

	@Column(name = "string_conexao")
	@Size(max = 2000)
	private String	conexao;
	
	public MaquinaServidora(Integer id, Tribunal tribunal, String alias, String descricao, String usuario, String senha,
			String conexao) {
		super();
		this.id = id;
		this.tribunal = tribunal;
		this.alias = alias;
		this.descricao = descricao;
		this.usuario = usuario;
		this.senha = senha;
		this.conexao = conexao;
	}


	public Tribunal getTribunal() {
		return tribunal;
	}


	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public MaquinaServidora() {
		super();
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public String getConexao() {
		return this.conexao;
	}

	public void setConexao(final String conexao) {
		this.conexao = conexao;
	}
}

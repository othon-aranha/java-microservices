package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.AbstractEntity;

@Entity
@Table(name = "maquina_servidora", schema = "admacesso")
public class MaquinaServidora implements Serializable  {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@EmbeddedId
	private MaquinaServidoraPK	id;

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
	
	public MaquinaServidora() {
		super();
	}

	public MaquinaServidoraPK getId() {
		return this.id;
	}

	
	public void setId(final MaquinaServidoraPK id) {
		this.id = id;
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

package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

// import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractStringEntity;

@Entity
@Table(name = "servidor", schema = "srh2")
@Immutable
public class Servidor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mat_servidor", unique = true)
	private String				id;

	@Column(name = "nom", nullable = false)
	private String				nome;

	@Column(name = "e_mail", nullable = false)
	private String				email;

	@Column(name = "mat_servidor", insertable = false, updatable = false)
	private String				matricula;

	@Column(name = "num_cpf")
	private String				numeroCpf;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_si_func", referencedColumnName = "id", nullable = true)
	private StatusServidor		status;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "unid_lot", referencedColumnName = "cd", nullable = true)
	private Unidade				unidade;

	public Servidor() {
		super();
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(final String matricula) {
		this.matricula = matricula;
	}

	public String getNumeroCpf() {
		return this.numeroCpf;
	}

	public void setNumeroCpf(final String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public StatusServidor getStatus() {
		return this.status;
	}

	public void setStatus(final StatusServidor status) {
		this.status = status;
	}

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(final Unidade unidade) {
		this.unidade = unidade;
	}

}

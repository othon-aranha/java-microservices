package acesso.tse.jus.br.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;

@Entity
@Table(name = "manutencao", schema = "admacesso")
public class Manutencao {

	@SuppressWarnings("unused")
	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@EmbeddedId
	private ManutencaoKey		id;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusManutencao	status;

	@Column(name = "dt_manutencao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataManutencao;

	@Column(name = "desc_erro")
	@Size(max = 4000)
	private String				mensagemErro;

	@Column(name = "versao")
	@Size(max = 20)
	private String				versao;

	@Column(name = "conexao")
	@Size(max = 30)
	private String				conexao;

	@Column(name = "maquina")
	@Size(max = 30)
	private String				maquina;

	@Column(name = "instancia")
	@Size(max = 30)
	private String				instancia;

	public Manutencao(ManutencaoKey id, StatusManutencao status, Date dataManutencao, String mensagemErro,
			String versao, String conexao, String maquina, String instancia) {
		super();
		this.id = id;
		this.status = status;
		this.dataManutencao = dataManutencao;
		this.mensagemErro = mensagemErro;
		this.versao = versao;
		this.conexao = conexao;
		this.maquina = maquina;
		this.instancia = instancia;
	}

	public Manutencao() {
		super();
	}

	public Manutencao(final ManutencaoKey id) {
		super();
		this.id = id;
	}

	
	public ManutencaoKey getId() {
		return this.id;
	}

	
	public void setId(final ManutencaoKey id) {
		this.id = id;
	}

	public StatusManutencao getStatus() {
		return this.status;
	}

	public void setStatus(final StatusManutencao status) {
		this.status = status;
	}

	public Date getDataManutencao() {
		return this.dataManutencao;
	}

	public void setDataManutencao(final Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public String getMensagemErro() {
		return this.mensagemErro;
	}

	public void setMensagemErro(final String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getVersao() {
		return this.versao;
	}

	public void setVersao(final String versao) {
		this.versao = versao;
	}

	public String getConexao() {
		return this.conexao;
	}

	public void setConexao(final String conexao) {
		this.conexao = conexao;
	}

	public String getMaquina() {
		return this.maquina;
	}

	public void setMaquina(final String maquina) {
		this.maquina = maquina;
	}

	public String getInstancia() {
		return this.instancia;
	}

	public void setInstancia(final String instancia) {
		this.instancia = instancia;
	}

}

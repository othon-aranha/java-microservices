package acesso.tse.jus.br.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;

@Entity
@Table(name = "manutencao", schema = "admacesso")
public class Manutencao implements Serializable {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "sq_manutencao", nullable = false)
	private Integer	id;

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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sq_maquina_servidora", referencedColumnName = "sq_maquina_servidora", nullable = false)
	private MaquinaServidora	maquinaservidora;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo", nullable = false)
	private Modulo	modulo;
	
	public Manutencao() {
		super();
	}

	public Manutencao(Integer id, StatusManutencao status, Date dataManutencao, @Size(max = 4000) String mensagemErro,
			@Size(max = 20) String versao, @Size(max = 30) String conexao, @Size(max = 30) String maquina,
			@Size(max = 30) String instancia, MaquinaServidora maquinaservidora, Modulo modulo) {
		super();
		this.id = id;
		this.status = status;
		this.dataManutencao = dataManutencao;
		this.mensagemErro = mensagemErro;
		this.versao = versao;
		this.conexao = conexao;
		this.maquina = maquina;
		this.instancia = instancia;
		this.maquinaservidora = maquinaservidora;
		this.modulo = modulo;
	}
	
	public MaquinaServidora getMaquinaservidora() {
		return maquinaservidora;
	}

	public void setMaquinaservidora(MaquinaServidora maquinaservidora) {
		this.maquinaservidora = maquinaservidora;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Manutencao(final Integer id) {
		super();
		this.id = id;
	}

	
	public Integer getId() {
		return this.id;
	}

	
	public void setId(final Integer id) {
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

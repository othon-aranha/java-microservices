package br.com.condominio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer	id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)	
	@JoinColumn(name = "unidade_reserva", referencedColumnName = "id", nullable = false)	
	private Unidade	unidadeReserva;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)	
	@JoinColumn(name = "local_reserva", referencedColumnName = "id", nullable = false)
	private Local	localReserva;
	
	@Column(name = "dt_reserva")
	@NotNull
	@Size(min = 3, max = 24)
	private String	dtReserva;
	
	@Column(name = "hr_ini_reserva")
	@NotNull
	@Size(min = 3, max = 29)
	private String	hrIniReserva;

	@Column(name = "hr_fim_reserva")
	@NotNull
	@Size(min = 3, max = 29)
	private String	hrFimReserva;	
	  
	@Column(name = "dt_solicitacao")
	@NotNull
	@Size(min = 3, max = 24)
	private String	dtSolicitacao;

	@Column(name = "dt_confirmacao")
	@Size(min = 3, max = 24)
	private String	dtConfirmacao;	
	
	@Column(name = "dt_cancelamento")
	@Size(min = 3, max = 24)
	private String	dtCancelamento;	
	
	public Reserva() {
		super();
	}
	
	public Reserva(Integer id, Unidade unidadeReserva, Local localReserva, String dtReserva, String hrIniReserva,
			String hrFimReserva, String dtSolicitacao, String dtConfirmacao, String dtCancelamento) {
		super();
		this.id = id;
		this.unidadeReserva = unidadeReserva;
		this.localReserva = localReserva;
		this.dtReserva = dtReserva;
		this.hrIniReserva = hrIniReserva;
		this.hrFimReserva = hrFimReserva;
		this.dtSolicitacao = dtSolicitacao;
		this.dtConfirmacao = dtConfirmacao;
		this.dtCancelamento = dtCancelamento;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Unidade getUnidadeReserva() {
		return unidadeReserva;
	}


	public void setUnidadeReserva(Unidade unidadeReserva) {
		this.unidadeReserva = unidadeReserva;
	}


	public Local getLocalReserva() {
		return localReserva;
	}


	public void setLocalReserva(Local localReserva) {
		this.localReserva = localReserva;
	}


	public String getDtReserva() {
		return dtReserva;
	}


	public void setDtReserva(String dtReserva) {
		this.dtReserva = dtReserva;
	}


	public String getHrIniReserva() {
		return hrIniReserva;
	}


	public void setHrIniReserva(String hrIniReserva) {
		this.hrIniReserva = hrIniReserva;
	}


	public String getHrFimReserva() {
		return hrFimReserva;
	}


	public void setHrFimReserva(String hrFimReserva) {
		this.hrFimReserva = hrFimReserva;
	}


	public String getDtSolicitacao() {
		return dtSolicitacao;
	}


	public void setDtSolicitacao(String dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
	}	
	
	public String getDtConfirmacao() {
		return dtConfirmacao;
	}

	public void setDtConfirmacao(String dtConfirmacao) {
		this.dtConfirmacao = dtConfirmacao;
	}

	public String getDtCancelamento() {
		return dtCancelamento;
	}

	public void setDtCancelamento(String dtCancelamento) {
		this.dtCancelamento = dtCancelamento;
	}


}

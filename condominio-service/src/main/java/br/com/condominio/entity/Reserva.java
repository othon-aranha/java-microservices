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
	private Date	dtReserva;
	
	@Column(name = "hr_ini_reserva")
	@NotNull
	private Timestamp	hrIniReserva;

	@Column(name = "hr_fim_reserva")
	@NotNull
	private Timestamp	hrFimReserva;	
	  
	@Column(name = "dt_solicitacao")
	@NotNull
	private Date	dtSolicitacao;

	@Column(name = "dt_confirmacao")
	private Date	dtConfirmacao;	
	
	@Column(name = "dt_cancelamento")
	private Date	dtCancelamento;	
	
	public Reserva() {
		super();
	}
	
	public Reserva(Integer id, Unidade unidadeReserva, Local localReserva, Date dtReserva, Timestamp hrIniReserva,
			Timestamp hrFimReserva, Date dtSolicitacao, Date dtConfirmacao, Date dtCancelamento) {
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


	public Date getDtReserva() {
		return dtReserva;
	}


	public void setDtReserva(Date dtReserva) {
		this.dtReserva = dtReserva;
	}


	public Timestamp getHrIniReserva() {
		return hrIniReserva;
	}


	public void setHrIniReserva(Timestamp hrIniReserva) {
		this.hrIniReserva = hrIniReserva;
	}


	public Timestamp getHrFimReserva() {
		return hrFimReserva;
	}


	public void setHrFimReserva(Timestamp hrFimReserva) {
		this.hrFimReserva = hrFimReserva;
	}


	public Date getDtSolicitacao() {
		return dtSolicitacao;
	}


	public void setDtSolicitacao(Date dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
	}	
	
	public Date getDtConfirmacao() {
		return dtConfirmacao;
	}

	public void setDtConfirmacao(Date dtConfirmacao) {
		this.dtConfirmacao = dtConfirmacao;
	}

	public Date getDtCancelamento() {
		return dtCancelamento;
	}

	public void setDtCancelamento(Date dtCancelamento) {
		this.dtCancelamento = dtCancelamento;
	}


}

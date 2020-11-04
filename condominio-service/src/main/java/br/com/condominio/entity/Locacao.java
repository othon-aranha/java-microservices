package br.com.condominio.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Locacao")
public class Locacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true)
	private Integer	id;

	@Column(name = "unidade_locataria")
	@NotNull
	private Integer	unidadeLocataria;

	@Column(name = "local")
	@NotNull
	private Integer	local;

	@Column(name = "dt_locacao")
	@NotNull
	private Date	Dtlocacao;

	@Column(name = "hora_ini")
	private Time	horaIni;

	@Column(name = "hora_fim")
	private Time	horaFim;

	@Column(name = "dt_solicitacao")
	@NotNull
	private Date	Dtsolicitacao;
	
	public Locacao(Integer id, @NotNull Integer unidadeLocataria, @NotNull Integer local, @NotNull Date dtlocacao,
			Time horaIni, Time horaFim, @NotNull Date dtsolicitacao) {
		super();
		this.id = id;
		this.unidadeLocataria = unidadeLocataria;
		this.local = local;
		Dtlocacao = dtlocacao;
		this.horaIni = horaIni;
		this.horaFim = horaFim;
		Dtsolicitacao = dtsolicitacao;
	}


	public Locacao() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUnidadeLocataria() {
		return unidadeLocataria;
	}


	public void setUnidadeLocataria(Integer unidadeLocataria) {
		this.unidadeLocataria = unidadeLocataria;
	}


	public Integer getLocal() {
		return local;
	}


	public void setLocal(Integer local) {
		this.local = local;
	}


	public Date getDtlocacao() {
		return Dtlocacao;
	}


	public void setDtlocacao(Date dtlocacao) {
		Dtlocacao = dtlocacao;
	}


	public Time getHoraIni() {
		return horaIni;
	}


	public void setHoraIni(Time horaIni) {
		this.horaIni = horaIni;
	}


	public Time getHoraFim() {
		return horaFim;
	}


	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}


	public Date getDtsolicitacao() {
		return Dtsolicitacao;
	}


	public void setDtsolicitacao(Date dtsolicitacao) {
		Dtsolicitacao = dtsolicitacao;
	}


}

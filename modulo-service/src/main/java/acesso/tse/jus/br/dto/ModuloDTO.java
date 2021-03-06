package acesso.tse.jus.br.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import acesso.tse.jus.br.entity.SimNaoType;
import acesso.tse.jus.br.entity.StatusModulo;
import acesso.tse.jus.br.entity.TipoAtualizacao;
import acesso.tse.jus.br.entity.TipoModulo;

@JsonDeserialize
public class ModuloDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String				sigla;

	private String				nome;
	
	
	private Boolean	mensagemCompartilhada;

	@NotEmpty(message="Preenchimento Obrigatório")
	@Enumerated(EnumType.STRING)
	private SimNaoType			controlaAcesso;

	
	public List<TipoModulo>			tipoModulo;

	@Enumerated(EnumType.STRING)
	private List<TipoAtualizacao>		tipoAtualizacao;

	@Enumerated(EnumType.ORDINAL)
	public List<StatusModulo>		statusModulo;	


	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getMensagemCompartilhada() {
		return mensagemCompartilhada;
	}

	public void setMensagemCompartilhada(Boolean mensagemCompartilhada) {
		this.mensagemCompartilhada = mensagemCompartilhada;
	}

	public SimNaoType getControlaAcesso() {
		return controlaAcesso;
	}

	public void setControlaAcesso(SimNaoType controlaAcesso) {
		this.controlaAcesso = controlaAcesso;
	}

	public List<TipoModulo> getTipoModulo() {
		return tipoModulo;
	}

	public void setTipoModulo(List<TipoModulo> tipoModulo) {
		this.tipoModulo = tipoModulo;
	}

	public List<TipoAtualizacao> getTipoAtualizacao() {
		return tipoAtualizacao;
	}

	public void setTipoAtualizacao(List<TipoAtualizacao> tipoAtualizacao) {
		this.tipoAtualizacao = tipoAtualizacao;
	}

	public List<StatusModulo> getStatusModulo() {
		return statusModulo;
	}

	public void setStatusModulo(List<StatusModulo> statusModulo) {
		this.statusModulo = statusModulo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

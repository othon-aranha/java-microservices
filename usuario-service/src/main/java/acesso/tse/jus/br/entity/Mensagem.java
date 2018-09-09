package acesso.tse.jus.br.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;

@Entity
@Table(name = "MENSAGEM", schema = "ADMACESSO")
public class Mensagem  {

	@SuppressWarnings("unused")
	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "cd_mensagem", unique = true)
	private Integer				id;

	@Column(name = "ds_mensagem", length = 255, nullable = false)
	@NotNull
	@Size(max = 255)
	private String				texto;

	@Column(name = "tp_mensagem", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private TipoMensagem		tipo;

	public Mensagem() {
		super();
	}

	public Mensagem(Integer id, String texto, TipoMensagem tipo) {
		super();
		this.id = id;
		this.texto = texto;
		this.tipo = tipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}

	public TipoMensagem getTipo() {
		return this.tipo;
	}

	public void setTipo(final TipoMensagem tipo) {
		this.tipo = tipo;
	}

}

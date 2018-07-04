package acesso.tse.jus.br.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Subselect;

import acesso.tse.jus.br.AcessoConstants;


@Entity
@Subselect("SELECT TO_NUMBER(cd_dominio) id, ds_dominio nome FROM admacesso.dominio d WHERE d.no_dominio = 'TP_OBJETO'")
public class TipoObjeto {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@Column(name = "id", unique = true)
	private Integer				id;

	@Column(name = "nome")
	@NotNull
	@Size(min = 2, max = 250)
	private String				nome;

	public TipoObjeto() {
		super();
	}

	
	public TipoObjeto(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}


	public Integer getId() {
		return this.id;
	}

	
	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

}

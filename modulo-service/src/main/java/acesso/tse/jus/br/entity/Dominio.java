package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "dominio", schema = "admacesso")
@SequenceGenerator(name = "sq_dominio", sequenceName = "admacesso.sq_dominio", allocationSize = 1)
public class Dominio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_dominio")
	@Column(name = "sq_dominio", unique = true)
	private Integer	id;

	@Column(name = "no_dominio")
	@NotNull
	@Size(min = 3, max = 100)
	private String	nome;

	@Column(name = "cd_dominio")
	@NotNull
	@Size(min = 1, max = 100)
	private String	cd;

	@Column(name = "ds_dominio")
	@NotNull
	@Size(min = 1, max = 100)
	private String	descricao;
	
	  

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Dominio() {
		super();
	}

	public Dominio(Integer id, String nome, String cd, String descricao			
			) {
		super();
		this.id = id;
		this.nome = nome;
		this.cd = cd;
		this.descricao = descricao;
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

package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "funcionalidade", schema = "admacesso")
@SequenceGenerator(name = "sq_funcionalidade", sequenceName = "admacesso.sq_funcionalidade", allocationSize = 1)
public class Funcionalidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_funcionalidade")
	@Column(name = "sq_funcionalidade", unique = true)
	private Integer	id;

	@Column(name = "nm_funcionalidade")
	@NotNull
	@Size(min = 3, max = 50)
	private String	nome;

	@Column(name = "ds_funcionalidade")
	@NotNull
	@Size(min = 1, max = 200)
	private String	descricao;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)	
	@JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo", nullable = false)
	private Modulo modulo;

	public String getDescricao() {
		return descricao;
	}

	public Funcionalidade(Integer id, @NotNull @Size(min = 3, max = 50) String nome,
			@NotNull @Size(min = 1, max = 200) String descricao, Modulo modulo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.modulo = modulo;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionalidade() {
		super();
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

package acesso.tse.jus.br.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;
//import br.net.woodstock.rockframework.domain.persistence.AbstractIntegerEntity;

@Entity
@Table(name = "objeto", schema = "admacesso")
@SequenceGenerator(name = "sq_objeto", sequenceName = "admacesso.sq_objeto", allocationSize = 1)
public class Objeto {

	@SuppressWarnings("unused")
	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_objeto")
	@Column(name = "cd_objeto", unique = true)
	private Integer				id;

	@Column(name = "ds_tela")
	@NotNull
	@Size(min = 2, max = 250)
	private String				nome;

	
	  @OneToMany(fetch = FetchType.LAZY, mappedBy = "objeto")	 
	  private Set<ObjetoModulo>	modulos;
	  

	public Objeto() {
		super();
	}

	public Objeto(Integer id, String nome
			, Set<ObjetoModulo> modulos
			) {
		super();
		this.id = id;
		this.nome = nome;
		this.modulos = modulos;
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

	
	public Set<ObjetoModulo> getModulos() {
		return this.modulos;
	}

	public void setModulos(final Set<ObjetoModulo> modulos) {
		this.modulos = modulos;
	}
	
}

package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.AcessoConstants;

@Embeddable
@MappedSuperclass
public class MaquinaServidoraKey implements Serializable {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_trib", referencedColumnName = "cd_trib", nullable = false)
	@NotNull
	private Tribunal			tribunal;

	@Column(name = "alias")
	@NotNull
	@Size(min = 1, max = 20)
	private String				alias;

	public MaquinaServidoraKey() {
		super();
	}

	public MaquinaServidoraKey(final Tribunal tribunal, final String alias) {
		super();
		this.tribunal = tribunal;
		this.alias = alias;
	}

	public Tribunal getTribunal() {
		return this.tribunal;
	}

	public void setTribunal(final Tribunal tribunal) {
		this.tribunal = tribunal;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(final String alias) {
		this.alias = alias;
	}

	
	/*
	@Override
	public boolean equals(final Object obj) {
		if ((!(obj instanceof MaquinaServidoraKey)) || (!this.getClass().isAssignableFrom(obj.getClass()))) {
			return false;
		}
		MaquinaServidoraKey other = (MaquinaServidoraKey) obj;

		EqualsBuilder builder = new EqualsBuilder();
		builder.add(this.getTribunal(), other.getTribunal());
		builder.add(this.getAlias(), other.getAlias());

		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.add(this.getTribunal());
		builder.add(this.getAlias());
		return builder.getHashCode();
	}
    */
}

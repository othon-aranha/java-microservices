package acesso.tse.jus.br.entity;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import acesso.tse.jus.br.AcessoConstants;

@Embeddable
public class ManutencaoKey extends MaquinaServidoraKey {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo", nullable = false)
	@NotNull
	private Modulo				modulo;

	public ManutencaoKey() {
		super();
	}

	public ManutencaoKey(final Tribunal tribunal, final String alias, final Modulo modulo) {
		super(tribunal, alias);
		this.modulo = modulo;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(final Modulo modulo) {
		this.modulo = modulo;
	}

	/*
	@Override
	public boolean equals(final Object obj) {
		if ((!(obj instanceof ManutencaoKey)) || (!this.getClass().isAssignableFrom(obj.getClass()))) {
			return false;
		}
		ManutencaoKey other = (ManutencaoKey) obj;

		EqualsBuilder builder = new EqualsBuilder();
		builder.add(this.getTribunal(), other.getTribunal());
		builder.add(this.getAlias(), other.getAlias());
		builder.add(this.getModulo(), other.getModulo());

		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.add(this.getTribunal());
		builder.add(this.getAlias());
		builder.add(this.getModulo());
		return builder.getHashCode();
	}
	*/

}

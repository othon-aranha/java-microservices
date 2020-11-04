package acesso.tse.jus.br.entity;

import acesso.tse.jus.br.impl.LabelSupport;

public enum StatusModulo implements LabelSupport {

	/**
	 * @formatter:off
	 */
	DESENVOLVIMENTO("Desenvolvimento"),
	HOMOLOGACAO("Homologação"),
	PRODUCAO("Produção");

	/**
	 * @formatter:on
	 */

	private String	label;

	private StatusModulo(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

}

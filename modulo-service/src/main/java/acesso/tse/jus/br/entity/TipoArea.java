package acesso.tse.jus.br.entity;

import acesso.tse.jus.br.impl.LabelSupport;

public enum TipoArea implements LabelSupport {

	/**
	 * @formatter:off
	 */
	INVALIDO("Inválida"),
	INTERNO("Interno"),
	EXTERNO("Externo");
	/**
	 * @formatter:on
	 */

	private String	label;

	private TipoArea(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

}

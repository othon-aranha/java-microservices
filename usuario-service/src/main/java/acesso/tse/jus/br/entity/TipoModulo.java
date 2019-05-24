package acesso.tse.jus.br.entity;

import acesso.tse.jus.br.impl.LabelSupport;

public enum TipoModulo implements LabelSupport {

	/**
	 * @formatter:off
	 */
	INVALIDO("Inválido"),
	DESKTOP("Desktop"),
	WEB("Web"),
	HIBRIDO("Híbrido");

	/**
	 * @formatter:on
	 */

	private String	label;

	private TipoModulo(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

}

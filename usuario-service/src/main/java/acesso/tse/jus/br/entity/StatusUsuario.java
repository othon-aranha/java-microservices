package acesso.tse.jus.br.entity;

import acesso.tse.jus.br.impl.LabelSupport;

public enum StatusUsuario implements LabelSupport {

	/**
	 * @formatter:off
	 */
	INVALIDO("Inválido"),
	ATIVO("Ativo"),
	INATIVO("Inativo");

	/**
	 * @formatter:on
	 */

	private String	label;

	private StatusUsuario(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

}
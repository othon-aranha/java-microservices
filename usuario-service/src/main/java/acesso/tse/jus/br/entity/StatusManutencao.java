package acesso.tse.jus.br.entity;

import acesso.tse.jus.br.impl.LabelSupport;

public enum StatusManutencao implements LabelSupport {

	/**
	 * @formatter:off
	 */
	D("Disponibilizado"),
	A("Atualizado"),
	E("Erro");

	/**
	 * @formatter:on
	 */

	private String	label;

	private StatusManutencao(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

}
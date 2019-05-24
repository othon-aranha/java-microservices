package acesso.tse.jus.br.entity;

import acesso.tse.jus.br.impl.LabelSupport;

public enum TipoAtualizacao implements LabelSupport {

	/**
	 * @formatter:off
	 */
	NAO_ATUALIZA("Não Atualiza"),
	POR_VERSAO("Por Versão"),
	POR_DATA("Por Data");
	/**
	 * @formatter:on
	 */

	private String	label;

	private TipoAtualizacao(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

}

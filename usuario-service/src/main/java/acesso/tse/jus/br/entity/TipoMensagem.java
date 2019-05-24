package acesso.tse.jus.br.entity;

import acesso.tse.jus.br.impl.LabelSupport;

public enum TipoMensagem implements LabelSupport {

	/**
	 * @formatter:off
	 */
	INVALIDO("Inválido"),
	AVISO("Aviso"),
	SIM_NAO("Sim Não"),
	ERRO("Erro"),
	SIM_NAO_CANCELA("Sim Não Cancela");

	/**
	 * @formatter:on
	 */

	private String	label;

	private TipoMensagem(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

}

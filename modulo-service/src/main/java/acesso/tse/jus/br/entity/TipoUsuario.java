package acesso.tse.jus.br.entity;



public enum TipoUsuario  {

	/**
	 * @formatter:off
	 */
	INVALIDO("Inválido"),
	SERVIDOR("Servidor"),
	TERCEIRIZADO("Terceirizado"),
	APLICACAO("Aplicação"),
	AVULSO("Avulso"),
	SISTEMA("Sistema"),
	EXTERNO("Externo");

	/**
	 * @formatter:on
	 */

	private String	label;

	private TipoUsuario(final String label) {
		this.label = label;
	}

	
	public String getLabel() {
		return this.label;
	}

}

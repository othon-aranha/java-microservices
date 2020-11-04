package acesso.tse.jus.br;

public class AcessoException extends RuntimeException {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	public AcessoException(final String message) {
		super(message);
	}

	public AcessoException(final Throwable cause) {
		super(cause);
	}

	public AcessoException(final String message, final Throwable cause) {
		super(message, cause);
	}

}

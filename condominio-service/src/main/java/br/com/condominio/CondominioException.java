package br.com.condominio;

public class CondominioException extends RuntimeException {

	private static final long	serialVersionUID	= CondominioConstants.VERSAO;

	public CondominioException(final String message) {
		super(message);
	}

	public CondominioException(final Throwable cause) {
		super(cause);
	}

	public CondominioException(final String message, final Throwable cause) {
		super(message, cause);
	}

}

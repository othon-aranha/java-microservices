package acesso.tse.jus.br;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AcessoLog {

	private static final String	LOG_NAME	= "br.jus.tse.corporativo.acesso";

	private static Logger		log			= LoggerFactory.getLogger(AcessoLog.LOG_NAME);

	private AcessoLog() {
		super();
	}

	public static Logger getLog() {
		return log;
	}

}

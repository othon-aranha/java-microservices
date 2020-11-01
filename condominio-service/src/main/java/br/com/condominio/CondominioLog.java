package br.com.condominio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CondominioLog {

	private static final String	LOG_NAME	= "br.com.condominio";

	private static Logger		log			= LoggerFactory.getLogger(CondominioLog.LOG_NAME);

	private CondominioLog() {
		super();
	}

	public static Logger getLog() {
		return log;
	}

}

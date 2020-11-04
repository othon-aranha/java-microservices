package br.com.condominio.entity;

import br.com.condominio.impl.LabelSupport;

public enum SimNaoType implements LabelSupport {

	S("Sim"), N("Não");

	private String	label;

	private SimNaoType(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	public static SimNaoType toSimNaoType(final Boolean bool) {
		if (bool == null) {
			return null;
		}
		if (bool.booleanValue()) {
			return SimNaoType.S;
		}
		return SimNaoType.N;
	}

	public static Boolean toBoolean(final SimNaoType type) {
		if (type == null) {
			return null;
		}
		if (type == SimNaoType.S) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}

package br.com.condominio.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.condominio.entity.Locacao;
import br.com.condominio.resource.exception.FieldMessage;

public class LocacaoInsertValidator implements ConstraintValidator<LocacaoInsert, Locacao> {
	@Override
	public void initialize(LocacaoInsert ann) {

	}

	@Override
	public boolean isValid(Locacao objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

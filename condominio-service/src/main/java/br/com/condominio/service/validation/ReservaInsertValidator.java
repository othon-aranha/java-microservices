package br.com.condominio.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.condominio.entity.Reserva;
import br.com.condominio.resource.exception.FieldMessage;

public class ReservaInsertValidator implements ConstraintValidator<ReservaInsert, Reserva> {
	@Override
	public void initialize(ReservaInsert ann) {

	}

	@Override
	public boolean isValid(Reserva objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

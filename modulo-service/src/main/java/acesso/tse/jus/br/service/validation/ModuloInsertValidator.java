package acesso.tse.jus.br.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import acesso.tse.jus.br.dto.ModuloDTO;
import acesso.tse.jus.br.resource.exception.FieldMessage;

public class ModuloInsertValidator implements ConstraintValidator<ModuloInsert, ModuloDTO> {
	@Override
	public void initialize(ModuloInsert ann) {

	}

	@Override
	public boolean isValid(ModuloDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

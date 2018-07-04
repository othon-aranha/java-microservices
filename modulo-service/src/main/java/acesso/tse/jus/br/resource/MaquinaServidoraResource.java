package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.MaquinaServidora;

public class MaquinaServidoraResource extends Resource<MaquinaServidora> {
	
	public MaquinaServidoraResource(MaquinaServidora maquinaServidora) {
		super (maquinaServidora);
	}
	
}
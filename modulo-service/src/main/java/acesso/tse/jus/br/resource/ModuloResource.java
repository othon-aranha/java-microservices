package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Modulo;

public class ModuloResource extends Resource<Modulo> {
	
	public ModuloResource(Modulo modulo, Link... links) {
		super(modulo, links);
	}
	
}
package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Objeto;

public class ObjetoResource extends Resource<Objeto> {
	
	public ObjetoResource(Objeto objeto, Link... links) {
		super(objeto, links);
	}
	
}
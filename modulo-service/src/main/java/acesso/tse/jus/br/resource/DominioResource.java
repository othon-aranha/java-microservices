package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Dominio;

public class DominioResource extends Resource<Dominio> {
	
	public DominioResource(Dominio dominio, Link... links) {
		super(dominio, links);
	}
	
}
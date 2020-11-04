package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Grupo;

public class GrupoResource extends Resource<Grupo> {
	
	public GrupoResource(Grupo grupo, Link... links) {
		super(grupo, links);
	}
	
}
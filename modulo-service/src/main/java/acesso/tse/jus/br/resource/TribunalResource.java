package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Tribunal;

public class TribunalResource extends Resource<Tribunal> {
	
	public TribunalResource(Tribunal tribunal, Link... links) {
		super(tribunal, links);
	}
	
}
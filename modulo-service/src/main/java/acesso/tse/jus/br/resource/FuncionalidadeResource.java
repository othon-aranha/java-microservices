package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Funcionalidade;

public class FuncionalidadeResource extends Resource<Funcionalidade> {
	
	public FuncionalidadeResource(Funcionalidade funcionalidade, Link... links) {
		super(funcionalidade, links);
	}
	
}
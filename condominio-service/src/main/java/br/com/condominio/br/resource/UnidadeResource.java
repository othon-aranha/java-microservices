package br.com.condominio.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import br.com.condominio.entity.Unidade;

public class UnidadeResource extends Resource<Unidade> {
	
	public UnidadeResource(Unidade unidade, Link... links) {
		super(unidade, links);
	}
	
}
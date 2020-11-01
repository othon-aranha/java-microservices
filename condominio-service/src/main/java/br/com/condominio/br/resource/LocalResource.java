package br.com.condominio.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import br.com.condominio.entity.Local;

public class LocalResource extends Resource<Local> {
	
	public LocalResource(Local local, Link... links) {
		super(local, links);
	}
	
}
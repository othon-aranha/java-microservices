package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Servidor;

public class ServidorResource extends Resource<Servidor> {
	
	public ServidorResource(Servidor servidor, Link... links) {
		super(servidor, links);
	}
	
}
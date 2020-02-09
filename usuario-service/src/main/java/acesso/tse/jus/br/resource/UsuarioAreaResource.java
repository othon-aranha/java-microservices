package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.UsuarioArea;

public class UsuarioAreaResource extends Resource<UsuarioArea> {
	
	public UsuarioAreaResource(UsuarioArea usuarioarea, Link... links) {
		super(usuarioarea, links);
	}
	
}
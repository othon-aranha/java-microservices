package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Usuario;

public class UsuarioResource extends Resource<Usuario> {
	
	public UsuarioResource(Usuario usuario, Link... links) {
		super(usuario, links);
	}
	
}
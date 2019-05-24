package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Perfil;

public class PerfilResource extends Resource<Perfil> {
	
	public PerfilResource(Perfil perfil, Link... links) {
		super(perfil, links);
	}
	
}
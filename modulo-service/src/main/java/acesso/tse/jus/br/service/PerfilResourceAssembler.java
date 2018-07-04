package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Perfil;
import acesso.tse.jus.br.resource.PerfilResource;


public class PerfilResourceAssembler extends ResourceAssemblerSupport<Perfil, PerfilResource> {
	
	public PerfilResourceAssembler() {
		super(Perfil.class, PerfilResource.class);
	}

	
	@Override
	public PerfilResource toResource(Perfil perfil) {
		return new PerfilResource(perfil, linkTo(methodOn(PerfilRestController.class).get(perfil.getId())).withSelfRel());
	}
	
	
	@Override
	protected PerfilResource instantiateResource(Perfil perfil) {
		return new PerfilResource(perfil);
	}

}
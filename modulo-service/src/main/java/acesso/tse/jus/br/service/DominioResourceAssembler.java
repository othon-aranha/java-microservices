package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Dominio;
import acesso.tse.jus.br.resource.DominioResource;


public class DominioResourceAssembler extends ResourceAssemblerSupport<Dominio, DominioResource> {
	
	public DominioResourceAssembler() {
		super(Dominio.class, DominioResource.class);
	}

	
	@Override
	public DominioResource toResource(Dominio dominio) {
		return new DominioResource(dominio, linkTo(methodOn(ObjetoRestController.class).get(dominio.getId())).withSelfRel());
	}
	
	
	@Override
	protected DominioResource instantiateResource(Dominio dominio) {
		return new DominioResource(dominio);
	}

}
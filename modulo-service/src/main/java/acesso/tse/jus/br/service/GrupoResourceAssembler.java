package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Grupo;
import acesso.tse.jus.br.resource.GrupoResource;


public class GrupoResourceAssembler extends ResourceAssemblerSupport<Grupo, GrupoResource> {
	
	public GrupoResourceAssembler() {
		super(Grupo.class, GrupoResource.class);
	}

	
	@Override
	public GrupoResource toResource(Grupo grupo) {
		return new GrupoResource(grupo, linkTo(methodOn(ObjetoRestController.class).get(grupo.getId())).withSelfRel());
	}
	
	
	@Override
	protected GrupoResource instantiateResource(Grupo grupo) {
		return new GrupoResource(grupo);
	}

}
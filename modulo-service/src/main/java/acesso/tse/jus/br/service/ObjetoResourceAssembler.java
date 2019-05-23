package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Objeto;
import acesso.tse.jus.br.resource.ObjetoResource;


public class ObjetoResourceAssembler extends ResourceAssemblerSupport<Objeto, ObjetoResource> {
	
	public ObjetoResourceAssembler() {
		super(Objeto.class, ObjetoResource.class);
	}

	
	@Override
	public ObjetoResource toResource(Objeto objeto) {
		return new ObjetoResource(objeto, linkTo(methodOn(ObjetoRestController.class).get(objeto.getId())).withSelfRel());
	}
	
	
	@Override
	protected ObjetoResource instantiateResource(Objeto objeto) {
		return new ObjetoResource(objeto);
	}

}
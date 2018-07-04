package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Tribunal;
import acesso.tse.jus.br.resource.TribunalResource;


public class TribunalResourceAssembler extends ResourceAssemblerSupport<Tribunal, TribunalResource> {
	
	public TribunalResourceAssembler() {
		super(Tribunal.class, TribunalResource.class);
	}

	
	@Override
	public TribunalResource toResource(Tribunal tribunal) {
		return new TribunalResource(tribunal, linkTo(methodOn(TribunalRestController.class).get(tribunal.getId())).withSelfRel());
	}
	
	
	@Override
	protected TribunalResource instantiateResource(Tribunal tribunal) {
		return new TribunalResource(tribunal);
	}

}
package br.com.condominio.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import br.com.condominio.br.resource.LocalResource;
import br.com.condominio.entity.Local;


public class LocalResourceAssembler extends ResourceAssemblerSupport<Local, LocalResource> {
	
	public LocalResourceAssembler() {
		super(Local.class, LocalResource.class);
	}

	
	@Override
	public LocalResource toResource(Local local) {
		return new LocalResource(local, linkTo(methodOn(LocalRestController.class).get(local.getId())).withSelfRel());
	}
	
	
	@Override
	protected LocalResource instantiateResource(Local local) {
		return new LocalResource(local);
	}

}
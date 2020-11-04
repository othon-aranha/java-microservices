package br.com.condominio.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import br.com.condominio.br.resource.UnidadeResource;
import br.com.condominio.entity.Unidade;


public class UnidadeResourceAssembler extends ResourceAssemblerSupport<Unidade, UnidadeResource> {
	
	public UnidadeResourceAssembler() {
		super(Unidade.class, UnidadeResource.class);
	}

	
	@Override
	public UnidadeResource toResource(Unidade unidade) {
		return new UnidadeResource(unidade, linkTo(methodOn(UnidadeRestController.class).get(unidade.getId())).withSelfRel());
	}
	
	
	@Override
	protected UnidadeResource instantiateResource(Unidade unidade) {
		return new UnidadeResource(unidade);
	}

}
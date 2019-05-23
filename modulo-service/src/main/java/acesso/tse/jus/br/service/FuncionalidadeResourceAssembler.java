package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Funcionalidade;
import acesso.tse.jus.br.resource.FuncionalidadeResource;


public class FuncionalidadeResourceAssembler extends ResourceAssemblerSupport<Funcionalidade, FuncionalidadeResource> {
	
	public FuncionalidadeResourceAssembler() {
		super(Funcionalidade.class, FuncionalidadeResource.class);
	}

	
	@Override
	public FuncionalidadeResource toResource(Funcionalidade funcionalidade) {
		return new FuncionalidadeResource(funcionalidade, linkTo(methodOn(ObjetoRestController.class).get(funcionalidade.getId())).withSelfRel());
	}
	
	
	@Override
	protected FuncionalidadeResource instantiateResource(Funcionalidade funcionalidade) {
		return new FuncionalidadeResource(funcionalidade);
	}

}
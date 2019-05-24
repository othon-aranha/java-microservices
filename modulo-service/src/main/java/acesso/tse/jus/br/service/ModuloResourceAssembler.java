package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpStatus;

import acesso.tse.jus.br.entity.Modulo;
import acesso.tse.jus.br.resource.ModuloResource;


public class ModuloResourceAssembler extends ResourceAssemblerSupport<Modulo, ModuloResource> {
	
	public ModuloResourceAssembler() {
		super(Modulo.class, ModuloResource.class);
	}

	
	@Override
	public ModuloResource toResource(Modulo modulo) {
		return new ModuloResource(modulo, linkTo(methodOn(ModuloRestController.class).get(modulo.getId())).withSelfRel());
	}
	
	
	@Override
	protected ModuloResource instantiateResource(Modulo modulo) {
		return new ModuloResource(modulo);
	}

}


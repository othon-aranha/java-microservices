package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.UsuarioArea;
import acesso.tse.jus.br.resource.UsuarioAreaResource;


public class UsuarioAreaResourceAssembler extends ResourceAssemblerSupport<UsuarioArea, UsuarioAreaResource> {
	
	public UsuarioAreaResourceAssembler() {
		super(UsuarioArea.class, UsuarioAreaResource.class);
	}

	
	@Override
	public UsuarioAreaResource toResource(UsuarioArea usuarioarea) {
		return new UsuarioAreaResource(usuarioarea, linkTo(methodOn(UsuarioAreaRestController.class).get(usuarioarea.getId())).withSelfRel());
	}
	
	
	@Override
	protected UsuarioAreaResource instantiateResource(UsuarioArea usuarioarea) {
		return new UsuarioAreaResource(usuarioarea);
	}

}
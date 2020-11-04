package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Usuario;
import acesso.tse.jus.br.resource.UsuarioResource;


public class UsuarioResourceAssembler extends ResourceAssemblerSupport<Usuario, UsuarioResource> {
	
	public UsuarioResourceAssembler() {
		super(Usuario.class, UsuarioResource.class);
	}

	
	@Override
	public UsuarioResource toResource(Usuario usuario) {
		return new UsuarioResource(usuario, linkTo(methodOn(UsuarioRestController.class).get(usuario.getId())).withSelfRel());
	}
	
	
	@Override
	protected UsuarioResource instantiateResource(Usuario usuario) {
		return new UsuarioResource(usuario);
	}

}
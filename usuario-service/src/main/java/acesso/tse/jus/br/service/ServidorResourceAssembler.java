package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Servidor;
import acesso.tse.jus.br.resource.ServidorResource;


public class ServidorResourceAssembler extends ResourceAssemblerSupport<Servidor, ServidorResource> {
	
	public ServidorResourceAssembler() {
		super(Servidor.class, ServidorResource.class);
	}

	
	@Override
	public ServidorResource toResource(Servidor servidor) {
		return new ServidorResource(servidor, linkTo(methodOn(ServidorRestController.class).get(servidor.getId())).withSelfRel());
	}
	
	
	@Override
	protected ServidorResource instantiateResource(Servidor servidor) {
		return new ServidorResource(servidor);
	}

}
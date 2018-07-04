package acesso.tse.jus.br.service;



//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.MaquinaServidora;
import acesso.tse.jus.br.resource.MaquinaServidoraResource;


public class MaquinaServidoraResourceAssembler extends ResourceAssemblerSupport<MaquinaServidora, MaquinaServidoraResource> {
	
	public MaquinaServidoraResourceAssembler() {
		super(MaquinaServidora.class, MaquinaServidoraResource.class);
	}

	
	@Override
	public MaquinaServidoraResource toResource(MaquinaServidora maquinaServidora) {
		//return new MaquinaServidoraResource(maquinaServidora, linkTo(methodOn(MaquinaServidoraRestController.class).get(maquinaServidora.getId())).withSelfRel());
		return new MaquinaServidoraResource(maquinaServidora);
	}

	
	@Override
	protected MaquinaServidoraResource instantiateResource(MaquinaServidora maquinaServidora) {
		return new MaquinaServidoraResource(maquinaServidora);
	}

}


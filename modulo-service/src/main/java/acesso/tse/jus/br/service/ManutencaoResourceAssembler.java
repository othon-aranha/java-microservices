package acesso.tse.jus.br.service;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Manutencao;
import acesso.tse.jus.br.resource.ManutencaoResource;


public class ManutencaoResourceAssembler extends ResourceAssemblerSupport<Manutencao, ManutencaoResource> {
	
	public ManutencaoResourceAssembler() {
		super(Manutencao.class, ManutencaoResource.class);
	}

	
	@Override
	public ManutencaoResource toResource(Manutencao manutencao) {
		return new ManutencaoResource(manutencao);
	}
	
	
	@Override
	protected ManutencaoResource instantiateResource(Manutencao manutencao) {
		return new ManutencaoResource(manutencao);
	}

}


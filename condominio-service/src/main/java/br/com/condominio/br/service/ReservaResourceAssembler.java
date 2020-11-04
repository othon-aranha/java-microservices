package br.com.condominio.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import br.com.condominio.br.resource.ReservaResource;
import br.com.condominio.entity.Reserva;


public class ReservaResourceAssembler extends ResourceAssemblerSupport<Reserva, ReservaResource> {
	
	public ReservaResourceAssembler() {
		super(Reserva.class, ReservaResource.class);
	}

	
	@Override
	public ReservaResource toResource(Reserva reserva) {
		return new ReservaResource(reserva, linkTo(methodOn(ReservaRestController.class).get(reserva.getId())).withSelfRel());
	}
	
	
	@Override
	protected ReservaResource instantiateResource(Reserva reserva) {
		return new ReservaResource(reserva);
	}

}
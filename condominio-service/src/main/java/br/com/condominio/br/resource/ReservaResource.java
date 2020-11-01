package br.com.condominio.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import br.com.condominio.entity.Reserva;

public class ReservaResource extends Resource<Reserva> {
	
	public ReservaResource(Reserva reserva, Link... links) {
		super(reserva, links);
	}
	
}
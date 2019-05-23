package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Manutencao;

public class ManutencaoResource extends Resource<Manutencao> {
	
	public ManutencaoResource(Manutencao manutencao) {
		super (manutencao);
	}	
}
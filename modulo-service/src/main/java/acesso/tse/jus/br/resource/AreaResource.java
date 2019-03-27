package acesso.tse.jus.br.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import acesso.tse.jus.br.entity.Area;

public class AreaResource extends Resource<Area> {
	
	public AreaResource(Area area, Link... links) {
		super(area, links);
	}
	
}
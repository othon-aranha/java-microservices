package acesso.tse.jus.br.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import acesso.tse.jus.br.entity.Area;
import acesso.tse.jus.br.resource.AreaResource;


public class AreaResourceAssembler extends ResourceAssemblerSupport<Area, AreaResource> {
	
	public AreaResourceAssembler() {
		super(Area.class, AreaResource.class);
	}

	
	@Override
	public AreaResource toResource(Area area) {
		return new AreaResource(area, linkTo(methodOn(AreaRestController.class).get(area.getId())).withSelfRel());
	}
	
	
	@Override
	protected AreaResource instantiateResource(Area area) {
		return new AreaResource(area);
	}

}
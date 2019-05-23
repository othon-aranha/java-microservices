package acesso.tse.jus.br.service;
	
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import acesso.tse.jus.br.entity.Area;
import acesso.tse.jus.br.repository.AreaRepository;
import acesso.tse.jus.br.repository.DominioRepository;
import acesso.tse.jus.br.resource.AreaResource;



@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/area")
public class AreaRestController {

	@Autowired
	AreaRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	AreaResourceAssembler assembler = new AreaResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/areas")
	public ResponseEntity<List<AreaResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	// @Transactional
	@GetMapping("/{id}")
	public ResponseEntity<AreaResource> get(@PathVariable Integer id) {
		Area parea = repository.findOne(id);
		if (parea != null) {			
			return new ResponseEntity<>(assembler.toResource(parea), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<AreaResource> create(@RequestBody Area area) {
		Area parea = repository.save(area);
		if (parea != null) {
			return new ResponseEntity<>(assembler.toResource(parea), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<AreaResource> update(@PathVariable Integer id, @RequestBody Area area) {
		Area parea = repository.findOne(id); 
		if ( parea != null) {
			area.setId(parea.getId()); 
			parea = repository.save(area);
			return new ResponseEntity<>(assembler.toResource(parea), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<AreaResource> delete(@PathVariable Integer id) {
		Area parea = repository.findOne(id);
		if ( parea != null) {
			repository.delete(parea);
			return new ResponseEntity<>(assembler.toResource(parea), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/sigla/{sigla}")
	public ResponseEntity<AreaResource> findBysiglaIgnoreCase(@PathVariable String sigla, Pageable pageable) {
		return new ResponseEntity<>(assembler.toResource(repository.findBysiglaIgnoreCase(sigla)), HttpStatus.OK);
	}	
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<AreaResource>> findBynomeIgnoreCaseContaining(@PathVariable String nome, Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findBynomeIgnoreCaseContaining(nome, pageable)), HttpStatus.OK);
	}	
	
}
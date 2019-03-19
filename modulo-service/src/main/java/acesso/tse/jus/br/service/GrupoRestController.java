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

import acesso.tse.jus.br.entity.Grupo;
import acesso.tse.jus.br.repository.GrupoRepository;
import acesso.tse.jus.br.resource.GrupoResource;



@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/grupo")
public class GrupoRestController {

	@Autowired
	GrupoRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	GrupoResourceAssembler assembler = new GrupoResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/grupos")
	public ResponseEntity<List<GrupoResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GrupoResource> get(@PathVariable Integer id) {
		Grupo grupo = repository.findOne(id);
		if (grupo != null) {			
			return new ResponseEntity<>(assembler.toResource(grupo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<GrupoResource> create(@RequestBody Grupo grupo) {
		grupo = repository.save(grupo);
		if (grupo != null) {
			return new ResponseEntity<>(assembler.toResource(grupo), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GrupoResource> update(@PathVariable Integer id, @RequestBody Grupo grupo) {
		Grupo pgrupo = repository.findOne(id); 
		if ( pgrupo != null) {
			grupo.setId(pgrupo.getId()); 
			pgrupo = repository.save(grupo);
			return new ResponseEntity<>(assembler.toResource(pgrupo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GrupoResource> delete(@PathVariable Integer id) {
		Grupo pgrupo = repository.findOne(id);
		if (pgrupo != null) {
			repository.delete(pgrupo);
			return new ResponseEntity<>(assembler.toResource(pgrupo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<GrupoResource>> findBynomeIgnoreCaseContaining(@PathVariable String nome, Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findBynomeIgnoreCaseContaining(nome, pageable)), HttpStatus.OK);
	}	
	

	
}
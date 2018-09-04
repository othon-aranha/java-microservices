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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import acesso.tse.jus.br.entity.Dominio;
import acesso.tse.jus.br.repository.DominioRepository;
import acesso.tse.jus.br.resource.DominioResource;



@RestController
@RequestMapping("/dominio")
public class DominioRestController {

	@Autowired
	DominioRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	DominioResourceAssembler assembler = new DominioResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/dominios")
	public ResponseEntity<List<DominioResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DominioResource> get(@PathVariable Integer id) {
		Dominio dominio = repository.findOne(id);
		if (dominio != null) {			
			return new ResponseEntity<>(assembler.toResource(dominio), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<DominioResource> create(@RequestBody Dominio dominio) {
		dominio = repository.save(dominio);
		if (dominio != null) {
			return new ResponseEntity<>(assembler.toResource(dominio), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DominioResource> update(@PathVariable Integer id, @RequestBody Dominio dominio) {
		Dominio pdominio = repository.findOne(id); 
		if ( pdominio != null) {
			dominio.setId(pdominio.getId()); 
			pdominio = repository.save(dominio);
			return new ResponseEntity<>(assembler.toResource(pdominio), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DominioResource> delete(@PathVariable Integer id) {
		Dominio dominio = repository.findOne(id);
		if (dominio != null) {
			repository.delete(dominio);
			return new ResponseEntity<>(assembler.toResource(dominio), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<DominioResource>> findBynomeIgnoreCaseContaining(@PathVariable String nome, Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findBynomeIgnoreCaseContaining(nome, pageable)), HttpStatus.OK);
	}	
	

	
}
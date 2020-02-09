package acesso.tse.jus.br.service;

import java.util.List;

import java.net.URI;

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

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import acesso.tse.jus.br.entity.Objeto;
import acesso.tse.jus.br.repository.ObjetoRepository;
import acesso.tse.jus.br.resource.ObjetoResource;



@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/objeto")
public class ObjetoRestController {

	@Autowired
	ObjetoRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	ObjetoResourceAssembler assembler = new ObjetoResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/objetos")
	public ResponseEntity<List<ObjetoResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ObjetoResource> get(@PathVariable Integer id) {
		Objeto objeto = repository.findOne(id);
		if (objeto != null) {			
			return new ResponseEntity<>(assembler.toResource(objeto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<ObjetoResource> create(@RequestBody Objeto objeto) {
		objeto = repository.save(objeto);
		// URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(objeto.getId()).toUri();
		
		return new ResponseEntity<>(assembler.toResource(objeto), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ObjetoResource> update(@PathVariable Integer id, @RequestBody Objeto objeto) {
		Objeto pobjeto = repository.findOne(id); 
		if ( pobjeto != null) {
			objeto.setId(pobjeto.getId()); 
			pobjeto = repository.save(objeto);
			return new ResponseEntity<>(assembler.toResource(pobjeto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ObjetoResource> delete(@PathVariable Integer id) {
		Objeto objeto = repository.findOne(id);
		if (objeto != null) {
			repository.delete(objeto);
			return new ResponseEntity<>(assembler.toResource(objeto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ObjetoResource>> findBynomeIgnoreCaseContaining(@PathVariable String nome, Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findBynomeIgnoreCaseContaining(nome, pageable)), HttpStatus.OK);
	}	
	

	
}
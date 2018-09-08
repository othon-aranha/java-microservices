package acesso.tse.jus.br.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
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

import acesso.tse.jus.br.entity.Tribunal;
import acesso.tse.jus.br.repository.TribunalRepository;
import acesso.tse.jus.br.resource.TribunalResource;



@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/tribunal")
public class TribunalRestController {

	@Autowired
	TribunalRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	TribunalResourceAssembler assembler = new TribunalResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/tribunais")
	public ResponseEntity<List<TribunalResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TribunalResource> get(@PathVariable Integer id) {
		Tribunal tribunal = repository.findOne(id);
		if (tribunal != null) {			
			return new ResponseEntity<>(assembler.toResource(tribunal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<TribunalResource> create(@RequestBody Tribunal tribunal) {
		tribunal = repository.save(tribunal);
		if (tribunal != null) {
			return new ResponseEntity<>(assembler.toResource(tribunal), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TribunalResource> update(@PathVariable Integer id, @RequestBody Tribunal tribunal) {
		Tribunal ptribunal = repository.findOne(id); 
		if ( ptribunal != null) {
			tribunal.setId(ptribunal.getId()); 
			ptribunal = repository.save(tribunal);
			return new ResponseEntity<>(assembler.toResource(ptribunal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TribunalResource> delete(@PathVariable Integer id) {
		Tribunal tribunal = repository.findOne(id);
		if (tribunal != null) {
			repository.delete(tribunal);
			return new ResponseEntity<>(assembler.toResource(tribunal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}	

	
	@GetMapping("/sigla/{sigla}")
	public ResponseEntity<TribunalResource> findBysigla(@PathVariable String sigla) {
		Tribunal tribunal = repository.findBysigla(sigla);
		if (tribunal != null) {			
			return new ResponseEntity<>(assembler.toResource(tribunal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
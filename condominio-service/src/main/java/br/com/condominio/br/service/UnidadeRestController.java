package br.com.condominio.br.service;
	
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
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

import br.com.condominio.br.resource.UnidadeResource;
import br.com.condominio.entity.Unidade;
import br.com.condominio.repository.UnidadeRepository;



@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/unidade")
public class UnidadeRestController {

	@Autowired
	UnidadeRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean @Primary
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	UnidadeResourceAssembler assembler = new UnidadeResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/unidades")
	public ResponseEntity<List<UnidadeResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	// @Transactional
	@GetMapping("/{id}")
	public ResponseEntity<UnidadeResource> get(@PathVariable Integer id) {
		Unidade unidade = repository.findOne(id);
		if (unidade != null) {			
			return new ResponseEntity<>(assembler.toResource(unidade), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<UnidadeResource> create(@RequestBody Unidade unidade) {
		unidade = repository.save(unidade);
		if (unidade != null) {
			return new ResponseEntity<>(assembler.toResource(unidade), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<UnidadeResource> update(@PathVariable Integer id, @RequestBody Unidade unidade) {
		Unidade punidade = repository.findOne(id); 
		if ( punidade != null) {
			unidade.setId(punidade.getId()); 
			punidade = repository.save(unidade);
			return new ResponseEntity<>(assembler.toResource(punidade), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<UnidadeResource> delete(@PathVariable Integer id) {
		Unidade unidade = repository.findOne(id);
		if (unidade != null) {
			repository.delete(unidade);
			return new ResponseEntity<>(assembler.toResource(unidade), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	

	
}
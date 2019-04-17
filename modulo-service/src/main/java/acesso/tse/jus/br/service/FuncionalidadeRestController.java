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

import acesso.tse.jus.br.entity.Funcionalidade;
import acesso.tse.jus.br.repository.FuncionalidadeRepository;
import acesso.tse.jus.br.resource.FuncionalidadeResource;



@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/funcionalidade")
public class FuncionalidadeRestController {

	@Autowired
	FuncionalidadeRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	FuncionalidadeResourceAssembler assembler = new FuncionalidadeResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/funcionalidades")
	public ResponseEntity<List<FuncionalidadeResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	// @Transactional
	@GetMapping("/{id}")
	public ResponseEntity<FuncionalidadeResource> get(@PathVariable Integer id) {
		Funcionalidade func = repository.findOne(id);
		if (func != null) {			
			return new ResponseEntity<>(assembler.toResource(func), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<FuncionalidadeResource> create(@RequestBody Funcionalidade func) {
		Funcionalidade pfunc = repository.save(func);
		if (pfunc != null) {
			return new ResponseEntity<>(assembler.toResource(func), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<FuncionalidadeResource> update(@PathVariable Integer id, @RequestBody Funcionalidade func) {
		Funcionalidade pfunc = repository.findOne(id); 
		if ( pfunc != null) {
			func.setId(pfunc.getId()); 
			pfunc = repository.save(func);
			return new ResponseEntity<>(assembler.toResource(pfunc), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<FuncionalidadeResource> delete(@PathVariable Integer id) {
		Funcionalidade func = repository.findOne(id);
		if ( func != null) {
			repository.delete(func);
			return new ResponseEntity<>(assembler.toResource(func), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<FuncionalidadeResource>> findBynomeIgnoreCaseContaining(@PathVariable String nome, Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findBynomeIgnoreCaseContaining(nome, pageable)), HttpStatus.OK);
	}	
	
}
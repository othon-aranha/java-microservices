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

import br.com.condominio.br.resource.LocalResource;
import br.com.condominio.entity.Local;
import br.com.condominio.repository.LocalRepository;



@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:8100"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/local")
public class LocalRestController {

	@Autowired
	LocalRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean @Primary
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	LocalResourceAssembler assembler = new LocalResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/locais")
	public ResponseEntity<List<LocalResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	// @Transactional
	@GetMapping("/{id}")
	public ResponseEntity<LocalResource> get(@PathVariable Integer id) {
		Local local = repository.findOne(id);
		if (local != null) {			
			return new ResponseEntity<>(assembler.toResource(local), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<LocalResource> create(@RequestBody Local local) {
		local = repository.save(local);
		if (local != null) {
			return new ResponseEntity<>(assembler.toResource(local), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<LocalResource> update(@PathVariable Integer id, @RequestBody Local local) {
		Local plocal = repository.findOne(id); 
		if ( plocal != null) {
			local.setId(plocal.getId()); 
			plocal = repository.save(local);
			return new ResponseEntity<>(assembler.toResource(plocal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<LocalResource> delete(@PathVariable Integer id) {
		Local local = repository.findOne(id);
		if (local != null) {
			repository.delete(local);
			return new ResponseEntity<>(assembler.toResource(local), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	

	
}
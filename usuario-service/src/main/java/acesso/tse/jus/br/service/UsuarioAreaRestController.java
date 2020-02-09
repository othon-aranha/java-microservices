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

import acesso.tse.jus.br.entity.UsuarioArea;
import acesso.tse.jus.br.repository.UsuarioAreaRepository;
import acesso.tse.jus.br.resource.UsuarioAreaResource;



@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, allowCredentials="true",  methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/usuarioarea")
public class UsuarioAreaRestController {

	@Autowired
	UsuarioAreaRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	UsuarioAreaResourceAssembler assembler = new UsuarioAreaResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/usuarioareas")
	public ResponseEntity<List<UsuarioAreaResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioAreaResource> get(@PathVariable Integer id) {
		UsuarioArea usuarioarea = repository.findOne(id);
		if (usuarioarea != null) {			
			return new ResponseEntity<>(assembler.toResource(usuarioarea), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("usuarioareas/login/{login}")
	public ResponseEntity<List<UsuarioAreaResource>> findBylogin(@PathVariable String login) {
		return new ResponseEntity<>(assembler.toResources(repository.findBylogin(login)), HttpStatus.OK);
	}
	
	@GetMapping("/usuarioareas/area/{area}")
	public ResponseEntity<List<UsuarioAreaResource>> findByarea(@PathVariable String area) {
		return new ResponseEntity<>(assembler.toResources(repository.findByarea(area)), HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<UsuarioAreaResource> create(@RequestBody UsuarioArea usuarioarea) {
		usuarioarea = repository.save(usuarioarea);
		if (usuarioarea != null) {
			return new ResponseEntity<>(assembler.toResource(usuarioarea), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioAreaResource> update(@PathVariable Integer id, @RequestBody UsuarioArea usuarioarea) {
		UsuarioArea pusuarioarea = repository.findOne(id); 
		if ( pusuarioarea != null) {
			usuarioarea.setId(pusuarioarea.getId()); 
			pusuarioarea = repository.save(usuarioarea);
			return new ResponseEntity<>(assembler.toResource(pusuarioarea), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioAreaResource> delete(@PathVariable Integer id) {
		UsuarioArea usuarioarea = repository.findOne(id);
		if (usuarioarea != null) {
			repository.delete(usuarioarea);
			return new ResponseEntity<>(assembler.toResource(usuarioarea), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}	
	
	
}
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

import acesso.tse.jus.br.entity.Perfil;
import acesso.tse.jus.br.repository.PerfilRepository;
import acesso.tse.jus.br.resource.PerfilResource;



@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/perfil")
public class PerfilRestController {

	@Autowired
	PerfilRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	PerfilResourceAssembler assembler = new PerfilResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/perfis")
	public ResponseEntity<List<PerfilResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@GetMapping("/perfis/{sigla}")
	public ResponseEntity<List<PerfilResource>> findBySiglaModulo(@PathVariable String sigla) {
		return new ResponseEntity<>(assembler.toResources(repository.findBySiglaModulo(sigla)), HttpStatus.OK);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<PerfilResource> get(@PathVariable String id) {
		Perfil perfil = repository.findOne(id);
		if (perfil != null) {			
			return new ResponseEntity<>(assembler.toResource(perfil), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<PerfilResource> create(@RequestBody Perfil perfil) {
		perfil = repository.save(perfil);
		if (perfil != null) {
			return new ResponseEntity<>(assembler.toResource(perfil), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PerfilResource> update(@PathVariable String id, @RequestBody Perfil perfil) {
		Perfil pperfil = repository.findOne(id); 
		if ( pperfil != null) {
			perfil.setId(pperfil.getId()); 
			pperfil = repository.save(perfil);
			return new ResponseEntity<>(assembler.toResource(pperfil), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PerfilResource> delete(@PathVariable String id) {
		Perfil perfil = repository.findOne(id);
		if (perfil != null) {
			repository.delete(perfil);
			return new ResponseEntity<>(assembler.toResource(perfil), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}	

	
}
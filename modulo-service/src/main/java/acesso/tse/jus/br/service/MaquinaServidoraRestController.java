package acesso.tse.jus.br.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
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

import acesso.tse.jus.br.entity.MaquinaServidora;
import acesso.tse.jus.br.entity.MaquinaServidoraPK;
import acesso.tse.jus.br.repository.MaquinaServidoraRepository;
import acesso.tse.jus.br.resource.MaquinaServidoraResource;



@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/alias")
public class MaquinaServidoraRestController {

	@Autowired
	MaquinaServidoraRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	MaquinaServidoraResourceAssembler assembler = new MaquinaServidoraResourceAssembler();
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/aliases")
	public ResponseEntity<List<MaquinaServidoraResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@GetMapping("/cdTrib/{cdTrib}")
	public ResponseEntity<List<MaquinaServidoraResource>> findBycdTrib(@PathVariable Integer cdTrib) {
		return new ResponseEntity<>(assembler.toResources(repository.findBycdTrib(cdTrib)), HttpStatus.OK);
	}	
	
	@GetMapping("/cdTrib/{cdTrib}/alias/{alias}")
	public ResponseEntity<List<MaquinaServidoraResource>> findBycdTribAndalias(@PathVariable Integer cdTrib,@PathVariable String alias) {
		return new ResponseEntity<>(assembler.toResources(repository.findBycdTribAndalias(cdTrib,alias)), HttpStatus.OK);
	}
	
	@GetMapping("/cdModulo/{cdModulo}")
	public ResponseEntity<List<MaquinaServidoraResource>> findBycdModulo(@PathVariable Integer cdModulo) {
		return new ResponseEntity<>(assembler.toResources(repository.findBycdModulo(cdModulo)), HttpStatus.OK);
	}	
		
	
	@Transactional(timeout = 10)
	@GetMapping("/{id}")
	public ResponseEntity<MaquinaServidoraResource> get(@PathVariable MaquinaServidoraPK maquinaServidoraPK) {
		MaquinaServidora maquinaServidora = repository.findOne(maquinaServidoraPK);
		if (maquinaServidora != null) {			
			return new ResponseEntity<>(assembler.toResource(maquinaServidora), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	
	@Transactional(timeout = 10)
	@PostMapping
	public ResponseEntity<MaquinaServidoraResource> create(@RequestBody MaquinaServidora maquinaServidora) {
		maquinaServidora = repository.save(maquinaServidora);
		if (maquinaServidora != null) {
			return new ResponseEntity<>(assembler.toResource(maquinaServidora), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 10)
	@PutMapping("/{id}")
	public ResponseEntity<MaquinaServidoraResource> update(@PathVariable MaquinaServidoraPK id, @RequestBody MaquinaServidora maquinaServidora) {
		MaquinaServidora pmaquinaServidora = repository.findOne(id); 
		if ( pmaquinaServidora != null) {
			maquinaServidora.setId(pmaquinaServidora.getId()); 
			pmaquinaServidora = repository.save(maquinaServidora);
			return new ResponseEntity<>(assembler.toResource(pmaquinaServidora), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 10)
	@DeleteMapping("/{id}")
	public ResponseEntity<MaquinaServidoraResource> delete(@PathVariable MaquinaServidoraPK id) {
		MaquinaServidora maquinaServidora = repository.findOne(id);
		if (maquinaServidora != null) {
			repository.delete(maquinaServidora);
			return new ResponseEntity<>(assembler.toResource(maquinaServidora), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}		

	
}
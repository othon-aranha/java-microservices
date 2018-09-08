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

import acesso.tse.jus.br.entity.Manutencao;
import acesso.tse.jus.br.entity.ManutencaoKey;
import acesso.tse.jus.br.repository.ManutencaoRepository;
import acesso.tse.jus.br.resource.ManutencaoResource;



@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/manutencao")
public class ManutencaoRestController {

	@Autowired
	ManutencaoRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	ManutencaoResourceAssembler assembler = new ManutencaoResourceAssembler();
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/manutencoes")
	public ResponseEntity<List<ManutencaoResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@GetMapping("/sigla/{sigla}")
	public ResponseEntity<List<ManutencaoResource>> findBysigla(@PathVariable String sigla) {
		return new ResponseEntity<>(assembler.toResources(repository.findBysigla(sigla)), HttpStatus.OK);	
	}	
	
	
	
	@Transactional(timeout = 10)
	@GetMapping("/{id}")
	public ResponseEntity<ManutencaoResource> get(@PathVariable ManutencaoKey manutencaoKey) {
		Manutencao pmanutencao = repository.findOne(manutencaoKey);
		if (pmanutencao != null) {			
			return new ResponseEntity<>(assembler.toResource(pmanutencao), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	
	@Transactional(timeout = 10)
	@PostMapping
	public ResponseEntity<ManutencaoResource> create(@RequestBody Manutencao manutencao) {
		Manutencao pmanutencao = repository.save(manutencao);
		if (pmanutencao != null) {
			return new ResponseEntity<>(assembler.toResource(pmanutencao), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 10)
	@PutMapping("/{id}")
	public ResponseEntity<ManutencaoResource> update(@PathVariable ManutencaoKey id, @RequestBody Manutencao Manutencao) {
		Manutencao pManutencao = repository.findOne(id); 
		if ( pManutencao != null) {
			Manutencao.setId(pManutencao.getId()); 
			pManutencao = repository.save(Manutencao);
			return new ResponseEntity<>(assembler.toResource(pManutencao), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 10)
	@DeleteMapping("/{id}")
	public ResponseEntity<ManutencaoResource> delete(@PathVariable ManutencaoKey id) {
		Manutencao manutencao = repository.findOne(id);
		if (manutencao != null) {
			repository.delete(manutencao);
			return new ResponseEntity<>(assembler.toResource(manutencao), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}		

	
}
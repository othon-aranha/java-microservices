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

import br.com.condominio.br.resource.ReservaResource;
import br.com.condominio.entity.Reserva;
import br.com.condominio.repository.ReservaRepository;



@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/reserva")
public class ReservaRestController {

	@Autowired
	ReservaRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean @Primary
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	ReservaResourceAssembler assembler = new ReservaResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/reservas")
	public ResponseEntity<List<ReservaResource>> getAll(Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findAll(pageable)), HttpStatus.OK);
	}
	
	@GetMapping("/anoMes/{anoMes}/local/{local}")
	public ResponseEntity<List<ReservaResource>> findByanoMesAndlocal(@PathVariable String anoMes,@PathVariable Integer local) {
		return new ResponseEntity<>(assembler.toResources(repository.findByanoMesAndlocal(anoMes, local )), HttpStatus.OK);
	}
	
	// @Transactional
	@GetMapping("/{id}")
	public ResponseEntity<ReservaResource> get(@PathVariable Integer id) {
		Reserva reserva = repository.findOne(id);
		if (reserva != null) {			
			return new ResponseEntity<>(assembler.toResource(reserva), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<ReservaResource> create(@RequestBody Reserva reserva) {
		reserva = repository.save(reserva);
		if (reserva != null) {
			return new ResponseEntity<>(assembler.toResource(reserva), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ReservaResource> update(@PathVariable Integer id, @RequestBody Reserva reserva) {
		Reserva preserva = repository.findOne(id); 
		if ( preserva != null) {
			reserva.setId(preserva.getId()); 
			preserva = repository.save(reserva);
			return new ResponseEntity<>(assembler.toResource(preserva), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<ReservaResource> delete(@PathVariable Integer id) {
		Reserva reserva = repository.findOne(id);
		if (reserva != null) {
			repository.delete(reserva);
			return new ResponseEntity<>(assembler.toResource(reserva), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	

	
}
package acesso.tse.jus.br.service;

import java.util.List;

import javax.annotation.PostConstruct;

//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

import com.google.gson.Gson;

import acesso.tse.jus.br.dto.ModuloDTO;
import acesso.tse.jus.br.entity.Modulo;
import acesso.tse.jus.br.entity.SimNaoType;
import acesso.tse.jus.br.entity.StatusModulo;
import acesso.tse.jus.br.entity.TipoModulo;
import acesso.tse.jus.br.impl.ModuloRepositoryCustomImpl;
import acesso.tse.jus.br.entity.TipoAtualizacao;
import acesso.tse.jus.br.repository.ModuloRepository;
import acesso.tse.jus.br.resource.ModuloResource;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/modulo")
public class ModuloRestController {

	@Autowired
	ModuloRepository repository;
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	ModuloResourceAssembler assembler = new ModuloResourceAssembler();
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/modulos")
	public ResponseEntity<List<ModuloResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}	
	
	@PostMapping("/modulos/filtrar")
	public ResponseEntity<List<ModuloResource>> get(@RequestBody(required=true) String body) {
		ModuloDTO modulo = new Gson().fromJson(body, ModuloDTO.class);
		new ModuloRepositoryCustomImpl();
		Specification<Modulo> spec = ModuloRepositoryCustomImpl.moduloByModuloDTO(modulo);
		return new ResponseEntity<>(assembler.toResources(repository.findAll(spec)), HttpStatus.OK);
	}
	
	
	@Transactional(timeout = 100)
	@GetMapping("/{id}")
	public ResponseEntity<ModuloResource> get(@PathVariable Integer id) {
		Modulo modulo = repository.findOne(id);
		if (modulo != null) {			
			return new ResponseEntity<>(assembler.toResource(modulo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional(timeout = 100)
	@PostMapping
	public ResponseEntity<ModuloResource> create(@RequestBody Modulo modulo) {
		modulo = repository.save(modulo);
		if (modulo != null) {
			return new ResponseEntity<>(assembler.toResource(modulo), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 100)
	@PutMapping("/{id}")
	public ResponseEntity<ModuloResource> update(@PathVariable Integer id, @RequestBody Modulo modulo) {
		Modulo pmodulo = repository.findOne(id); 
		if ( pmodulo != null) {
			modulo.setId(pmodulo.getId());
			pmodulo = repository.save(modulo);
			return new ResponseEntity<>(assembler.toResource(pmodulo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 100)
	@DeleteMapping("/{id}")
	public ResponseEntity<ModuloResource> delete(@PathVariable Integer id) {
		Modulo modulo = repository.findOne(id);
		if (modulo != null) {
			repository.delete(modulo);
			return new ResponseEntity<>(assembler.toResource(modulo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}	

	
	@GetMapping("/sigla/{sigla}")
	public ResponseEntity<ModuloResource> findBysigla(@PathVariable String sigla) {
		Modulo modulo = repository.findBysigla(sigla);
		if (modulo != null) {			
			return new ResponseEntity<>(assembler.toResource(modulo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@GetMapping("/statusModulo/{statusModulo}")
	public ResponseEntity<List<ModuloResource>> findBystatusModulo(@PathVariable StatusModulo statusModulo) {
		return new ResponseEntity<>(assembler.toResources(repository.findBystatusModulo(statusModulo)), HttpStatus.OK);	
	}

	@GetMapping("/controlaAcesso/{controlaAcesso}")
	public ResponseEntity<List<ModuloResource>> findBycontrolaAcesso(@PathVariable SimNaoType controlaAcesso) {
		return new ResponseEntity<>(assembler.toResources(repository.findBycontrolaAcesso(controlaAcesso)), HttpStatus.OK);	
	}	
	
	
	@GetMapping("/tipoModulo/{tipoModulo}")
	public ResponseEntity<List<ModuloResource>> findBytipoModulo(@PathVariable List<TipoModulo> tipoModulo) {		
		return new ResponseEntity<>(assembler.toResources(repository.findBytipoModuloIn(tipoModulo)), HttpStatus.OK);		
	}
	
	@GetMapping("/tipoAtualizacao/{tipoAtualizacao}")
	public ResponseEntity<List<ModuloResource>> findBytipoAtualizacao(@PathVariable String tipoAtualizacao) {		
		return new ResponseEntity<>(assembler.toResources(repository.findBytipoAtualizacao(TipoAtualizacao.valueOf(tipoAtualizacao))), HttpStatus.OK);		
	}	
	
	@GetMapping("/loginusuario/{login}")
	public ResponseEntity<List<ModuloResource>> findByLoginUsuario(@PathVariable String login, Pageable pageable) {
		return new ResponseEntity<>(assembler.toResources(repository.findByLoginUsuario(login, pageable)), HttpStatus.OK);	
	}	
	
}
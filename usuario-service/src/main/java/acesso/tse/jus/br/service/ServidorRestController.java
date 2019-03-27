package acesso.tse.jus.br.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
// import org.springframework.data.jpa.domain.Specification;
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

// import com.google.gson.Gson;


// import acesso.tse.jus.br.dto.UsuarioDTO;

import acesso.tse.jus.br.entity.StatusUsuario;
import acesso.tse.jus.br.entity.TipoUsuario;
import acesso.tse.jus.br.entity.Servidor;
import acesso.tse.jus.br.repository.ServidorRepository;
// import acesso.tse.jus.br.impl.UsuarioRepositoryCustomImpl;
import acesso.tse.jus.br.repository.UsuarioRepository;

import acesso.tse.jus.br.resource.ServidorResource;



@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials="true",  methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/servidor")
public class ServidorRestController {

	@Autowired
	ServidorRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	ServidorResourceAssembler assembler = new ServidorResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/servidores")
	public ResponseEntity<List<ServidorResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	
	/*
	 @PostMapping("/usuarios/filtrar")
	 
	public ResponseEntity<List<UsuarioResource>> get(@RequestBody(required=true) String body) {
		UsuarioDTO usuario = new Gson().fromJson(body, UsuarioDTO.class);
		new UsuarioRepositoryCustomImpl();
		Specification<Usuario> spec = UsuarioRepositoryCustomImpl.usuarioByUsuarioDTO(usuario);
		return new ResponseEntity<>(assembler.toResources(repository.findAll(spec)), HttpStatus.OK);
	}
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<ServidorResource> get(@PathVariable String id) {
		return new ResponseEntity<>(assembler.toResource(repository.findOne(id)), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ServidorResource> create(@RequestBody Servidor servidor) {
		servidor = repository.save(servidor);
		if (servidor != null) {
			return new ResponseEntity<>(assembler.toResource(servidor), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ServidorResource> update(@PathVariable String id, @RequestBody Servidor servidor) {
		Servidor pservidor = repository.findOne(id);
		if (pservidor != null) {
			servidor.setId(pservidor.getId());
			pservidor = repository.save(servidor);
			return new ResponseEntity<>(assembler.toResource(servidor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ServidorResource> delete(@PathVariable String id) {
		Servidor servidor = repository.findOne(id);
		if (servidor != null) {
			repository.delete(servidor);
			return new ResponseEntity<>(assembler.toResource(servidor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	

	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<ServidorResource> findBymatricula(@PathVariable String matricula) {
		Servidor servidor = repository.findBymatricula(matricula);
		if (servidor != null) {			
			return new ResponseEntity<>(assembler.toResource(servidor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	
}
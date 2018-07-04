package acesso.tse.jus.br.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import acesso.tse.jus.br.entity.Usuario;
import acesso.tse.jus.br.repository.UsuarioRepository;
import acesso.tse.jus.br.resource.UsuarioResource;



@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/usuario")
public class UsuarioRestController {

	@Autowired
	UsuarioRepository repository;	
	RestTemplate restTemplate;
	
	@LoadBalanced @Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
	UsuarioResourceAssembler assembler = new UsuarioResourceAssembler();
	
	@PersistenceContext
    private EntityManager em;	
	
	@PostConstruct
	public void init() {
		
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@GetMapping("/login/{login}/senha/{senha}")
	public ResponseEntity<UsuarioResource> findByloginAndsenha(@PathVariable String login, @PathVariable String senha) {
		Usuario usuario = repository.findByloginAndsenha(login,senha);
		if (usuario != null) {			
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@GetMapping("/usuarios/{sigla}")
	public ResponseEntity<List<UsuarioResource>> findBySiglaModulo(@PathVariable String sigla) {
		return new ResponseEntity<>(assembler.toResources(repository.findBySiglaModulo(sigla)), HttpStatus.OK);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResource> get(@PathVariable Integer id) {
		Usuario usuario = repository.findOne(id);
		if (usuario != null) {			
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<UsuarioResource> create(@RequestBody Usuario usuario) {
		usuario = repository.save(usuario);
		if (usuario != null) {
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResource> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Usuario pusuario = repository.findOne(id);
		if (pusuario != null) {
			usuario.setId(pusuario.getId());
			pusuario = repository.save(usuario);
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioResource> delete(@PathVariable Integer id) {
		Usuario usuario = repository.findOne(id);
		if (usuario != null) {
			repository.delete(usuario);
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/login/{login}")
	public ResponseEntity<UsuarioResource> findBylogin(@PathVariable String login) {
		Usuario usuario = repository.findBylogin(login);
		if (usuario != null) {			
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<UsuarioResource> findBysiglaModulo(@PathVariable String matricula) {
		Usuario usuario = repository.findBymatriculaServidor(matricula);
		if (usuario != null) {			
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
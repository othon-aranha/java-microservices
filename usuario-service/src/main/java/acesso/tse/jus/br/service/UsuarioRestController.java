package acesso.tse.jus.br.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
// import org.springframework.data.jpa.domain.Specification;
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

// import com.google.gson.Gson;


// import acesso.tse.jus.br.dto.UsuarioDTO;

import acesso.tse.jus.br.entity.StatusUsuario;
import acesso.tse.jus.br.entity.TipoUsuario;
import acesso.tse.jus.br.entity.Usuario;

// import acesso.tse.jus.br.impl.UsuarioRepositoryCustomImpl;
import acesso.tse.jus.br.repository.UsuarioRepository;

import acesso.tse.jus.br.resource.UsuarioResource;



@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials="true",  methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/usuario")
public class UsuarioRestController {

	@Autowired
	UsuarioRepository repository;	
	RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
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
	
	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Acesso Service - Modulo rodando em porta: " + env.getProperty("local.server.port");
	}
	
	// -------- Admin Area --------
	// This method should only be accessed by users with role of 'admin'
	// We'll add the logic of role based auth later
	@RequestMapping("/admin")
	public String homeAdmin() {
		return "Esta é a área de administração do serviço rodando na porta: " + env.getProperty("local.server.port");
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioResource>> getAll() {
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
	
	@GetMapping("/login/{login}/senha/{senha}")
	public ResponseEntity<UsuarioResource> findByloginAndsenha(@PathVariable String login, @PathVariable String senha) {
		Usuario usuario = repository.findByloginAndsenha(login,senha);
		if (usuario != null) {			
			return new ResponseEntity<>(assembler.toResource(usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@GetMapping("/usuarios/siglaModulo/{sigla}")
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
	
	@Transactional(timeout = 100)
	@PostMapping
	public ResponseEntity<UsuarioResource> create(@RequestBody Usuario usuario) {
		Usuario pusuario = repository.save(usuario);
		if (pusuario != null) {
			return new ResponseEntity<>(assembler.toResource(pusuario), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 100)
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResource> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Usuario pusuario = repository.findOne(id);
		if (pusuario != null) {
			pusuario.setUnidade(usuario.getUnidade());
			pusuario.setId(usuario.getId());
			pusuario = repository.save(usuario);
			return new ResponseEntity<>(assembler.toResource(pusuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Transactional(timeout = 100)
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
	
	@GetMapping("usuarios/tipoUsuario/{tipoUsuario}")
	public ResponseEntity<List<UsuarioResource>> findBytipo(@PathVariable List<TipoUsuario> tipoUsuario) {		
		return new ResponseEntity<>(assembler.toResources(repository.findBytipoIn(tipoUsuario)), HttpStatus.OK);		
	}	

	@GetMapping("usuarios/status/{status}")
	public ResponseEntity<List<UsuarioResource>> findBystatusIn(@PathVariable List<StatusUsuario> status) {
		return new ResponseEntity<>(assembler.toResources(repository.findBystatusIn(status)), HttpStatus.OK);		
	}		
	
	@GetMapping("usuarios/tipoUsuario/{tipoUsuario}/status/{status}")
	public ResponseEntity<List<UsuarioResource>> findBytipoInAndstatusIn(@PathVariable List<TipoUsuario> tipoUsuario, @PathVariable List<StatusUsuario> status) {
		return new ResponseEntity<>(assembler.toResources(repository.findBytipoInAndstatusIn(tipoUsuario,status)), HttpStatus.OK);		
	}	
	
	
}
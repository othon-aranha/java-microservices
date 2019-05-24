package acesso.tse.jus.br.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.jdbc.core.JdbcTemplate;

import acesso.tse.jus.br.entity.Servidor;
import acesso.tse.jus.br.entity.Usuario;

@RepositoryRestResource(path="/servidores", itemResourceRel="servidor")
public interface ServidorRepository extends JpaRepository<Servidor, String>{	
	@Autowired		
	JdbcTemplate jdbctemplate = null;
	
	@RestResource(path="/id")
	@Query("SELECT a FROM Servidor a where a.id = :id")
	Servidor findOne(@Param("id") String id); 
		
	@RestResource(path="/nome")
	Usuario findBynome(@Param("nome") String nome);
	
	@RestResource(path="/matricula")
	Servidor findBymatricula(@Param("matricula") String matricula);
		
	@RestResource(path="/servidores")
	@Query("SELECT a FROM Usuario a")
	Page<Servidor> findAll(Pageable pageable);
}
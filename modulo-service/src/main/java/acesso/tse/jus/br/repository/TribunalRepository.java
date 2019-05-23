package acesso.tse.jus.br.repository;

//import java.math.BigDecimal;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.jdbc.core.JdbcTemplate;

import acesso.tse.jus.br.entity.Tribunal;

@RepositoryRestResource(path="/tribunal", itemResourceRel="tribunal")
public interface TribunalRepository extends JpaRepository<Tribunal, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	@RestResource(path="/id")
	@Query("SELECT a FROM Tribunal a where a.id = :id")
	Tribunal findOne(@Param("id") Integer id);
	
	@RestResource(path="/sigla")
	Tribunal findBysigla(@Param("sigla") String sigla);
	
	@RestResource(path="/acesso")
	Tribunal findByacesso(@Param("acesso") String acesso);	
	
	@Query("SELECT a FROM Tribunal a")
	Page<Tribunal> findAll(Pageable pageable);		
}
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

import acesso.tse.jus.br.entity.Funcionalidade;

@RepositoryRestResource(path="/funcionalidade", itemResourceRel="funcionalidade")
public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
	
	@RestResource(path="/id")
	@Query("SELECT a FROM Funcionalidade a where a.id = :id")
	Funcionalidade findOne(@Param("id") Integer id);
	
	@RestResource(path="/nome")
	Page<Funcionalidade> findBynomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);	

	@Query("SELECT a FROM Funcionalidade a")
	Page<Funcionalidade> findAll(Pageable pageable);	
	
	@Query("SELECT a FROM Funcionalidade a inner join a.modulo m WHERE m.id = :id ")
	Page<Funcionalidade> findByModuloid(@Param("id") Integer id, Pageable pageable);	
}
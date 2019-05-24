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

import acesso.tse.jus.br.entity.Grupo;

@RepositoryRestResource(path="/grupo", itemResourceRel="grupo")
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
	
	@RestResource(path="/id")
	@Query("SELECT a FROM Grupo a WHERE a.id = :id")
	Grupo findOne(@Param("id") Integer id);
	
	@RestResource(path="/nome")
	Page<Grupo> findBynomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);	
	
	@Query("SELECT a FROM Grupo a INNER JOIN a.area aa WHERE aa.sigla = :sigla")
	Page<Grupo> findBysigla(Pageable pageable, @Param("sigla") String sigla);
	
	@Query("SELECT a FROM Grupo a")
	Page<Grupo> findAll(Pageable pageable);		
}
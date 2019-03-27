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

import acesso.tse.jus.br.entity.Area;

@RepositoryRestResource(path="/area", itemResourceRel="area")
public interface AreaRepository extends JpaRepository<Area, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	Area findOne(@Param("id") Integer id);
	
	@RestResource(path="/nome")
	Page<Area> findBynomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);	

	@RestResource(path="/sigla")
	Area findBysiglaIgnoreCase(@Param("sigla") String sigla);	
	
	@Query("SELECT a FROM Area a")
	Page<Area> findAll(Pageable pageable);		
}
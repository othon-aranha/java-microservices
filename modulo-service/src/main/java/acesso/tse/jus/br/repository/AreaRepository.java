package acesso.tse.jus.br.repository;

import java.util.List;

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
	
	@RestResource(path="/id")
	@Query("SELECT a FROM Area a where a.id = :id")
	Area findOne(@Param("id") Integer id);
	
	@RestResource(path="/nome")
	Page<Area> findBynomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);	

	@RestResource(path="/sigla")
	Area findBysiglaIgnoreCase(@Param("sigla") String sigla);	
	
	@Query("SELECT a FROM Area a WHERE status = :status")
	Page<Area> findBystatus(@Param("status") Boolean status, Pageable pageable);		
	
	@Query("SELECT a FROM Area a")
	List<Area> findAll();	 	
}
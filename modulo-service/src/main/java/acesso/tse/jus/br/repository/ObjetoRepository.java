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

import acesso.tse.jus.br.entity.Objeto;

@RepositoryRestResource(path="/objeto", itemResourceRel="objeto")
public interface ObjetoRepository extends JpaRepository<Objeto, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	Objeto findOne(@Param("id") Integer id);
	
	@RestResource(path="/nome")
	Page<Objeto> findBynomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);	
	
	@Query("SELECT a FROM Objeto a")
	Page<Objeto> findAll(Pageable pageable);		
}
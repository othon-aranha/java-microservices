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

import acesso.tse.jus.br.entity.Dominio;

@RepositoryRestResource(path="/dominio", itemResourceRel="dominio")
public interface DominioRepository extends JpaRepository<Dominio, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
	
	@RestResource(path="/id")
	@Query("SELECT a FROM Dominio a where a.id = :id")
	Dominio findOne(@Param("id") Integer id);
	
	@RestResource(path="/nome")
	Page<Dominio> findBynomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);	

	
	@Query("SELECT a FROM Dominio a where UPPER(a.nome) LIKE UPPER(:nome) and UPPER(a.descricao) like UPPER(:descricao)")
	Page<Dominio> findBynomeAnddescricao(@Param("nome") String nome, @Param("descricao") String descricao, Pageable pageable);	
	
	
	@Query("SELECT a FROM Dominio a")
	Page<Dominio> findAll(Pageable pageable);		
}
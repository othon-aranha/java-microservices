package acesso.tse.jus.br.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.jdbc.core.JdbcTemplate;

import acesso.tse.jus.br.entity.MaquinaServidora;
import acesso.tse.jus.br.entity.MaquinaServidoraPK;

@RepositoryRestResource(path="/alias", itemResourceRel="alias")
public interface MaquinaServidoraRepository extends JpaRepository<MaquinaServidora, MaquinaServidoraPK> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	MaquinaServidora findOne(@Param("id") MaquinaServidoraPK id);
	
	@RestResource(path="/cdTrib/{cdTrib}")	
	@Query("SELECT a FROM MaquinaServidora a where a.id.cdTrib = :cdTrib")
	List<MaquinaServidora> findBycdTrib(@Param("cdTrib") Integer cdTrib);	
	
	@RestResource(path="/cdTrib/{cdTrib}/alias/{alias}")	
	@Query("SELECT a FROM MaquinaServidora a where a.id.cdTrib = :cdTrib and a.id.alias = :alias")
	List<MaquinaServidora> findBycdTribAndalias(@Param("cdTrib") Integer cdTrib, @Param("alias") String alias);	
	
	@Query("SELECT a FROM MaquinaServidora a")
	List<MaquinaServidora> findAll();		
}
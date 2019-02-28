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

import acesso.tse.jus.br.entity.Perfil;

@RepositoryRestResource(path="/perfil", itemResourceRel="perfil")
public interface PerfilRepository extends JpaRepository<Perfil, String> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	Perfil findOne(@Param("id") String id);
	
	@RestResource(path="/nome")
	Perfil findBynomeLike(@Param("nome") String nome);
	
	@RestResource(path="/perfis/{sigla}")
	@Query("SELECT a FROM Perfil a INNER JOIN a.modulo u WHERE u.sigla = :sigla ")
	List<Perfil> findBySiglaModulo(@Param("sigla") String sigla);	
	
	@RestResource(path="/perfis")
	@Query("SELECT a FROM Perfil a INNER JOIN a.modulo m")
	Page<Perfil> findAll(Pageable pageable);		
}
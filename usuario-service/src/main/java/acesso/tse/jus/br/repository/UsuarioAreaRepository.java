package acesso.tse.jus.br.repository;

import java.util.List;

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

import acesso.tse.jus.br.entity.UsuarioArea;

@RepositoryRestResource(path="/usuarioarea", itemResourceRel="usuarioarea")
public interface UsuarioAreaRepository extends JpaRepository<UsuarioArea, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	@RestResource(path="/id")
	@Query("SELECT ua FROM UsuarioArea ua where ua.id = :id")
	UsuarioArea findOne(@Param("id") Integer id);
	
	@RestResource(path="/login")
	@Query("SELECT ua FROM UsuarioArea ua INNER JOIN ua.usuario u WHERE u.login = :login")
	List<UsuarioArea> findBylogin(@Param("login") String login);
	
	@RestResource(path="/area")
	@Query("SELECT ua FROM UsuarioArea ua INNER JOIN ua.area a WHERE a.sigla = :area")
	List<UsuarioArea> findByarea(@Param("area") String area);	
	
	@Query("SELECT ua FROM UsuarioArea ua")
	Page<UsuarioArea> findAll(Pageable pageable);		
}
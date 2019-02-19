package acesso.tse.jus.br.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.jdbc.core.JdbcTemplate;

import acesso.tse.jus.br.entity.Modulo;
import acesso.tse.jus.br.entity.SimNaoType;
import acesso.tse.jus.br.entity.StatusModulo;
import acesso.tse.jus.br.entity.TipoModulo;
import acesso.tse.jus.br.entity.TipoAtualizacao;

@RepositoryRestResource(path="/modulo", itemResourceRel="modulo")
public interface ModuloRepository extends JpaRepository<Modulo, Integer>, JpaSpecificationExecutor<Modulo> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	@RestResource(path="/id")
	@Query("SELECT a FROM Modulo a INNER JOIN a.tribunal t where t.acesso = '*' and a.id = :id")
	Modulo findOne(@Param("id") Integer id);
	
	@RestResource(path="/sigla")
	Modulo findBysigla(@Param("sigla") String sigla);
	
	@RestResource(path="/tipoModulo")
	@Query(nativeQuery = true)
	List<Modulo> findBytipoModuloIn(@Param("tipoModulo") List<TipoModulo> tipoModulo);
	
	@RestResource(path="/tipoAtualizacao")
	@Query(nativeQuery = true)
	List<Modulo> findBytipoAtualizacao(@Param("tipoAtualizacao") TipoAtualizacao tipoAtualizacao);	
	
	@RestResource(path="/statusModulo")
	@Query(nativeQuery = true)
	List<Modulo> findBystatusModulo(@Param("statusModulo") StatusModulo statusModulo);
	
	@RestResource(path="/controlaAcesso")
	@Query(nativeQuery = true)
	List<Modulo> findBycontrolaAcesso(@Param("controlaAcesso") SimNaoType controlaAcesso);
	
	@Query("SELECT a FROM Modulo a INNER JOIN a.usuarios u INNER JOIN a.tribunal t where t.acesso = '*' and UPPER(u.login) like UPPER(:login)")
	Page<Modulo> findByLoginUsuario(@Param("login") String login, Pageable pageable);

	@Query("SELECT a FROM Modulo a INNER JOIN a.tribunal t where t.acesso = '*'")
	List<Modulo> findAll(Specification<Modulo> spec);	
		
}
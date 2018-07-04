package acesso.tse.jus.br.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.jdbc.core.JdbcTemplate;

import acesso.tse.jus.br.entity.Manutencao;
import acesso.tse.jus.br.entity.ManutencaoKey;

@RepositoryRestResource(path="/manutencao", itemResourceRel="manutencao")
public interface ManutencaoRepository extends JpaRepository<Manutencao, ManutencaoKey> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	Manutencao findOne(@Param("id") ManutencaoKey id);
	
	@RestResource(path="/idModulo/{idModulo}")	
	@Query("SELECT a FROM Manutencao a where a.id.modulo.id = :idModulo")
	List<Manutencao> findBycdTrib(@Param("idModulo") Integer idModulo);
	
	@RestResource(path="/sigla/{sigla}")	
	@Query("SELECT a FROM Manutencao a inner join a.id.modulo m where m.sigla = :sigla")
	List<Manutencao> findBysigla(@Param("sigla") String sigla);	

	
}
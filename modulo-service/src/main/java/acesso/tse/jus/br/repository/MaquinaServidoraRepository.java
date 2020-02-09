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

import acesso.tse.jus.br.entity.Manutencao;
import acesso.tse.jus.br.entity.MaquinaServidora;
import acesso.tse.jus.br.entity.MaquinaServidoraPK;

@RepositoryRestResource(path="/alias", itemResourceRel="alias")
public interface MaquinaServidoraRepository extends JpaRepository<MaquinaServidora, MaquinaServidoraPK> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	@RestResource(path="/id")
	@Query("SELECT a FROM MaquinaServidora a where a.id = :id")
	MaquinaServidora findOne(@Param("id") Integer id);
	
	@RestResource(path="/cdTrib/{cdTrib}")	
	@Query("SELECT a FROM MaquinaServidora a inner join a.tribunal t where t.id = :cdTrib order by a.alias")
	List<MaquinaServidora> findBycdTrib(@Param("cdTrib") Integer cdTrib);	
	
	@RestResource(path="/cdTrib/{cdTrib}/alias/{alias}")	
	@Query("SELECT a FROM MaquinaServidora a inner join a.tribunal t where t.id = :cdTrib and a.alias = :alias")
	MaquinaServidora findBycdTribAndalias(@Param("cdTrib") Integer cdTrib, @Param("alias") String alias);
	
	@RestResource(path="/cdModulo/{cdModulo}")
	@Query(value="SELECT MS.* FROM ADMACESSO.MAQUINA_SERVIDORA MS INNER JOIN ADMACESSO.MANUTENCAO M ON ( MS.SQ_MAQUINA_SERVIDORA = M.SQ_MAQUINA_SERVIDORA) WHERE M.CD_MODULO = :cdModulo ORDER BY MS.ALIAS", nativeQuery = true)	
	List<MaquinaServidora> findBycdModulo(@Param("cdModulo") Integer cdModulo);
	
	@Query("SELECT a FROM MaquinaServidora a order by a.alias")
	List<MaquinaServidora> findAll();		
	
	@RestResource(path="/alias/{alias}")
	@Query(value="SELECT MS.* FROM ADMACESSO.MAQUINA_SERVIDORA MS INNER JOIN ADMACESSO.TRIBUNAL T ON ( MS.CD_TRIB = T.CD_TRIB ) WHERE T.ACESSO = '*' AND MS.ALIAS LIKE :alias ORDER BY MS.ALIAS", nativeQuery = true)
	List<MaquinaServidora> findAlias(@Param("alias") String alias);	
}
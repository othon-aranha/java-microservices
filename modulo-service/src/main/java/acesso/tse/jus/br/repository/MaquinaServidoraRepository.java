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

import acesso.tse.jus.br.entity.MaquinaServidora;

@RepositoryRestResource(path="/alias", itemResourceRel="alias")
public interface MaquinaServidoraRepository extends JpaRepository<MaquinaServidora, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
		
	MaquinaServidora findOne(@Param("id") Integer id);
	
	@RestResource(path="/cdTrib/{cdTrib}")	
	@Query("SELECT a FROM MaquinaServidora a inner join a.tribunal t where t.id = :cdTrib")
	List<MaquinaServidora> findBycdTrib(@Param("cdTrib") Integer cdTrib);	
	
	@RestResource(path="/alias/{alias}")	
	// @Query("SELECT a FROM MaquinaServidora a inner join a.tribunal t where t.acesso = '*' and a.alias like :alias")
	Page<MaquinaServidora>  findByaliasIgnoreCaseContaining(@Param("alias") String alias, Pageable pageable);
	
	@RestResource(path="/cdModulo/{cdModulo}")
	@Query(value="SELECT MS.* FROM ADMACESSO.MAQUINA_SERVIDORA MS INNER JOIN ADMACESSO.MANUTENCAO M ON ( MS.SQ_MAQUINA_SERVIDORA = M.SQ_MAQUINA_SERVIDORA ) WHERE M.CD_MODULO = :cdModulo ORDER BY MS.ALIAS", nativeQuery = true)	
	List<MaquinaServidora> findBycdModulo(@Param("cdModulo") Integer cdModulo);
	
	@Query("SELECT a FROM MaquinaServidora a")
	List<MaquinaServidora> findAll();
	
	@Query("SELECT a FROM MaquinaServidora a inner join a.tribunal")
	MaquinaServidora findByid(@Param("id") Integer id);
}
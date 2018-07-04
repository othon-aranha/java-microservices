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

import acesso.tse.jus.br.entity.Usuario;

@RepositoryRestResource(path="/usuarios", itemResourceRel="usuario")
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;
	
	Usuario findOne(@Param("id") Integer id); 
		
	@RestResource(path="/login")
	Usuario findBylogin(@Param("login") String login);
	
	@RestResource(path="/nome")
	Usuario findBynome(@Param("nome") String nome);
	
	@RestResource(path="/matricula")
	Usuario findBymatriculaServidor(@Param("matricula") String matricula);
	
	@RestResource(path="/usuarios/{sigla}")
	@Query("SELECT a FROM Usuario a INNER JOIN a.usuarioModulos u where UPPER(u.sigla) = ?")
	List<Usuario> findBySiglaModulo(@Param("sigla") String sigla);
	
	@RestResource(path="/login/{login}/senha/{senha}")
	@Query(value = "SELECT * FROM ADMACESSO.USUARIO WHERE CD_USUARIO = :login AND SENHA = ADMACESSO.PCK_ACESSO_SECURITY.CRIPT(RPAD(LOWER(:senha),32),RPAD(:login,32))", nativeQuery = true)
	Usuario findByloginAndsenha(@Param("login") String login, @Param("senha") String senha);
	
	@RestResource(path="/usuarios")
	@Query("SELECT a FROM Usuario a")
	Page<Usuario> findAll(Pageable pageable);		
}
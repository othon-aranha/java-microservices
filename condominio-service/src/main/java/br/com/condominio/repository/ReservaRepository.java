package br.com.condominio.repository;

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

import br.com.condominio.entity.Reserva;

@RepositoryRestResource(path="/reserva", itemResourceRel="reserva")
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {	
	@Autowired		
	JdbcTemplate jdbctemplate = null;	
	
	@RestResource(path="/id")
	@Query("SELECT a FROM Reserva a where a.id = :id")
	Reserva findOne(@Param("id") Integer id);	
	
	@Query("SELECT a FROM Reserva a")
	Page<Reserva> findAll(Pageable pageable);
	
	@RestResource(path="/anoMes/{anoMes}/local/{local}")
	@Query(value="SELECT r.* FROM  Reserva r WHERE strftime('%Y-%m', dt_reserva) = :anoMes AND r.local_reserva in :local", nativeQuery = true)
	List<Reserva> findByanoMesAndlocal(@Param("anoMes") String anoMes, @Param("local") List<Integer> id);
}
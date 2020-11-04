package acesso.tse.jus.br.repository;

import java.util.List;

import acesso.tse.jus.br.dto.ModuloDTO;
import acesso.tse.jus.br.entity.Modulo;



public interface ModuloRepositoryCustom  {		
	public List<Modulo> moduloByModuloDTO(ModuloDTO modulo);
}
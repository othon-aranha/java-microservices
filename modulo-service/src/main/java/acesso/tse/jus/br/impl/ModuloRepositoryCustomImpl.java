package acesso.tse.jus.br.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;


import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.base.Strings;

import acesso.tse.jus.br.dto.ModuloDTO;
import acesso.tse.jus.br.entity.Modulo;
import acesso.tse.jus.br.entity.TipoModulo;
import acesso.tse.jus.br.repository.ModuloRepositoryCustom;


public class ModuloRepositoryCustomImpl implements ModuloRepositoryCustom {

	@PersistenceContext
    private EntityManager em;
    
	@Override
	public List<Modulo> moduloByModuloDTO(ModuloDTO params) {
	      CriteriaBuilder builder = em.getCriteriaBuilder();
	      CriteriaQuery<Modulo> query = builder.createQuery(Modulo.class);
	      Root<Modulo> r = query.from(Modulo.class);
	      query.select(r);
	 
	      Predicate predicate = builder.conjunction();
		
		//Verificando os parametros no DTO
	    /* Parametro nome */
	    if (!Strings.isNullOrEmpty(params.getNome()) ) {
			predicate = builder.and(predicate, builder.equal(r.get("nome"), params.getNome()) );
		}

	    /* Parametro Sigla */
	    if (!Strings.isNullOrEmpty(params.getSigla()) ) {
			predicate = builder.and(predicate, builder.equal(r.get("sigla"), params.getSigla()) );
		}
	    
	    
	    /* Parametro Tipo do Modulo */
	    List<String> list = Arrays.asList();
	    if ( !params.getTipoModulo().isEmpty() ) {
	    	for(TipoModulo param: params.getTipoModulo()) {
	    		list.add(param.toString());
	    	}
	    	predicate = builder.and(predicate, r.in(list));
	    }

	    /* Parametro Status do Modulo */
	    if (!Strings.isNullOrEmpty(params.getStatusModulo().toString()) ) {
			predicate = builder.and(predicate, builder.equal(r.get("statusModulo"), params.getStatusModulo().toString()) );
		}
	    
	    
	    /* Parametro Tipo de Atualizacao */
	    if (!Strings.isNullOrEmpty(params.getTipoAtualizacao().toString()) ) {
			predicate = builder.and(predicate, builder.equal(r.get("tipoAtualizacao"), params.getTipoAtualizacao().toString()) );
		}
	    	    
	    
		query.where(predicate);
		
		List<Modulo> result = em.createQuery(query).getResultList();
		return result;
	}

}

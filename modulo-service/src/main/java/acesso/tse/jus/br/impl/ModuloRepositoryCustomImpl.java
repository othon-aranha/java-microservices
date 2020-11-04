package acesso.tse.jus.br.impl;


import javax.persistence.criteria.CriteriaBuilder;


import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Strings;

import acesso.tse.jus.br.dto.ModuloDTO;
import acesso.tse.jus.br.entity.Modulo;
import acesso.tse.jus.br.entity.SimNaoType;
import acesso.tse.jus.br.entity.StatusModulo;
import acesso.tse.jus.br.entity.TipoAtualizacao;


public class ModuloRepositoryCustomImpl {
    
	
	public static Specification<Modulo> moduloByModuloDTO(ModuloDTO params) {
		return new Specification<Modulo>() {
			public Predicate toPredicate(Root<Modulo> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
			 
			    Predicate predicate = builder.conjunction();
			    
	            //Parametro TipoModulo
			    if ( params.getTipoModulo() != null ) {			    	
			    	if ( ! params.getTipoModulo().isEmpty() ) {	    	
			    		predicate = builder.and(predicate, root.get("tipoModulo").in(params.getTipoModulo()));
			    	}
			    }
			    
			    if ( params.getStatusModulo() != null ) {
					if ( ! params.getStatusModulo().isEmpty() ) {	    	
			    		predicate = builder.and(predicate, root.get("statusModulo").in(params.getStatusModulo()));
			    	}
			    }				    
				
			    
			    /* Parametro nome */
			    if (!Strings.isNullOrEmpty(params.getNome()) ) {
					predicate = builder.and(predicate, builder.equal(root.get("nome"), params.getNome()) );
				}

			    /* Parametro Sigla */
			    if (!Strings.isNullOrEmpty(params.getSigla()) ) {
					predicate = builder.and(predicate, builder.equal(root.get("sigla"), params.getSigla()) );
				}
			    
			    /* Parametro controlaAcesso */
			    if ( params.getControlaAcesso() != null ) {
				    if (SimNaoType.values().length > 0 ) {
						predicate = builder.and(predicate, builder.equal(root.get("controlaAcesso"), params.getControlaAcesso()) );
					}
			    	
			    }
			    
			    
			    //Parametro Tipo de Atualizacao
			    if ( params.getTipoAtualizacao() != null ) {
				    if ( TipoAtualizacao.values().length > 0 ) {
						predicate = builder.and(predicate, builder.equal(root.get("tipoAtualizacao"), params.getTipoAtualizacao()) );
					}
			    	
			    }
				 
			    
				return predicate;				
						
			}

	};
  }

}

package acesso.tse.jus.br.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acesso.tse.jus.br.entity.Modulo;


import acesso.tse.jus.br.AcessoConstants;

@Embeddable
public class ManutencaoKey implements Serializable {

	private static final long	serialVersionUID	= AcessoConstants.VERSAO;

	@Basic(optional = false)
    @NotNull
    @Column(name = "CD_TRIB")
    private Integer cdTrib;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ALIAS")
    private String alias;	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo", nullable = false)
	@NotNull
	private Modulo	modulo;

	public ManutencaoKey() {
		super();
	}

	public ManutencaoKey(Integer cdTrib, String alias, final Modulo modulo) {
		this.cdTrib = cdTrib;
		this.alias = alias;
		this.modulo = modulo;
	}

	public Integer getCdTrib() {
		return cdTrib;
	}

	public void setCdTrib(Integer cdTrib) {
		this.cdTrib = cdTrib;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(final Modulo modulo) {
		this.modulo = modulo;
	}
	
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += super.hashCode(); 
        hash += (modulo != null ? modulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManutencaoKey)) {
            return false;
        }
        
        ManutencaoKey other = (ManutencaoKey) object;
        if (this.getCdTrib() != other.getCdTrib() ) {
            return false;
        }
        if ((this.getAlias() == null && other.getAlias() != null) || (this.getAlias() != null && !this.getAlias().equals(other.getAlias()))) {
            return false;
        }
        
        if ( ( this.modulo == null && other.modulo != null ) || ( this.modulo != null && !this.modulo.equals(other.modulo) ) ) {
        	return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
    	return this.getCdTrib().toString() + ' ' + this.getAlias() + ' ' + this.modulo.toString();
    }
	

}

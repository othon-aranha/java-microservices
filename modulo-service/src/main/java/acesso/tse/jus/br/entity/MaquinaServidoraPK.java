/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso.tse.jus.br.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author othon.aranha
 */
@Embeddable
public class MaquinaServidoraPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
    @NotNull
    @Column(name = "CD_TRIB")
    private Integer cdTrib;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ALIAS")
    private String alias;

    public MaquinaServidoraPK() {
    }

    public MaquinaServidoraPK(Integer cdTrib, String alias) {
        this.cdTrib = cdTrib;
        this.alias = alias;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cdTrib;
        hash += (alias != null ? alias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaquinaServidoraPK)) {
            return false;
        }
        MaquinaServidoraPK other = (MaquinaServidoraPK) object;
        if (this.cdTrib != other.cdTrib) {
            return false;
        }
        if ((this.alias == null && other.alias != null) || (this.alias != null && !this.alias.equals(other.alias))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
    	return this.cdTrib.toString() + ' ' + this.alias;
    }
}

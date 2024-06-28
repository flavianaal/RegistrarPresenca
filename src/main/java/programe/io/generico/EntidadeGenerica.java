/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.generico;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Flaviana Andrade
 */

@MappedSuperclass
public abstract class EntidadeGenerica implements Serializable{
    
    public Boolean ativo;
    
    
    public EntidadeGenerica() {
    ativo = Boolean.TRUE;
    setAtivo(ativo);
       
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.ativo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntidadeGenerica other = (EntidadeGenerica) obj;
        return Objects.equals(this.ativo, other.ativo);
    }

    @Override
    public String toString() {
        return "EntidadeGenerica{" + "ativo=" + ativo + '}';
    }
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Manager;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import programe.io.Modelo.Endereco;
import programe.io.Util.Mensagem;
import programe.io.servico.ServicoEndereco;


/**
 *
 * @author Flaviana Andrade
 */
@Named
@ViewScoped
public class ManagerEndereco implements Serializable{
    
    @EJB
    ServicoEndereco servicoEndereco;
    
    private Endereco endereco;
   
    
    @PostConstruct
    public void instanciar(){
        endereco = new Endereco();
      
    }
     public void salvar(){
        servicoEndereco.salvar(endereco);
        instanciar();
        Mensagem.msg("Operação Realizada com Sucesso");
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ServicoEndereco getServicoEndereco() {
        return servicoEndereco;
    }

    public void setServicoEndereco(ServicoEndereco servicoEndereco) {
        this.servicoEndereco = servicoEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.servicoEndereco);
        hash = 89 * hash + Objects.hashCode(this.endereco);
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
        final ManagerEndereco other = (ManagerEndereco) obj;
        if (!Objects.equals(this.servicoEndereco, other.servicoEndereco)) {
            return false;
        }
        return Objects.equals(this.endereco, other.endereco);
    }

    @Override
    public String toString() {
        return "ManagerEndereco{" + "servicoEndereco=" + servicoEndereco + ", endereco=" + endereco + '}';
    }
     
     
    

    
}

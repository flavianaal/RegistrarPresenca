/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import programe.io.Modelo.EntradaSaida;
import programe.io.Util.Mensagem;
import programe.io.servico.ServicoEntradaSaida;

/**
 *
 * @author Flaviana Andrade
 */
@Named
@ViewScoped
public class ManagerEntradaSaida implements Serializable  {
    
    @EJB
    private ServicoEntradaSaida servicoEntradaSaida;

    private List<EntradaSaida> entradasSaidas;
    private EntradaSaida entradaSaida;
    
    @PostConstruct
    public void instanciar(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String visualizar = params.get("visualizar");
        String editar = params.get("editar");
        if (visualizar != null) {
            entradaSaida = servicoEntradaSaida.find(Long.parseLong(visualizar));
            
        }else if(editar != null){
            entradaSaida = servicoEntradaSaida.find(Long.parseLong(editar));
        } else{
            entradaSaida = new EntradaSaida();
            
        }
        
        
    }
    
    public void salvar(){
       
        if (entradaSaida == null) {
            entradaSaida = new EntradaSaida();
        }
        if (entradaSaida.getId() == null) {
            servicoEntradaSaida.salvar(entradaSaida);
            instanciar();
            System.out.println(entradaSaida);
        } else {
            servicoEntradaSaida.atualizar(entradaSaida);
        }
        Mensagem.msg("Operação Realizada com sucesso");
    }

    
    
    public void pesquisar(){
        entradasSaidas = servicoEntradaSaida.findByAll(entradaSaida); 
    }
    
    public void remover(){
        servicoEntradaSaida.delete(entradaSaida);
        Mensagem.msg("Entrada/Saida removida com sucesso");
        
    }

    public ServicoEntradaSaida getServicoEntradaSaida() {
        return servicoEntradaSaida;
    }

    public void setServicoEntradaSaida(ServicoEntradaSaida servicoEntradaSaida) {
        this.servicoEntradaSaida = servicoEntradaSaida;
    }

    public List<EntradaSaida> getEntradasSaidas() {
        return entradasSaidas;
    }

    public void setEntradasSaidas(List<EntradaSaida> entradasSaidas) {
        this.entradasSaidas = entradasSaidas;
    }

    public EntradaSaida getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(EntradaSaida entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.servicoEntradaSaida);
        hash = 47 * hash + Objects.hashCode(this.entradasSaidas);
        hash = 47 * hash + Objects.hashCode(this.entradaSaida);
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
        final ManagerEntradaSaida other = (ManagerEntradaSaida) obj;
        if (!Objects.equals(this.servicoEntradaSaida, other.servicoEntradaSaida)) {
            return false;
        }
        if (!Objects.equals(this.entradasSaidas, other.entradasSaidas)) {
            return false;
        }
        return Objects.equals(this.entradaSaida, other.entradaSaida);
    }

    @Override
    public String toString() {
        return "ManagerEntradaSaida{" + "servicoEntradaSaida=" + servicoEntradaSaida + ", entradasSaidas=" + entradasSaidas + ", entradaSaida=" + entradaSaida + '}';
    }

   
}

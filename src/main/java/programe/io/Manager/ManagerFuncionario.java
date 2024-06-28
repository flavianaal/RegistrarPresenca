package programe.io.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import programe.io.Modelo.Endereco;
import programe.io.Modelo.FolhaDePonto;
import programe.io.Modelo.Funcionario;
import programe.io.Util.Mensagem;
import programe.io.enums.CargosProfissionais;
import programe.io.servico.ServicoEndereco;
import programe.io.servico.ServicoFuncionario;

@Named
@ViewScoped
public class ManagerFuncionario implements Serializable {
    
    @EJB
    ServicoFuncionario servicoFuncionario;
    
    @EJB
    ServicoEndereco servicoEndereco;
    
    
    private Funcionario funcionario;
    private List<Funcionario> funcionarios;
    private Endereco endereco;
    private CargosProfissionais cargosProfissionais; // Adicionado para selecionar o cargo
    private List<FolhaDePonto> folhaDepontos;
   
   
   
    @PostConstruct
    public void instanciar(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String visualizar = params.get("visualizar");
        String editar = params.get("editar");
        
        if (visualizar != null) {
            funcionario = servicoFuncionario.find(Long.parseLong(visualizar)); 
            endereco = servicoEndereco.find(funcionario.getEndereco().getId());
          
        } else if (editar != null) {
            funcionario = servicoFuncionario.find(Long.parseLong(editar));
            endereco = servicoEndereco.find(funcionario.getEndereco().getId());
        }else{
            funcionario = new Funcionario();
            endereco = new Endereco();
        }
        //escola.setEndereco(new Endereco());
        funcionario.setEndereco(endereco);
        funcionarios = new ArrayList<>();
        
        //funcionario.setEscola(new ArrayList<>());
        
        
    }
    
    public void salvar() {
        if (funcionario.getId() == null) {
            servicoFuncionario.salvar(funcionario);
            instanciar();
            System.out.println(funcionario);
        } else {
            servicoFuncionario.atualizar(funcionario);
            servicoEndereco.atualizar(endereco);
            
        }
        Mensagem.msg("Operação Realizada com sucesso");
    }

    
    public void pesquisar() {   
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("Cargo: " + funcionario.getCargo());
        funcionarios = servicoFuncionario.findByAll(funcionario);
        
   
    }
    
     public void remover(Funcionario funcionario) {
        servicoFuncionario.delete(funcionario);
        Mensagem.msg("Funcionário removido com sucesso");
        
    }
    
    
    
    public List<SelectItem> getCargosProfissionais() {
        List<SelectItem> items = new ArrayList<>();
        for (CargosProfissionais item : CargosProfissionais.values()) {
            items.add(new SelectItem(item, item.getCargo()));
        }
        return items;
    }
    public void setCargosProfissionais(CargosProfissionais cargosProfissionais) {
        this.cargosProfissionais = cargosProfissionais;
    }
    
    public ServicoFuncionario getServicoFuncionario() {
        return servicoFuncionario;
    }

    public void setServicoFuncionario(ServicoFuncionario servicoFuncionario) {
        this.servicoFuncionario = servicoFuncionario;
    }

    public ServicoEndereco getServicoEndereco() {
        return servicoEndereco;
    }

    public void setServicoEndereco(ServicoEndereco servicoEndereco) {
        this.servicoEndereco = servicoEndereco;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<FolhaDePonto> getFolhaDepontos() {
        return folhaDepontos;
    }

    public void setFolhaDepontos(List<FolhaDePonto> folhaDepontos) {
        this.folhaDepontos = folhaDepontos;
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.servicoFuncionario);
        hash = 31 * hash + Objects.hashCode(this.servicoEndereco);
        
        hash = 31 * hash + Objects.hashCode(this.funcionario);
        hash = 31 * hash + Objects.hashCode(this.funcionarios);
        hash = 31 * hash + Objects.hashCode(this.endereco);
        hash = 31 * hash + Objects.hashCode(this.cargosProfissionais);
        hash = 31 * hash + Objects.hashCode(this.folhaDepontos);
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
        final ManagerFuncionario other = (ManagerFuncionario) obj;
        if (!Objects.equals(this.servicoFuncionario, other.servicoFuncionario)) {
            return false;
        }
        if (!Objects.equals(this.servicoEndereco, other.servicoEndereco)) {
            return false;
        }
        
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        if (!Objects.equals(this.funcionarios, other.funcionarios)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (this.cargosProfissionais != other.cargosProfissionais) {
            return false;
        }
        return Objects.equals(this.folhaDepontos, other.folhaDepontos);
    }

    @Override
    public String toString() {
        return "ManagerFuncionario{" + "servicoFuncionario=" + servicoFuncionario + ", servicoEndereco=" + servicoEndereco + ", servicoGenerico=" + funcionario + ", funcionarios=" + funcionarios + ", endereco=" + endereco + ", cargosProfissionais=" + cargosProfissionais + ", folhaDepontos=" + folhaDepontos + '}';
    }

    
    
    

  

}

   

   
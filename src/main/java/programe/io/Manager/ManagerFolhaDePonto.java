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
import programe.io.Modelo.FolhaDePonto;
import programe.io.Modelo.Funcionario;
import programe.io.Util.Mensagem;
import programe.io.servico.ServicoFolhaDePonto;
import programe.io.servico.ServicoFuncionario;


/**
 * Classe responsável por gerenciar as operações relacionadas à folha de ponto.
 */
@Named
@ViewScoped
public class ManagerFolhaDePonto implements Serializable {
    
    @EJB
    private ServicoFuncionario servicoFuncionario;
    
    @EJB
    private ServicoFolhaDePonto servicoFolhaDePonto;
    
    private List<FolhaDePonto> folhaDePontos;
    private FolhaDePonto folhaDePonto;
    private List<Funcionario> funcionarios;
    private Funcionario funcionario;
    private List<EntradaSaida> entradaSaida;
    
    
    @PostConstruct
    public void instanciar(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String visualizar = params.get("visualizar");
        String editar = params.get("editar");
        
        if (visualizar != null) {
            folhaDePonto = servicoFolhaDePonto.find(Long.parseLong(visualizar));
            funcionario = servicoFuncionario.find(funcionario.getEndereco().getId());
            
        } else if (editar != null) {
            folhaDePonto = servicoFolhaDePonto.find(Long.parseLong(editar));
            funcionario = servicoFuncionario.find(funcionario.getEndereco().getId());
            
        } else {
            funcionario = new Funcionario();
            //folhaDePonto.setEntradasaida(new ArrayList<>());
            //folhaDePonto.setFuncionario(funcionario);
           
        }

         entradaSaida = new ArrayList<>();
        
    }
    
    public void salvar(){
        if (folhaDePonto.getId() == null) {
            servicoFolhaDePonto.salvar(folhaDePonto);
            instanciar();
            System.out.println(folhaDePonto);
        } else {
            servicoFolhaDePonto.atualizar(folhaDePonto);
        }
        Mensagem.msg("Operação Realizada com sucesso");
    }
    
    public void pesquisar(){
        folhaDePontos = servicoFolhaDePonto.findByAll(folhaDePonto);
    }
    
    public void remover(){
        servicoFolhaDePonto.delete(folhaDePonto);
        Mensagem.msg("Registro de Presença removido com sucesso");
    }
    
    public void adicionarEntradaSaida() {
        folhaDePonto.getEntradasaida().add(new EntradaSaida());
        
    }
    
    public List<Funcionario> autocompleteFuncionario(String nome) {
        return servicoFolhaDePonto.findFuncionario(nome);
        
    }

    // Getters and Setters

    public ServicoFuncionario getServicoFuncionario() {
        return servicoFuncionario;
    }

    public void setServicoFuncionario(ServicoFuncionario servicoFuncionario) {
        this.servicoFuncionario = servicoFuncionario;
    }

    public ServicoFolhaDePonto getServicoFolhaDePonto() {
        return servicoFolhaDePonto;
    }

    public void setServicoFolhaDePonto(ServicoFolhaDePonto servicoFolhaDePonto) {
        this.servicoFolhaDePonto = servicoFolhaDePonto;
    }

    public FolhaDePonto getFolhaDePonto() {
        return folhaDePonto;
    }

    public void setFolhaDePonto(FolhaDePonto folhaDePonto) {
        this.folhaDePonto = folhaDePonto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<EntradaSaida> getEntradasaida() {
        return entradaSaida;
    }

    public void setEntradasaida(List<EntradaSaida> entradasaida) {
        this.entradaSaida = entradasaida;
    }

    public List<FolhaDePonto> getFolhaDePontos() {
        return folhaDePontos;
    }

    public void setFolhaDePontos(List<FolhaDePonto> folhaDePontos) {
        this.folhaDePontos = folhaDePontos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<EntradaSaida> getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(List<EntradaSaida> entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.servicoFuncionario);
        hash = 17 * hash + Objects.hashCode(this.servicoFolhaDePonto);
        hash = 17 * hash + Objects.hashCode(this.folhaDePontos);
        hash = 17 * hash + Objects.hashCode(this.folhaDePonto);
        hash = 17 * hash + Objects.hashCode(this.funcionarios);
        hash = 17 * hash + Objects.hashCode(this.funcionario);
        hash = 17 * hash + Objects.hashCode(this.entradaSaida);
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
        final ManagerFolhaDePonto other = (ManagerFolhaDePonto) obj;
        if (!Objects.equals(this.servicoFuncionario, other.servicoFuncionario)) {
            return false;
        }
        if (!Objects.equals(this.servicoFolhaDePonto, other.servicoFolhaDePonto)) {
            return false;
        }
        if (!Objects.equals(this.folhaDePontos, other.folhaDePontos)) {
            return false;
        }
        if (!Objects.equals(this.folhaDePonto, other.folhaDePonto)) {
            return false;
        }
        if (!Objects.equals(this.funcionarios, other.funcionarios)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        return Objects.equals(this.entradaSaida, other.entradaSaida);
    }

    @Override
    public String toString() {
        return "ManagerFolhaDePonto{" + "servicoFuncionario=" + servicoFuncionario + ", servicoFolhaDePonto=" + servicoFolhaDePonto + ", folhaDePontos=" + folhaDePontos + ", folhaDePonto=" + folhaDePonto + ", funcionarios=" + funcionarios + ", funcionario=" + funcionario + ", entradaSaida=" + entradaSaida + '}';
    }
    
    
    
}

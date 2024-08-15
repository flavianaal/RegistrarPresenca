package programe.io.Manager;

/**
 *
 * @author Flaviana Andrade
 */
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
    private List<EntradaSaida> entradaSaidas;
    private EntradaSaida entradaSaida;

    @PostConstruct
    public void instanciar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String visualizar = params.get("visualizar");
        String editar = params.get("editar");

        if (visualizar != null) {
            // Se estamos visualizando, carregue a FolhaDePonto com base no ID fornecido
            folhaDePonto = servicoFolhaDePonto.find(Long.parseLong(visualizar));

            if (folhaDePonto != null) {
                funcionario = folhaDePonto.getFuncionario();
                entradaSaidas = folhaDePonto.getEntradasSaidas();
            } else {
                // Caso a FolhaDePonto não seja encontrada, instancie novos objetos
                folhaDePonto = new FolhaDePonto();
                entradaSaidas = new ArrayList<>();
                funcionario = new Funcionario();
            }

        } else if (editar != null) {
            // Se estamos editando, carregue a FolhaDePonto com base no ID fornecido
            folhaDePonto = servicoFolhaDePonto.find(Long.parseLong(editar));

            if (folhaDePonto != null) {
                System.out.println("Antes de carregar Funcionario (Visualizar): " + funcionario);
                funcionario = folhaDePonto.getFuncionario();
                System.out.println("Depois de carregar Funcionario (Visualizar): " + funcionario);
                entradaSaidas = folhaDePonto.getEntradasSaidas();
            } else {
                // Caso a FolhaDePonto não seja encontrada, instancie novos objetos
                folhaDePonto = new FolhaDePonto();
                entradaSaidas = new ArrayList<>();
                funcionario = new Funcionario();
            }

        } else {
            // Se não for visualizar nem editar, crie novos objetos para uma nova entrada
            folhaDePonto = new FolhaDePonto();
            entradaSaidas = new ArrayList<>();
            funcionario = new Funcionario();
        }

        // Verifique se o funcionario está corretamente associado à FolhaDePonto
        if (folhaDePonto.getFuncionario() == null) {
            folhaDePonto.setFuncionario(funcionario);
        }

        // Inicialize uma nova EntradaSaida
        entradaSaida = new EntradaSaida();

        // Se a lista de entradaSaidas for nula, inicialize-a como uma nova lista vazia
        if (folhaDePonto.getEntradasSaidas() == null) {
            folhaDePonto.setEntradasSaidas(new ArrayList<>());
        }
    }

    public void adicionarEntradaSaida() {
        if (folhaDePonto.getFuncionario() == null || folhaDePonto.getData() == null) {
            Mensagem.msg("Erro: Funcionário e Data são obrigatórios.");
            return;
        }

        boolean exists = entradaSaidas.stream()
                .anyMatch(e -> e.getHoraEntrada().equals(entradaSaida.getHoraEntrada())
                && e.getHoraSaida().equals(entradaSaida.getHoraSaida()));

        if (!exists) {
            entradaSaidas.add(entradaSaida);
            entradaSaida = new EntradaSaida(); // Limpa para nova entrada
        } else {
            Mensagem.msg("A entrada/saída já foi adicionada.");
        }
    }

    public void salvar() {
        try {
            // Verifique se a folhaDePonto e a lista de entradaSaidas não estão vazias
            if (folhaDePonto != null && !entradaSaidas.isEmpty()) {
                System.out.println("Funcionário selecionado: " + funcionario);
                System.out.println("ID do funcionário: " + (funcionario != null ? funcionario.getId() : "Nulo"));

                // Verifique se o funcionário está corretamente associado com uma PK válida
                if (funcionario != null && funcionario.getId() != null) {
                    // Busca o funcionário no banco de dados
                    Funcionario funcionarioExistente = servicoFuncionario.find(funcionario.getId());

                    if (funcionarioExistente != null) {
                        // Utilize o objeto já existente para evitar duplicações
                        folhaDePonto.setFuncionario(funcionarioExistente);
                        System.out.println("Funcionario a ser salvo: " + funcionario);

                    } else {
                        Mensagem.msg("Erro: Funcionário não encontrado no banco de dados.");
                        return;
                    }
                } else {
                    Mensagem.msg("Erro: ID do funcionário é nula.");
                    return;
                }

                // Certifique-se de que a lista de entradas e saídas está corretamente associada
                folhaDePonto.setEntradasSaidas(entradaSaidas);

                // Salve ou atualize a folhaDePonto
                if (folhaDePonto.getId() == null) {
                    servicoFolhaDePonto.salvar(folhaDePonto);
                    Mensagem.msg("Folha de ponto salva com sucesso!");
                } else {
                    servicoFolhaDePonto.atualizar(folhaDePonto);
                    Mensagem.msg("Folha de ponto atualizada com sucesso!");
                }

                // Limpa o formulário após salvar
                instanciar();

            } else {
                Mensagem.msg("Erro: Dados inválidos. Certifique-se de que a folha de ponto e as entradas/saídas estão preenchidos corretamente.");
            }
        } catch (Exception e) {
            Mensagem.msg("Erro ao registrar a presença: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pesquisar() {
        folhaDePontos = servicoFolhaDePonto.findByAll(folhaDePonto);
    }

    public void remover() {
        servicoFolhaDePonto.delete(folhaDePonto);
        Mensagem.msg("Registro de Presença removido com sucesso");
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

    public List<EntradaSaida> getEntradaSaidas() {
        return entradaSaidas;
    }

    public void setEntradaSaidas(List<EntradaSaida> entradaSaidas) {
        this.entradaSaidas = entradaSaidas;
    }

    public EntradaSaida getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(EntradaSaida entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.servicoFuncionario);
        hash = 97 * hash + Objects.hashCode(this.servicoFolhaDePonto);
        hash = 97 * hash + Objects.hashCode(this.folhaDePontos);
        hash = 97 * hash + Objects.hashCode(this.folhaDePonto);
        hash = 97 * hash + Objects.hashCode(this.funcionarios);
        hash = 97 * hash + Objects.hashCode(this.funcionario);
        hash = 97 * hash + Objects.hashCode(this.entradaSaidas);
        hash = 97 * hash + Objects.hashCode(this.entradaSaida);
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
        if (!Objects.equals(this.entradaSaidas, other.entradaSaidas)) {
            return false;
        }
        return Objects.equals(this.entradaSaida, other.entradaSaida);
    }

    @Override
    public String toString() {
        return "ManagerFolhaDePonto{" + "servicoFuncionario=" + servicoFuncionario + ", servicoFolhaDePonto=" + servicoFolhaDePonto + ", folhaDePontos=" + folhaDePontos + ", folhaDePonto=" + folhaDePonto + ", funcionarios=" + funcionarios + ", funcionario=" + funcionario + ", entradaSaidas=" + entradaSaidas + ", entradaSaida=" + entradaSaida + '}';
    }
}

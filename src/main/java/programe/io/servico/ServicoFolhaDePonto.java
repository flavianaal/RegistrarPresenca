package programe.io.servico;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import programe.io.Modelo.FolhaDePonto;
import programe.io.Modelo.Funcionario;
import programe.io.generico.ServicoGenerico;

/**
 *
 * @author Flaviana Andrade
 */
@Stateless
public class ServicoFolhaDePonto extends ServicoGenerico<FolhaDePonto> {

    @PersistenceContext
    private EntityManager entityManager;

    public ServicoFolhaDePonto() {
        super(FolhaDePonto.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    //pesquisar todos as folhas de pontos
    public List<FolhaDePonto> findByAll(FolhaDePonto folhaDePonto) {
        StringBuilder sql = new StringBuilder("SELECT fp FROM FolhaDePonto fp WHERE fp.ativo = true");

        if (folhaDePonto.getData() != null) {
            sql.append(" AND fp.data LIKE :data");
        }

        TypedQuery<FolhaDePonto> query = getEntityManager().createQuery(sql.toString(), FolhaDePonto.class);

        if (folhaDePonto.getData() != null) {
            query.setParameter("data", "%" + folhaDePonto.getData() + "%");
        }

        return query.getResultList();
    }

    //PESQUISAR POR FUNCIONARIO
 
    public List<Funcionario> findFuncionario(String nome) {
        // Início da consulta
        String sql = "select f from Funcionario f where f.ativo = true";

        // Adiciona condição de nome se o parâmetro nome não for nulo
        if (nome != null && !nome.trim().isEmpty()) {
            sql += " and lower(f.nome) like lower(:nome)";
        }

        // Ordenação
        sql += " ORDER BY f.nome ASC";

        // Criação da query
        Query query = getEntityManager().createQuery(sql);

        // Passagem do parâmetro nome, se necessário
        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", "%" + nome.trim() + "%");
        }

        // Retorna a lista de resultados
        return query.getResultList();
    }    

    public Funcionario findFuncionarioById(Long id) {
        return entityManager.find(Funcionario.class, id);
    }


}
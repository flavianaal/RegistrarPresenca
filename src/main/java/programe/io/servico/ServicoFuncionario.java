package programe.io.servico;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import programe.io.Modelo.Endereco;
import programe.io.Modelo.Funcionario;
import programe.io.generico.ServicoGenerico;

@Stateless
public class ServicoFuncionario extends ServicoGenerico<Funcionario> {
    
    @PersistenceContext
    private EntityManager entityManager;

    public ServicoFuncionario() {
        super(Funcionario.class);
    }

    public List<Funcionario> findByAll(Funcionario funcionario) {
        // Construindo a JPQL
        StringBuilder jpql = new StringBuilder("select f from Funcionario f where f.ativo = true");
        List<String> conditions = new ArrayList<>();

        // Adicionando condições baseadas nos parâmetros do funcionário
        if (funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            conditions.add("lower(f.nome) like lower(:nome)");
        }
        
        if (funcionario.getCargo() != null) {
            conditions.add("f.cargo = :cargo");
        }
        
        // Adicionando as condições à query
        if (!conditions.isEmpty()) {
            jpql.append(" and ");
            jpql.append(String.join(" and ", conditions));
        }

        // Criando a query
        TypedQuery<Funcionario> query = entityManager.createQuery(jpql.toString(), Funcionario.class);

        // Configurando parâmetros
        if (funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            query.setParameter("nome", "%" + funcionario.getNome() + "%");
        }
        
        if (funcionario.getCargo() != null) {
            query.setParameter("cargo", funcionario.getCargo());
        }

        return query.getResultList();
    }
    
    public List<Funcionario> findFuncionario(String nome) {
        // Cria uma consulta JPQL para buscar funcionários pelo nome
        String jpql = "SELECT f FROM Funcionario f WHERE LOWER(f.nome) LIKE LOWER(:nome)";
        TypedQuery<Funcionario> query = entityManager.createQuery(jpql, Funcionario.class);
        query.setParameter("nome", "%" + nome + "%"); // Busca usando 'LIKE' com caracteres coringa
        return query.getResultList();
    }
    
    public Endereco findEndereco(Endereco endereco) {
        String query = "SELECT e FROM Endereco e WHERE e.cep = :cep AND e.logradouro = :logradouro AND e.numero = :numero";
        TypedQuery<Endereco> typedQuery = entityManager.createQuery(query, Endereco.class);
        typedQuery.setParameter("cep", endereco.getCEP());
        typedQuery.setParameter("logradouro", endereco.getLogradouro());
        typedQuery.setParameter("numero", endereco.getNumero());
        List<Endereco> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
   

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
}
package programe.io.servico;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import programe.io.Modelo.Funcionario;
import programe.io.generico.ServicoGenerico;

@Stateless
public class ServicoFuncionario extends ServicoGenerico<Funcionario> {
    
    public ServicoFuncionario() {
        super(Funcionario.class);
    }
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<Funcionario> findByAll(Funcionario funcionario) {
        // INICIANDO A QUERY DO BANCO DE DADOS
        StringBuilder sql = new StringBuilder("select f from Funcionario f where f.ativo = true");
        List<String> conditions = new ArrayList<>();

        // CASO O NOME DO CLIENTE TENHA UM VALOR INSERIDO ELE ENTRA NO IF
        if (funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            conditions.add("lower(f.nome) like lower(:nome)");
        }
        
        // CASO O CARGO DO CLIENTE TENHA UM VALOR INSERIDO ELE ENTRA NO IF
        if (funcionario.getCargo() != null) {
            conditions.add("f.cargo = :cargo");
        }
        
        // Adiciona condições à query se existirem
        if (!conditions.isEmpty()) {
            sql.append(" and ");
            sql.append(String.join(" and ", conditions));
        }

        // LENDO A QUERY
        Query query = getEntityManager().createQuery(sql.toString(), Funcionario.class);

        // PASSANDO OS PARÂMETROS
        if (funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            query.setParameter("nome", "%" + funcionario.getNome() + "%");
        }
        
        if (funcionario.getCargo() != null) {
            query.setParameter("cargo", funcionario.getCargo());
        }

        return query.getResultList();
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

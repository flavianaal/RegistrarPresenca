
package programe.io.servico;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    public ServicoFolhaDePonto(){
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
    public List<FolhaDePonto> findByAll(FolhaDePonto folhaDePonto){
        
        //INICIANDO A QUERY DO BANCO DE DADOS
        String sql = "select fp from FolhaDePonto fp where ";
                              
        if(folhaDePonto.getData()!= null && !folhaDePonto.getData().equals("")){
            sql += "fp.folhaDePonto like :folhaDePonto and ";
        }
        
        //A CONSULTA VAI SEMPRE TRAZER OS CLIENTES ATIVOS NO BANCO DE DADOS
        sql += "fp.ativo = true";
        
        //LENDO A QUERY
        
        Query query = getEntityManager().createQuery(sql, FolhaDePonto.class);
        //PASSANDO OS PARAMETROS
                                        
        if(folhaDePonto.getData()!= null && !folhaDePonto.getData().equals("")){
           query.setParameter("data", "%"+folhaDePonto.getData()+"%");
        }
        
        return query.getResultList();
    }
    
    //AUTO COMPLITE
    
    //PESQUISAR POR FUNCIONARIO
    public List<Funcionario> findFuncionario(String nome) {
        String sql = "select f from Funcionario f where ";

        if (nome != null && !nome.equals(nome)) {
            sql += "lower(f.nome) like lower(:nome) and ";
        }
        sql += "f.ativo = true ORDER BY f.nome ASC";

        Query query = getEntityManager().createQuery(sql);

       if (nome != null && !nome.equals(nome)){
            query.setParameter("nome", "%" + nome.trim() + "%");
        }

        return query.getResultList();
    }    
    
    
   
}

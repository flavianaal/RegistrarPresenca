/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.servico;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import programe.io.Modelo.EntradaSaida;
import programe.io.generico.ServicoGenerico;

/**
 *
 * @author Flaviana Andrade
 */
@Stateless
public class ServicoEntradaSaida extends ServicoGenerico<EntradaSaida>{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public ServicoEntradaSaida(){
      super(EntradaSaida.class);
    } 

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    //pesquisar todos as entradas e saidas
    public List<EntradaSaida> findByAll(EntradaSaida entradaSaida){
        
        //INICIANDO A QUERY DO BANCO DE DADOS
        String sql = "select es from EntradaSaida es where ";
                              
        if(entradaSaida.getHoraEntrada()!= null && !entradaSaida.getHoraEntrada().equals("")){
            sql += "es.entradaSaida like :entradaSaida and ";
        }
        
        //A CONSULTA VAI SEMPRE TRAZER OS CLIENTES ATIVOS NO BANCO DE DADOS
        sql += "es.ativo = true";
        
        //LENDO A QUERY
        
        Query query = getEntityManager().createQuery(sql, EntradaSaida.class);
        //PASSANDO OS PARAMETROS
                                        
        if(entradaSaida.getHoraEntrada()!= null && !entradaSaida.getHoraEntrada().equals("")){
           query.setParameter("horaEntrada", "%"+entradaSaida.getHoraEntrada()+"%");
        }
        
        return query.getResultList();
    }
    
    


    
    
}

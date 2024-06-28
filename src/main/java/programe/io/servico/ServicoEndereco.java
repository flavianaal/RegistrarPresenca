/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.servico;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import programe.io.Modelo.Endereco;
import programe.io.generico.ServicoGenerico;

/**
 *
 * @author Flaviana Andrade
 */
@Stateless
public class ServicoEndereco extends ServicoGenerico<Endereco>{
    
    public ServicoEndereco(){
      super(Endereco.class);
    } 
    
    
    public List<Endereco> pesquisarEndereco(Endereco endereco) {
        String sql = "select e from Endereco e where  ";

        if (endereco.getLogradouro()!= null && !endereco.getLogradouro().equals("")) {
            sql += "lower(e.nomeRua) like lower(:nomeRua) and";
        }

        if (endereco.getNumero()!= null && !endereco.getNumero().equals("")) {
            sql += " e.numero like :numero and ";
        }
        
        
        sql += " e.ativo = true";
        Query query = getEntityManager().createQuery(sql, Endereco.class);

        if (endereco.getLogradouro()!= null && !endereco.getLogradouro().equals("")) {
            query.setParameter("nomeRua", "%" +endereco.getLogradouro()+ "%");
        }

        if (endereco.getNumero()!= null && !endereco.getNumero().equals("")) {
            query.setParameter("numero", "%" + endereco.getNumero() + "%");
        }

        return query.getResultList();
    }
    
}

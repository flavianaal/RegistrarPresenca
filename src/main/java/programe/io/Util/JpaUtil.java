/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Util;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Flaviana Andrade
 */
public class JpaUtil {
    
    private static EntityManagerFactory emf;
    
    static {
        emf = Persistence.createEntityManagerFactory("RegistrarPresencaPU");
        
    }
    public static EntityManager conexao(){
        return emf.createEntityManager();
    }
    public static void fecharConexao(){
        emf.close();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Flaviana Andrade
 */
public class Mensagem {
    
      public static void msg(String mensagem){
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(mensagem));
    }
    
}

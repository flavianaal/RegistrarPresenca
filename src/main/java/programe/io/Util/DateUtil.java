/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Util;

/**
 *
 * @author Flaviana Andrade
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date StringToDate(String dataRecebida) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dataFormatada = formato.parse(dataRecebida);
        return dataFormatada;
    }
}


    

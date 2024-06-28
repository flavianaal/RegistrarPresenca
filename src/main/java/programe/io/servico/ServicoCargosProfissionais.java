/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.servico;

import java.util.Arrays;
import java.util.List;
import programe.io.enums.CargosProfissionais;
import programe.io.generico.EntidadeGenerica;
import programe.io.generico.ServicoGenerico;

/**
 *
 * @author Flaviana Andrade
 */
public class ServicoCargosProfissionais extends ServicoGenerico<EntidadeGenerica>{
    
     public ServicoCargosProfissionais() {
        super(EntidadeGenerica.class);
    }
    
    public List<CargosProfissionais> getAllCargos() {
        return Arrays.asList(CargosProfissionais.values());
    }
    
}

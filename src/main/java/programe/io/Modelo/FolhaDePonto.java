/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Modelo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import programe.io.generico.EntidadeGenerica;


/**
 *
 * @author Flaviana Andrade
 */

@Entity
@Table
public class FolhaDePonto extends EntidadeGenerica {
    
    @Id
    @SequenceGenerator(name = "seq_folhaDePonto", sequenceName = "seq_folhaDePonto", initialValue = 1)
    @GeneratedValue(generator = "seq_folhaDePonto", strategy = GenerationType.SEQUENCE)
    private Long Id;
   
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Funcionario funcionario;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntradaSaida> entradaSaidas;

    public FolhaDePonto() {
    }
    
    public FolhaDePonto(Long id, Date data, Funcionario funcionario, List<EntradaSaida> entradasSaidas) {
        this.Id = id;
        this.data = data;
        this.funcionario = funcionario;
        this.entradaSaidas = entradasSaidas != null ? entradasSaidas : new ArrayList<>();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        System.out.println(funcionario);
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<EntradaSaida> getEntradasSaidas() {
        return entradaSaidas;
    }

    public void setEntradasSaidas(List<EntradaSaida> entradasSaidas) {
        this.entradaSaidas = entradasSaidas;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.Id);
        hash = 97 * hash + Objects.hashCode(this.data);
        hash = 97 * hash + Objects.hashCode(this.funcionario);
        hash = 97 * hash + Objects.hashCode(this.entradaSaidas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FolhaDePonto other = (FolhaDePonto) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        return Objects.equals(this.entradaSaidas, other.entradaSaidas);
    }

    @Override
    public String toString() {
        return "FolhaDePonto{" + "Id=" + Id + ", data=" + data + ", funcionario=" + funcionario + ", entradasSaidas=" + entradaSaidas + '}';
    }
    
    


    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Modelo;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import programe.io.generico.EntidadeGenerica;

/**
 *
 * @author Flaviana Andrade
 */
@Entity
@Table
public class EntradaSaida extends EntidadeGenerica {
    @Id
    @SequenceGenerator(name = "seq_entradaSaida", sequenceName = "seq_entradaSaida", initialValue = 1)
    @GeneratedValue(generator = "seq_entradaSaida", strategy = GenerationType.SEQUENCE)
    private Long Id;
    
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Temporal(TemporalType.TIME)
    private Date horaSaida;

    public EntradaSaida() {
    }

    public EntradaSaida(Long Id, Date horaEntrada, Date horaSaida) {
        this.Id = Id;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
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
        hash = 43 * hash + Objects.hashCode(this.Id);
        hash = 43 * hash + Objects.hashCode(this.horaEntrada);
        hash = 43 * hash + Objects.hashCode(this.horaSaida);
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
        final EntradaSaida other = (EntradaSaida) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (!Objects.equals(this.horaEntrada, other.horaEntrada)) {
            return false;
        }
        return Objects.equals(this.horaSaida, other.horaSaida);
    }

    @Override
    public String toString() {
        return "EntradaSaida{" + "Id=" + Id + ", horaEntrada=" + horaEntrada + ", horaSaida=" + horaSaida + '}';
    }
    
    
    
   
    
    
}

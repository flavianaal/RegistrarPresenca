/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programe.io.Modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.NaturalId;
import programe.io.generico.EntidadeGenerica;


/**
 *
 * @author Flaviana Andrade
 */

@Entity
@Table
public class Endereco extends EntidadeGenerica {
    
    @Id
    @SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", initialValue = 1)
    @GeneratedValue(generator = "seq_endereco", strategy = GenerationType.IDENTITY)
    private Long Id;
    @NaturalId
    private String logradouro;
    @NaturalId
    private String numero;
    @NaturalId
    private String bairro;
    @NaturalId
    private String cidade;
    @NaturalId
    private String Estado;
    @NaturalId
    private String CEP;

    public Endereco() {
    }

    public Endereco(Long Id, String logradouro, String numero, String bairro, String cidade, String Estado, String CEP) {
        this.Id = Id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.Estado = Estado;
        this.CEP = CEP;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    @Override
    public Boolean getAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.Id);
        hash = 31 * hash + Objects.hashCode(this.logradouro);
        hash = 31 * hash + Objects.hashCode(this.numero);
        hash = 31 * hash + Objects.hashCode(this.bairro);
        hash = 31 * hash + Objects.hashCode(this.cidade);
        hash = 31 * hash + Objects.hashCode(this.Estado);
        hash = 31 * hash + Objects.hashCode(this.CEP);
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.Estado, other.Estado)) {
            return false;
        }
        if (!Objects.equals(this.CEP, other.CEP)) {
            return false;
        }
        return Objects.equals(this.Id, other.Id);
    }

    @Override
    public String toString() {
        return "Endereco{" + "Id=" + Id + ", logradouro=" + logradouro + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", Estado=" + Estado + ", CEP=" + CEP + '}';
    }

    
}


package programe.io.Modelo;


import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import programe.io.enums.CargosProfissionais;
import programe.io.generico.EntidadeGenerica;

/**
 *
 * @author Flaviana Andrade
 */
@Entity
@Table
public class Funcionario extends EntidadeGenerica {
    
    @Id
    @SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario", initialValue = 1)
    @GeneratedValue(generator = "seq_funcionario", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column
    private String nome;
    private String matricula;
    
    @Enumerated(EnumType.STRING)
    private CargosProfissionais cargo;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<FolhaDePonto> folhaDePontos;

    public Funcionario() {
    }

    public Funcionario(Long id, String nome, String matricula, CargosProfissionais cargo, Endereco endereco, List<FolhaDePonto> folhaDePontos) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.endereco = endereco;
        this.folhaDePontos = folhaDePontos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public CargosProfissionais getCargo() {
        return cargo;
    }

    public void setCargo(CargosProfissionais cargo) {
        this.cargo = cargo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<FolhaDePonto> getFolhaDePontos() {
        return folhaDePontos;
    }

    public void setFolhaDePontos(List<FolhaDePonto> folhaDePontos) {
        this.folhaDePontos = folhaDePontos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.matricula);
        hash = 79 * hash + Objects.hashCode(this.cargo);
        hash = 79 * hash + Objects.hashCode(this.endereco);
        hash = 79 * hash + Objects.hashCode(this.folhaDePontos);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.cargo != other.cargo) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        return Objects.equals(this.folhaDePontos, other.folhaDePontos);
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", cargo=" + cargo + ", endereco=" + endereco + ", folhaDePontos=" + folhaDePontos + '}';
    }
    
    
   
   
 }
    

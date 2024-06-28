package programe.io.Manager;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import programe.io.enums.CargosProfissionais;

@Named
@ViewScoped
public class ManagerCargosProfissionais implements Serializable {

    private CargosProfissionais cargosProfissionaisSelecionado;
    private SelectItem[] profissoes;

    public ManagerCargosProfissionais() {
        profissoes = new SelectItem[CargosProfissionais.values().length];
        int i = 0;
        for (CargosProfissionais cargo : CargosProfissionais.values()) {
            profissoes[i++] = new SelectItem(cargo, cargo.toString());
        }
    }

    public CargosProfissionais getCargosProfissionaisSelecionado() {
        return cargosProfissionaisSelecionado;
    }

    public void setCargosProfissionaisSelecionado(CargosProfissionais cargosProfissionaisSelecionado) {
        this.cargosProfissionaisSelecionado = cargosProfissionaisSelecionado;
    }

    public SelectItem[] getProfissoes() {
        return profissoes;
    }

    public void setProfissoes(SelectItem[] profissoes) {
        this.profissoes = profissoes;
    }

    public void selecionarCargo() {
        if (cargosProfissionaisSelecionado != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargo Selecionado", "Cargo: " + cargosProfissionaisSelecionado.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum Cargo Selecionado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.cargosProfissionaisSelecionado);
        hash = 71 * hash + Arrays.deepHashCode(this.profissoes);
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
        final ManagerCargosProfissionais other = (ManagerCargosProfissionais) obj;
        if (this.cargosProfissionaisSelecionado != other.cargosProfissionaisSelecionado) {
            return false;
        }
        return Arrays.deepEquals(this.profissoes, other.profissoes);
    }

    @Override
    public String toString() {
        return "ManagerCargosProfissionais{" + "cargosProfissionaisSelecionado=" + cargosProfissionaisSelecionado + ", profissoes=" + profissoes + '}';
    }
    
    
}

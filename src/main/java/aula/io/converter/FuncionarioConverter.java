package aula.io.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import programe.io.Modelo.Funcionario;

/**
 *
 * @author arthur
 */
@FacesConverter(value = "funcionarioconverter", forClass = Funcionario.class ) //forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Funcionario) uiComponent.getAttributes().get(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

        if (value instanceof Funcionario) {

            Funcionario entity = (Funcionario) value;

            if (entity != null && entity instanceof Funcionario&& entity.getId() != null) {
                uiComponent.getAttributes().put(entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }
}
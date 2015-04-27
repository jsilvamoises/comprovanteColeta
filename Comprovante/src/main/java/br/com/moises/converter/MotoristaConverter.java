/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.converter;

import br.com.moises.model.Motorista;
import br.com.moises.suport.MotoristaSuport;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = Motorista.class)
public class MotoristaConverter implements javax.faces.convert.Converter {
    private final MotoristaSuport suport = new MotoristaSuport();
      @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Motorista pessoa = null;

        if (value != null) {
            try {
                Long id = new Long(value);
                System.out.println("Passou no pessoa converter");
                pessoa = (Motorista) new MotoristaSuport().getMotoristaById(id);
            } catch (Exception e) {
            }

        }
        return pessoa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long id = ((Motorista) value).getId();
            return String.valueOf(id);
        }
        return "";
    }

}

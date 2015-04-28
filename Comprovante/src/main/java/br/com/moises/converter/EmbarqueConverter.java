/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.converter;

import br.com.moises.model.Embarque;
import br.com.moises.suport.EmbarqueSuport;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = Embarque.class)
public class EmbarqueConverter implements javax.faces.convert.Converter {
    private final EmbarqueSuport suport = new EmbarqueSuport();
    
      @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Embarque embarque = null;

        if (value != null) {
            try {
                Long id = new Long(value);
                System.out.println("Passou no embarque converter");
                embarque = (Embarque) new EmbarqueSuport().getEmbarqueById(id);
            } catch (Exception e) {
            }

        }
        return embarque;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long id = ((Embarque) value).getId();
            return String.valueOf(id);
        }
        return "";
    }

}

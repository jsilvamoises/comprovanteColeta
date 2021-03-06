/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.converter;

import br.com.moises.model.Transportadora;
import br.com.moises.suport.TransportadoraSuport;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = Transportadora.class)
public class TransportadoraConverter implements javax.faces.convert.Converter {
    private TransportadoraSuport suport = new TransportadoraSuport();
    
      @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Transportadora transportadora = null;

        if (value != null) {
            try {
                Long id = new Long(value);
                System.out.println("Passou no transportadora converter");
                transportadora = (Transportadora) new TransportadoraSuport().getObjetoById(id);
            } catch (Exception e) {
            }

        }
        return transportadora;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long id = ((Transportadora) value).getId();
            return String.valueOf(id);
        }
        return "";
    }

}

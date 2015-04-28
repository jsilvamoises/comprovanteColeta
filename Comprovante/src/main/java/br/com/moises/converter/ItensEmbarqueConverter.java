/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.converter;

import br.com.moises.model.ItensEmbarque;
import br.com.moises.suport.ItensEmbarqueSuport;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = ItensEmbarque.class)
public class ItensEmbarqueConverter implements javax.faces.convert.Converter {
    private final ItensEmbarqueSuport suport = new ItensEmbarqueSuport();
    
      @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ItensEmbarque itensEmbarque = null;

        if (value != null) {
            try {
                Long id = new Long(value);
                System.out.println("Passou no itensEmbarque converter");
                itensEmbarque = (ItensEmbarque) new ItensEmbarqueSuport().getItensEmbarqueById(id);
            } catch (Exception e) {
            }

        }
        return itensEmbarque;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long id = ((ItensEmbarque) value).getId();
            return String.valueOf(id);
        }
        return "";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.converter;

import br.com.moises.model.Cliente;
import br.com.moises.suport.ClienteSuport;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements javax.faces.convert.Converter {
    private ClienteSuport suport = new ClienteSuport();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cliente anoAplicacao = null;
        
        if(value!=null){
            Long id = new Long(value);
            return suport.getTransportadoraById(id);
        }
        return anoAplicacao;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!= null){
            Long id = ((Cliente)value).getId();
            return String.valueOf(id);
        }
        return "";
    }

}

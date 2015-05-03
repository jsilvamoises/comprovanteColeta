/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.model.Transportadora;
import br.com.moises.suport.TransportadoraSuport;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MOISES
 */
@ManagedBean
@ViewScoped
public class CadastroTransportadoraBean implements Serializable {

    private static final Long serialVersionUID = 1L;
    
    
    private Transportadora transportadora;
    private final TransportadoraSuport suport;

    public CadastroTransportadoraBean() {
        suport = new TransportadoraSuport();
        criarObjeto();
    }

    public void limpar() {
        criarObjeto();
    }

    public void save() {
        if (suport.save(transportadora)) {
            criarObjeto();
        }
    }

    public void delete() {
        if (suport.delete(transportadora)) {
            criarObjeto();
        }
    }

    

    private void criarObjeto() {
        transportadora = new Transportadora();

    }

  

    

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        if (transportadora != null) 
            this.transportadora = transportadora;
       
    }

}

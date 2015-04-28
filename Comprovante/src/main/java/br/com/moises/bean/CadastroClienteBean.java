/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.model.Cliente;
import br.com.moises.suport.ClienteSuport;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MOISES
 */
@ManagedBean
@ViewScoped
public class CadastroClienteBean implements Serializable{
    private static final Long serialVersionUID= 1L;
    
    private Cliente cliente;
    private ClienteSuport suport;

    public CadastroClienteBean() {
      suport = new ClienteSuport();
      criarObjeto();
    }
    
    
    public void limpar(){
        criarObjeto();
    }
    
    public void save(){
        if(suport.save(cliente)){
            criarObjeto();
        }
    }
    public void delete(){
        if(suport.delete(cliente)){
            criarObjeto();
        }
    }
    
    private void criarObjeto(){
          cliente = new Cliente();
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if(cliente!=null)
        this.cliente = cliente;
    }

   
    
}

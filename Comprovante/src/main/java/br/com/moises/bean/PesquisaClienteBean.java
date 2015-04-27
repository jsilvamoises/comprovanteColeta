/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.model.Cliente;
import br.com.moises.suport.ClienteSuport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MOISES
 */
@ManagedBean
@ViewScoped
public class PesquisaClienteBean  implements Serializable{
    private static final Long serialVersionUID=1L;
    
    private String campo;
    
    private boolean tamanhoLista;
    
    private List<Cliente> clientes;
    private ClienteSuport suport;
    
    private Cliente motoristaSelecionado;

    public PesquisaClienteBean() {
        clientes = new ArrayList<>();
        suport = new ClienteSuport();
    }
    
    
    public void delete(){
        if(suport.delete(motoristaSelecionado)){
            pesquisarTodos();
        }
    }
    public void pesquisarTodos(){
      clientes =  suport.list();
    }
    
    public void pesquisarPorNome(){
      clientes =  suport.clientePorNome(campo);
        System.out.println("Lista Tamanho "+clientes.size()+campo);
    }

    public List<Cliente> getMotoristas() {
        return clientes;
    }

    public void setMotoristas(List<Cliente> clientes) {
        
        this.clientes = clientes;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public ClienteSuport getSuport() {
        return suport;
    }

    public void setSuport(ClienteSuport suport) {
        this.suport = suport;
    }

    public boolean isTamanhoLista() {
        tamanhoLista = clientes.size()>0;
        return tamanhoLista;
    }

    public Cliente getMotoristaSelecionado() {
        return motoristaSelecionado;
    }

    public void setMotoristaSelecionado(Cliente motoristaSelecionado) {
        this.motoristaSelecionado = motoristaSelecionado;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.model.Motorista;
import br.com.moises.suport.MotoristaSuport;
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
public class PesquisaMotoristaBean  implements Serializable{
    private static final Long serialVersionUID=1L;
    
    private String campo;
    
    private boolean tamanhoLista;
    
    private List<Motorista> motoristas;
    private MotoristaSuport suport;
    
    private Motorista motoristaSelecionado;

    public PesquisaMotoristaBean() {
        motoristas = new ArrayList<>();
        suport = new MotoristaSuport();
    }
    
    
    public void delete(){
        if(suport.delete(motoristaSelecionado)){
            pesquisarTodos();
        }
    }
    public void pesquisarTodos(){
      motoristas =  suport.list();
    }
    
    public void pesquisarPorPlaca(){
      motoristas =  suport.getPessoasByPlaca(campo);
        System.out.println("Lista Tamanho "+motoristas.size()+campo);
    }

    public List<Motorista> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(List<Motorista> motoristas) {
        
        this.motoristas = motoristas;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public MotoristaSuport getSuport() {
        return suport;
    }

    public void setSuport(MotoristaSuport suport) {
        this.suport = suport;
    }

    public boolean isTamanhoLista() {
        tamanhoLista = motoristas.size()>0;
        return tamanhoLista;
    }

    public Motorista getMotoristaSelecionado() {
        return motoristaSelecionado;
    }

    public void setMotoristaSelecionado(Motorista motoristaSelecionado) {
        this.motoristaSelecionado = motoristaSelecionado;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.model.Transportadora;
import br.com.moises.suport.TransportadoraSuport;
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
public class PesquisaTransportadoraBean implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String campo;
    
    private boolean tamanhoLista;

    private List<Transportadora> transportadoras;
    private TransportadoraSuport suport;

    public PesquisaTransportadoraBean() {
        transportadoras = new ArrayList<>();
        suport = new TransportadoraSuport();
    }

    public void pesquisarTodos() {
        transportadoras = suport.list();
    }

    public List<Transportadora> pesquisarTransportadoraPorNome(String nome) {
        System.err.println("pAOSUIDAR");
        if (!nome.isEmpty()) {
            return suport.transportadoraPorNome(nome);
        }
        return null;
    }

    public List<Transportadora> getMotoristas() {
        return transportadoras;
    }

    public void setMotoristas(List<Transportadora> transportadoras) {

        this.transportadoras = transportadoras;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public TransportadoraSuport getSuport() {
        return suport;
    }

    public void setSuport(TransportadoraSuport suport) {
        this.suport = suport;
    }

    public boolean isTamanhoLista() {
        tamanhoLista = transportadoras.isEmpty();
        return tamanhoLista;
    }

    public void setTamanhoLista(boolean tamanhoLista) {
        this.tamanhoLista = tamanhoLista;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.enums.StatusEmbarque;
import br.com.moises.model.Cliente;
import br.com.moises.model.Embarque;
import br.com.moises.model.ItensEmbarque;
import br.com.moises.model.Transportadora;
import br.com.moises.suport.EmbarqueSuport;
import br.com.moises.suport.ItensEmbarqueSuport;
import br.com.moises.suport.TransportadoraSuport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MOISES
 */
@ManagedBean
@ViewScoped
public class EmbarqueBean implements Serializable {

    private Embarque embarque;
    private List<ItensEmbarque> itens;
    private List<Embarque> embarques;
    private ItensEmbarque item;
    private final EmbarqueSuport embarqueSuport;
    private final ItensEmbarqueSuport itensSuport;
    
    private ItensEmbarque itemSelecionado;
    private Embarque embarqueSelecionado;

    private List<Transportadora> transportadoras;

    public EmbarqueBean() {
        embarque = new Embarque();
        itens = new ArrayList<>();
        embarques = new ArrayList<>();
        itensSuport = new ItensEmbarqueSuport();
        embarqueSuport = new EmbarqueSuport();
        item = new ItensEmbarque();
        transportadoras = new ArrayList<>();
    }
    
    
    public void embarcar(){
        embarque.setStatus(StatusEmbarque.EMBARCADO);
        embarqueSuport.save(embarque);
        novoEmbarque();
    }
    public void embarqueByQuery(){
        embarques = embarqueSuport.getEmbarquesAbertoByQuery();
    }

    public void criarEmbarque() {
        embarque.setStatus(StatusEmbarque.VAZIO);
        embarque.setDataCriacao(Calendar.getInstance().getTime());
        embarqueSuport.saveOrUpdate(embarque);
        embarques = embarqueSuport.list();
    }

    public void salvarEmbarque() {
        embarqueSuport.saveOrUpdate(embarque);
    }

    public void novoEmbarque() {
        embarque = new Embarque();
        itens = new ArrayList<>();
        embarques = new ArrayList<>();
        item = new ItensEmbarque();
    }

    public void cancelarEmbarque() {
        embarque.setStatus(StatusEmbarque.CANCELADO);

    }
    
    public void incluirItensNoEmbarque(){
        embarque = embarqueSelecionado;
        itens = itensSuport.itensEmbarquePorEmbarque(embarque);
        embarques = embarqueSuport.getEmbarquesEmAberto();
    }

    //ITENS DO EMBARQUE
    public void salvarItemDoEmbarque() {
       // Cliente cliente = item.getCliente();
        if(embarque.getStatus()==StatusEmbarque.VAZIO){
            embarque.setStatus(StatusEmbarque.CARREGANDO);
            embarqueSuport.save(embarque);
        }
        item.setDataInclusao(Calendar.getInstance().getTime());
        item.setEmbarque(embarque);
        itensSuport.saveOrUpdate(item);
       // embarque.getItens().add(item);
       // embarqueSuport.saveOrUpdate(embarque);

      //  embarques = embarqueSuport.list();

       // embarqueSuport.merge(embarque);
        item = new ItensEmbarque();
     //   item.setCliente(cliente);
        itens = itensSuport.itensEmbarquePorEmbarque(embarque);

       

    }
    
    public void editar(){
        item  = itemSelecionado;
    }

    public void salvarItenEditado() {
       // Cliente cliente = item.getCliente();
        itensSuport.merge(item);
        item = new ItensEmbarque();
        itens = itensSuport.itensEmbarquePorEmbarque(embarque);
       // item.setCliente(cliente);
    }
    
    public void mostrarEmbarqueEmAberto(){
        embarques = embarqueSuport.getEmbarquesEmAberto();
    }

    public Embarque getEmbarque() {
        return embarque;
    }

    public List<ItensEmbarque> getItens() {
        return itens;
    }

    public List<Embarque> getEmbarques() {
        return embarques;
    }

    public void setEmbarque(Embarque embarque) {
        if (embarque != null) {
            itens = new ArrayList<>();
            this.embarque = embarque;
            itens = itensSuport.itensEmbarquePorEmbarque(embarque);
            embarques = embarqueSuport.list();
        }

    }

    public void setItens(List<ItensEmbarque> itens) {
        this.itens = itens;
    }

    public void setEmbarques(List<Embarque> embarques) {
        this.embarques = embarques;
    }

    public ItensEmbarque getItem() {
        return item;
    }

    public void setItem(ItensEmbarque item) {
        if (item != null) {
            this.item = item;
        }
    }

    public void listarTodasTransportadoras() {
        transportadoras = new TransportadoraSuport().list();
    }

    public List<Transportadora> getTransportadoras() {

        return transportadoras;
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public ItensEmbarque getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(ItensEmbarque itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public Embarque getEmbarqueSelecionado() {
        return embarqueSelecionado;
    }

    public void setEmbarqueSelecionado(Embarque embarqueSelecionado) {
        this.embarqueSelecionado = embarqueSelecionado;
    }

}

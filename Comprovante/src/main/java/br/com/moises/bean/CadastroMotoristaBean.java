/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.model.Motorista;
import br.com.moises.suport.MotoristaSuport;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MOISES
 */
@ManagedBean
@ViewScoped
public class CadastroMotoristaBean implements Serializable{
    private static final Long serialVersionUID= 1L;
    
    private Motorista motorista;
    private MotoristaSuport suport;

    public CadastroMotoristaBean() {
      suport = new MotoristaSuport();
      criarObjeto();
    }
    
    
    public void limpar(){
        criarObjeto();
    }
    
    public void save(){
        if(suport.save(motorista)){
            criarObjeto();
        }
    }
    public void delete(){
        if(suport.delete(motorista)){
            criarObjeto();
        }
    }
    
    private void criarObjeto(){
          motorista = new Motorista();
        
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        if(motorista!=null)
        this.motorista = motorista;
    }
    
}

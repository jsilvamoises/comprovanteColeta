/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

import br.com.moises.enums.StatusEmbarque;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MOISES
 */
@ManagedBean
@ViewScoped
public class StatusEmbarqueBean implements Serializable{
    private StatusEmbarque[] status;

    public StatusEmbarque[] getStatus() {
        return StatusEmbarque.values();
    }
    
    
}

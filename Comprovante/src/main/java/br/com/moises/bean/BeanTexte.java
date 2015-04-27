/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.bean;

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
public class BeanTexte {
    private List<String> lista;

    public List getLista() {
        lista = new ArrayList<>();
        for(int i = 0 ; i < 20; i++){
            lista.add(String.valueOf(i));
        }
        return lista;
    }
     
    
}

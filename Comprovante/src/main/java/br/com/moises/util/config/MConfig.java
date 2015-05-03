/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.util.config;

/**
 *
 * @author MOISES
 */
public class MConfig {
    private static MConfig instance;
    public static MConfig getIntance(){
        return instance==null?instance = new MConfig():instance;
    }
    private String usuario;
    private String empresa;
    

    public String getUsuario() {
        usuario = System.getProperty("user.name");
        return usuario;
    }

    public String getEmpresa() {
        return empresa;
    }
    
    
}

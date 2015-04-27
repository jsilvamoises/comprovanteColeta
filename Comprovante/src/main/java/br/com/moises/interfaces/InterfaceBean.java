/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.interfaces;

import java.util.List;

/**
 *
 * @author Moises
 */
public interface InterfaceBean {
    public void save();
    public void delete();
    public void clear();
    public void edit();
    public void update();
    public List list();
}

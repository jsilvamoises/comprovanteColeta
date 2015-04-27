/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.teste;

import br.com.moises.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author MOISES
 */
public class TesteHibernate {
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.getTransaction().begin();
        s.getTransaction().commit();
    }
}

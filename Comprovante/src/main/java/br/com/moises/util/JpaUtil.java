/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Moises
 */
public final class JpaUtil {
    private static final String PERSISTENCE_UNIT = "DACPU";
    private static ThreadLocal<EntityManager>
            threadEntityManager = new ThreadLocal<>();
    
    private static EntityManagerFactory entityManagerFactory;

    public JpaUtil() {
    }
    
//    @Produces
    public static EntityManager getEntityManager(){
        if(entityManagerFactory==null){
            entityManagerFactory = Persistence .createEntityManagerFactory(PERSISTENCE_UNIT);
            
        }
        
        EntityManager entityManager = threadEntityManager.get();
        
        if(entityManager == null || !entityManager.isOpen()){
            entityManagerFactory.createEntityManager();
            JpaUtil.threadEntityManager.set(entityManager);
        }
        return entityManager;
    }
    
    public static void closeEntityManager(){
        EntityManager em = threadEntityManager.get();
        
        if(em!=null){
            EntityTransaction transaction = em.getTransaction();
            
            if(transaction.isActive()){
                transaction.commit();
                em.close();
                threadEntityManager.set(null);
            }
        }
    }
    
    
    public static void closeEntityManagerFactory(){
        closeEntityManager();
        entityManagerFactory.close();
    }
    
}

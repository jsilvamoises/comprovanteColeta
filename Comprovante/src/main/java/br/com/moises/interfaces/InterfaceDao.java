/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.interfaces;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author Moises
 * @param <T>
 */
public interface InterfaceDao<T> {
    boolean save(T entity);
    boolean saveOrUpdate(T entity);
    boolean update(T entity);
    boolean remove(T entity);
    boolean merge(T entity);
    T getEntity(Serializable id);
    T getEntityByDetachedCriteria(DetachedCriteria criteria);
    List<T> getEntities();
    List<T> getEntitiesByDetachetCriteria(DetachedCriteria criteria);
    List<T> getEntitiesByQuery(String query);
    public void commit(Session session);    
    void rollback(Session session);    
    public void begin(Session session);    
    public Session getSession();
}

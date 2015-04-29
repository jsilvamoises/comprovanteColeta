/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.dao;


import br.com.moises.interfaces.InterfaceDao;
import br.com.moises.util.FacesUtil;
import br.com.moises.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author Moises
 */
public class Dao<T> implements InterfaceDao<T>, Serializable {

    private static final Long SerialVersionUID = 1L;
    private Class<T> classe;

    public Dao(Class<T> classe) {
        this.classe = classe;
    }

    @Override
    public boolean save(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {

            session.getTransaction().begin();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            FacesUtil.addInfoMessage("Salvo com sucesso!!");
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            FacesUtil.addInfoMessage("Erro ao salvar!!!" + e);
            return false;
        }

    }

    @Override
    public boolean saveOrUpdate(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {

            session.getTransaction().begin();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            FacesUtil.addInfoMessage("Salvo com sucesso!!");
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            FacesUtil.addInfoMessage("Erro ao salvar!!!" + e);
            return false;
        }
    }

    @Override
    public boolean update(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {

            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
            FacesUtil.addInfoMessage("Atualizado com sucesso!!");
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            FacesUtil.addInfoMessage("Erro ao atualizar!!!" + e);
            return false;
        }
    }

    @Override
    public boolean remove(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {

            session.getTransaction().begin();
            session.delete(entity);
            session.getTransaction().commit();
            FacesUtil.addInfoMessage("Removido com sucesso!!");
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            FacesUtil.addInfoMessage("Erro ao remover!!!" + e);
            return false;
        }
    }

    @Override
    public boolean merge(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {

            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
            FacesUtil.addInfoMessage("Sincronizado com sucesso!!");
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            FacesUtil.addInfoMessage("Erro ao sincronizar!!!" + e);
            return false;
        }
    }

    @Override
    public T getEntity(Serializable id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction().begin();
            T entity = (T) session.get(classe, id);
            return entity;
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction().begin();
            T entity = (T) criteria.getExecutableCriteria(session).uniqueResult();
            return entity;
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> getEntities() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction().begin();

            Criteria criteria = session.createCriteria(classe);

            return criteria.list();
        } catch (Exception e) {

            return null;
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> getEntitiesByDetachetCriteria(DetachedCriteria criteria) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction().begin();
            List<T> lista = (List<T>) session.createCriteria(classe).list();
            return criteria.getExecutableCriteria(session).list();
        } catch (Exception e) {
            return null;
        }finally{
            session.getTransaction().commit();
        }

    }

    @Override
    public List<T> getEntitiesByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public void rollback(Session session){
        session.getTransaction().rollback();
    }
    
    @Override
    public void begin(Session session){
        session.getTransaction().begin();
    }
    
    @Override
    public Session getSession(){
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public void commit(Session session) {
        session.getTransaction().commit();
    }

    

}

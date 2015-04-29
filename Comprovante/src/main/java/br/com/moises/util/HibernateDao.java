/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.util;

import br.com.moises.interfaces.InterfaceDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author Moises
 */
public class HibernateDao<T> implements InterfaceDao<T>, Serializable {

    private static final Long serialVersionUID = 1L;

    private Class<T> classe;
    private Session session;

    public HibernateDao(Class<T> classe) {
        super();
        this.classe = classe;
        // this.session = session;
    }

    @Override
    public boolean save(T entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.save(entity);
            session.getTransaction().commit();
            FacesUtil.addInfoMessage("Salvo com sucesso!");
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            FacesUtil.addErrorMessage("Não foi possível salvar! " + e);
            return false;
        }

    }

    @Override
    public boolean saveOrUpdate(T entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.saveOrUpdate(entity);
            terminarTransacao();
            if (!entity.toString().isEmpty()) {
                FacesUtil.addInfoMessage("Salvo com sucesso!");
            } else {
                FacesUtil.addInfoMessage("Atualizado com sucesso!");
            }

            return true;
        } catch (Exception e) {
            cancelarTransacao();
            FacesUtil.addErrorMessage("Não foi possível salvar! " + e);
            return false;
        }

    }

    @Override
    public boolean update(T entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.update(entity);
            FacesUtil.addInfoMessage("Atualizado com sucesso!");
            return true;
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Erro ao atualizar! \n" + e);
            return false;
        }
    }

    @Override
    public boolean remove(T entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.delete(entity);
            FacesUtil.addInfoMessage("Excluído com sucesso!");
            return true;
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Registro não pode ser excluído! " + e);
            return false;
        }
    }

    @Override
    public boolean merge(T entity) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.merge(entity);
            FacesUtil.addInfoMessage("Sincronizado com sucesso!");
            return true;
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Não foi possível sincronizar o objeto! " + e);
            return false;
        }
    }

    @Override
    public T getEntity(Serializable id) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            T entity = (T) session.get(classe, id);
            return entity;
        } catch (Exception e) {
            return null;
        }finally{
            terminarTransacao();
        }

    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
        try{
        T entity = (T) criteria.getExecutableCriteria(session).uniqueResult();
        return entity;
        }catch(Exception e){
            return null;
        }finally{
            terminarTransacao();
        }
    }

    @Override
    public List<T> getEntities() {
        List<T> lista = (List<T>) session.createCriteria(classe).list();
        return lista;
    }

    @Override
    public List<T> getEntitiesByDetachetCriteria(DetachedCriteria criteria) {
        System.err.println("dETAC");
        return criteria.getExecutableCriteria(session).list();
    }

    private void iniciarTransacao() {
        session.getTransaction().begin();
    }

    private void terminarTransacao() {
        session.getTransaction().commit();
    }

    private void cancelarTransacao() {
        session.getTransaction().rollback();
    }

    @Override
    public List<T> getEntitiesByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void commit(Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rollback(Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void begin(Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Session getSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

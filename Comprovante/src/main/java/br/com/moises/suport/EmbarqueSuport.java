/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.suport;

import br.com.moises.dao.Dao;
import br.com.moises.enums.StatusEmbarque;
import br.com.moises.interfaces.InterfaceCrud;
import br.com.moises.interfaces.InterfaceDao;
import br.com.moises.model.Embarque;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MOISES
 */
public class EmbarqueSuport implements Serializable, InterfaceCrud {

    private InterfaceDao<Embarque> Dao() {
        InterfaceDao<Embarque> dao = new Dao<>(Embarque.class);
        return dao;
    }

    @Override
    public boolean save(Object o) {
        return Dao().save((Embarque) o);
    }

    @Override
    public boolean saveOrUpdate(Object o) {
        return Dao().saveOrUpdate((Embarque) o);
    }

    @Override
    public boolean delete(Object o) {
        return Dao().remove((Embarque) o);
    }

    @Override
    public boolean merge(Object o) {
        return Dao().merge((Embarque) o);
    }

    @Override
    public List list() {
        return Dao().getEntities();
    }

    public Embarque getEmbarqueById(Long id) {
        return Dao().getEntity(id);
    }

    public List<Embarque> embarquePorNumero(Long valor) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Embarque.class)
                .add(Restrictions.ilike("id", valor));

        return Dao().getEntitiesByDetachetCriteria(criteria);
    }

//    public List<Embarque> getPessoasByPlaca(String valor){
//        System.out.print(valor+" ->suport");
//        DetachedCriteria criteria = DetachedCriteria.forClass(Embarque.class)
//                .add(Restrictions.or(Restrictions.ilike("nome", valor, MatchMode.START), Restrictions.ilike("placa", valor, MatchMode.START)));
//        return Dao().getEntitiesByDetachetCriteria(criteria);
//    }
    public List<Embarque> getEmbarquesAbertoByQuery() {
        Session s = Dao().getSession();
        Dao().begin(s);
        try {
            Query query = s.createQuery("SELECT E FROM Embarque E WHERE E.status =:STATUS");
            query.setParameter("status", StatusEmbarque.VAZIO);
            query.setParameter("status", StatusEmbarque.CARREGANDO);
            List<Embarque> embarques = query.list();
            embarques.size();
            return embarques;
        } catch (Exception e) {
            return null;
        }finally{
            Dao().commit(s);
            
        }

        
    }

    public List<Embarque> getEmbarquesEmAberto() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Embarque.class)
                .add(Restrictions.or(
                                Restrictions.eq("status", StatusEmbarque.CARREGANDO),
                                Restrictions.eq("status", StatusEmbarque.VAZIO)
                        ));
        return Dao().getEntitiesByDetachetCriteria(criteria);
    }
}

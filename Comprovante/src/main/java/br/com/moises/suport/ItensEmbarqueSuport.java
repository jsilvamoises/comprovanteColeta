/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.suport;

import br.com.moises.dao.Dao;
import br.com.moises.interfaces.InterfaceCrud;
import br.com.moises.interfaces.InterfaceDao;
import br.com.moises.model.Embarque;
import br.com.moises.model.ItensEmbarque;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MOISES
 */
public class ItensEmbarqueSuport implements Serializable, InterfaceCrud{
    
    private InterfaceDao<ItensEmbarque> Dao() {
        InterfaceDao<ItensEmbarque> dao = new Dao<>(ItensEmbarque.class);
        return dao;
    }

    @Override
    public boolean save(Object o) {
        return Dao().save((ItensEmbarque) o);
    }

    @Override
    public boolean saveOrUpdate(Object o) {
        return Dao().saveOrUpdate((ItensEmbarque) o);
    }

    @Override
    public boolean delete(Object o) {
        return Dao().remove((ItensEmbarque) o);
    }

    @Override
    public boolean merge(Object o) {
       return Dao().merge((ItensEmbarque) o);
    }

    @Override
    public List list() {
        return Dao().getEntities();
    }
    
    public ItensEmbarque getItensEmbarqueById(Long id){
        return Dao().getEntity(id);
    }
    
    public List<ItensEmbarque> itensEmbarquePorEmbarque(Embarque embarque){
        DetachedCriteria criteria = DetachedCriteria.forClass(ItensEmbarque.class)
                .add(Restrictions.eq("embarque", embarque));
                
        return Dao().getEntitiesByDetachetCriteria(criteria);
    }
    
//    public List<ItensEmbarque> getPessoasByPlaca(String valor){
//        System.out.print(valor+" ->suport");
//        DetachedCriteria criteria = DetachedCriteria.forClass(ItensEmbarque.class)
//                .add(Restrictions.or(Restrictions.ilike("nome", valor, MatchMode.START), Restrictions.ilike("placa", valor, MatchMode.START)));
//        return Dao().getEntitiesByDetachetCriteria(criteria);
//    }
}

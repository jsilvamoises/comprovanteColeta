/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.suport;

import br.com.moises.dao.Dao;
import br.com.moises.interfaces.InterfaceCrud;
import br.com.moises.interfaces.InterfaceDao;
import br.com.moises.model.Transportadora;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MOISES
 */
public class TransportadoraSuport implements Serializable, InterfaceCrud{
    
    private InterfaceDao<Transportadora> Dao() {
        InterfaceDao<Transportadora> dao = new Dao<>(Transportadora.class);
        return dao;
    }

    @Override
    public boolean save(Object o) {
        return Dao().save((Transportadora) o);
    }

    @Override
    public boolean saveOrUpdate(Object o) {
        return Dao().saveOrUpdate((Transportadora) o);
    }

    @Override
    public boolean delete(Object o) {
        return Dao().remove((Transportadora) o);
    }

    @Override
    public boolean merge(Object o) {
       return Dao().merge((Transportadora) o);
    }

    @Override
    public List list() {
        return Dao().getEntities();
    }
    
    public Transportadora getObjetoById(Long id){
        System.err.println("pb");
        return Dao().getEntity(id);
    }
    
    public List<Transportadora> transportadoraPorNome(String valor){
        System.err.println("pA");
        DetachedCriteria criteria = DetachedCriteria.forClass(Transportadora.class)
                .add(Restrictions.ilike("nome", valor, MatchMode.START));
        return Dao().getEntitiesByDetachetCriteria(criteria);
    }
    
//    public List<Transportadora> getPessoasByPlaca(String valor){
//        System.out.print(valor+" ->suport");
//        DetachedCriteria criteria = DetachedCriteria.forClass(Transportadora.class)
//                .add(Restrictions.or(Restrictions.ilike("nome", valor, MatchMode.START), Restrictions.ilike("placa", valor, MatchMode.START)));
//        return Dao().getEntitiesByDetachetCriteria(criteria);
//    }
}

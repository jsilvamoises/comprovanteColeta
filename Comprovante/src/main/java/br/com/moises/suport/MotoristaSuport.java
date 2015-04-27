/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.suport;

import br.com.moises.dao.Dao;
import br.com.moises.interfaces.InterfaceCrud;
import br.com.moises.interfaces.InterfaceDao;
import br.com.moises.model.Motorista;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MOISES
 */
public class MotoristaSuport implements Serializable, InterfaceCrud{
    
    private InterfaceDao<Motorista> Dao() {
        InterfaceDao<Motorista> dao = new Dao<>(Motorista.class);
        return dao;
    }

    @Override
    public boolean save(Object o) {
        return Dao().save((Motorista) o);
    }

    @Override
    public boolean saveOrUpdate(Object o) {
        return Dao().saveOrUpdate((Motorista) o);
    }

    @Override
    public boolean delete(Object o) {
        return Dao().remove((Motorista) o);
    }

    @Override
    public boolean merge(Object o) {
       return Dao().merge((Motorista) o);
    }

    @Override
    public List list() {
        return Dao().getEntities();
    }
    
    public Motorista getMotoristaById(Long id){
        return Dao().getEntity(id);
    }
    
    public List<Motorista> veiculosPorPlaca(String valor){
        DetachedCriteria criteria = DetachedCriteria.forClass(Motorista.class)
                .add(Restrictions.ilike("nome", valor, MatchMode.START));
        return Dao().getEntitiesByDetachetCriteria(criteria);
    }
    
    public List<Motorista> getPessoasByPlaca(String valor){
        System.out.print(valor+" ->suport");
        DetachedCriteria criteria = DetachedCriteria.forClass(Motorista.class)
                .add(Restrictions.or(Restrictions.ilike("nome", valor, MatchMode.START), Restrictions.ilike("placa", valor, MatchMode.START)));
        return Dao().getEntitiesByDetachetCriteria(criteria);
    }
}

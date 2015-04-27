/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.suport;

import br.com.moises.dao.Dao;
import br.com.moises.interfaces.InterfaceCrud;
import br.com.moises.interfaces.InterfaceDao;
import br.com.moises.model.Cliente;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MOISES
 */
public class ClienteSuport implements Serializable, InterfaceCrud{
    
    private InterfaceDao<Cliente> Dao() {
        InterfaceDao<Cliente> dao = new Dao<>(Cliente.class);
        return dao;
    }

    @Override
    public boolean save(Object o) {
        return Dao().save((Cliente) o);
    }

    @Override
    public boolean saveOrUpdate(Object o) {
        return Dao().saveOrUpdate((Cliente) o);
    }

    @Override
    public boolean delete(Object o) {
        return Dao().remove((Cliente) o);
    }

    @Override
    public boolean merge(Object o) {
       return Dao().merge((Cliente) o);
    }

    @Override
    public List list() {
        return Dao().getEntities();
    }
    
    public Cliente getClienteById(Long id){
        return Dao().getEntity(id);
    }
    
    public List<Cliente> clientePorNome(String valor){
        DetachedCriteria criteria = DetachedCriteria.forClass(Cliente.class)
                .add(Restrictions.ilike("nome", valor, MatchMode.START));
        return Dao().getEntitiesByDetachetCriteria(criteria);
    }
    
//    public List<Cliente> getPessoasByPlaca(String valor){
//        System.out.print(valor+" ->suport");
//        DetachedCriteria criteria = DetachedCriteria.forClass(Cliente.class)
//                .add(Restrictions.or(Restrictions.ilike("nome", valor, MatchMode.START), Restrictions.ilike("placa", valor, MatchMode.START)));
//        return Dao().getEntitiesByDetachetCriteria(criteria);
//    }
}

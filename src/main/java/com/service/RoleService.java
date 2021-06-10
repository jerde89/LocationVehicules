package com.service;

import com.connection.EMF;
import com.entity.Role;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class RoleService {

    EntityManager em;
    EntityTransaction transaction;

    public RoleService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Role trouver(int id ) throws ServiceException {
        try {
            return em.find( Role.class, id );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public void creer( Role role ) throws ServiceException {
        try {
            transaction.begin();
            em.persist( role );
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Role> lister() throws ServiceException {
        try {
            TypedQuery<Role> query = em.createNamedQuery( "Role.lister", Role.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }
}


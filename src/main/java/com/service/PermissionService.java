package com.service;

import com.connection.EMF;
import com.entity.Permission;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class PermissionService {

    EntityManager em;
    EntityTransaction transaction;

    public PermissionService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Permission trouver(int id ) throws ServiceException {
        try {
            return em.find( Permission.class, id );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public void creer( Permission permission ) throws ServiceException {
        try {
            em.persist( permission );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public List<Permission> lister() throws ServiceException {
        try {
            TypedQuery<Permission> query = em.createQuery( "SELECT c FROM Permission c ORDER BY c.idPermission", Permission.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }
}


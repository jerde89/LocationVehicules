package com.service;

import com.connection.EMF;
import com.entity.Telephone;
import com.entity.Utilisateur;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class TelephoneService {

    EntityManager em;
    EntityTransaction transaction;

    public TelephoneService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Telephone trouver(int id ) throws ServiceException {
        try {
            return em.find( Telephone.class, id );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public void creer( Telephone telephone ) throws ServiceException {
        try {
            transaction.begin();
            em.persist( telephone );
            transaction.commit();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Telephone> lister(Utilisateur utilisateur) throws ServiceException {
        try {
            TypedQuery<Telephone> query = em.createNamedQuery( "Telephone.lister", Telephone.class );
            query.setParameter("utilisateur", utilisateur);
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }
}


package com.service;

import com.connection.EMF;
import com.entity.Ville;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class VilleService {

    EntityManager em;
    EntityTransaction transaction;

    public VilleService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Ville trouver(int id ) throws ServiceException {
        try {
            return em.find( Ville.class, id );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public Ville trouverParVille(String nomVille) {
        TypedQuery<Ville> query = em.createNamedQuery("Ville.trouverParNomVille", Ville.class);
        query.setParameter("nomVille", nomVille);

        if(query.getSingleResult() != null) {
            return query.getSingleResult();
        } else {
            return null;
        }
    }

    public void creer( Ville ville ) throws ServiceException {
        try {
            transaction.begin();
            em.persist( ville );
            transaction.commit();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Ville> lister() throws ServiceException {
        try {
            TypedQuery<Ville> query = em.createNamedQuery( "Ville.lister", Ville.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }
}


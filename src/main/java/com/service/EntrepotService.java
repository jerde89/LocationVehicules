package com.service;

import com.entity.Entrepot;
import com.exception.ServiceException;
import com.connection.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @authors Wets Jeoffroy // Vanconingsloo Kevin
 */

public class EntrepotService {

    EntityManager em;
    EntityTransaction transaction;

    public EntrepotService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public void update(Entrepot entrepot) {
        transaction.begin();
        em.merge(entrepot);
        transaction.commit();
    }

    public Entrepot trouver( int id ) throws ServiceException {
        try {
            return em.find( Entrepot.class, id );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public void creer( Entrepot entrepot ) throws ServiceException {
        try {
            em.persist( entrepot );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public List<Entrepot> lister() throws ServiceException {
        try {
            TypedQuery<Entrepot> query = em.createNamedQuery("Entrepot.lister", Entrepot.class);
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public void suppressionLogique (Entrepot entrepot){
        try {
            transaction.begin();
            entrepot.setActifEntrepot(false);
            em.persist(entrepot);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
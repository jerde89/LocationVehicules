package com.service;

import com.connection.EMF;
import com.entity.Adresse;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class AdresseService {

    EntityManager em;
    EntityTransaction transaction;

    public AdresseService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Adresse trouver(int id ) throws ServiceException {
        try {
            return em.find( Adresse.class, id );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public void creer( Adresse adresse ) throws ServiceException {
        try {
            em.persist( adresse );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public List<Adresse> lister() throws ServiceException {
        try {
            TypedQuery<Adresse> query = em.createNamedQuery( "Adresse.lister", Adresse.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }
}

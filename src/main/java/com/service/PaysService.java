package com.service;

import com.connection.EMF;
import com.entity.Pays;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class PaysService {

    EntityManager em;
    EntityTransaction transaction;

    public PaysService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Pays trouver(int id ) throws ServiceException {
        return em.find( Pays.class, id );
    }

    public void creer( Pays pays ) {
        try {
            transaction.begin();
            em.persist( pays );
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Pays> lister() throws ServiceException {
        try {
            TypedQuery<Pays> query = em.createQuery( "SELECT c FROM Pays c ORDER BY c.idPays", Pays.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public List<Pays> trouverParNom(String nom) throws ServiceException{
        try {
            TypedQuery<Pays> query = em.createQuery("SELECT u FROM Pays u WHERE u.nomPays = :nom ", Pays.class);
            query.setParameter("nom", nom);
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }
}

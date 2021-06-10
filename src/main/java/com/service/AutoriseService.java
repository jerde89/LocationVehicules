package com.service;

import com.connection.EMF;
import com.entity.Autorise;
import com.entity.Utilisateur;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class AutoriseService {

    EntityManager em;
    EntityTransaction transaction;

    public AutoriseService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Autorise trouver(int id ) throws ServiceException {
        try {
            return em.find( Autorise.class, id );
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }

    public void creer( Autorise autorise ) throws ServiceException {
        try {
            transaction.begin();
            em.persist( autorise );
            transaction.commit();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public Boolean hasPermission (int idRole, String permission){
        TypedQuery<Autorise> query = em.createNamedQuery("Autorise.hasPermission", Autorise.class);
        query.setParameter("idRole", idRole);
        query.setParameter("permission", permission);

        try {
            Autorise a = query.getSingleResult();
            return true;
        } catch(javax.persistence.NoResultException e) {
            return false;
        }
    }

    public List<Autorise> lister(int idRole) throws ServiceException {
        try {
            TypedQuery<Autorise> query = em.createQuery( "SELECT c FROM Autorise c where c.rolesByIdRole.idRole = :idRole ORDER BY  c.idAutorise", Autorise.class );
            query.setParameter("idRole", idRole);
            return query.getResultList();
        } catch ( Exception e ) {
            throw new ServiceException( e );
        }
    }
}

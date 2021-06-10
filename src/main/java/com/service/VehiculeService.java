package com.service;

import com.entity.Utilisateur;
import com.entity.Vehicule;
import com.exception.ServiceException;
import com.connection.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @authors Wets Jeoffroy // Vanconingsloo Kevin
 */

public class VehiculeService {

    EntityManager em;
    EntityTransaction transaction;

    public VehiculeService(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public void update(Vehicule vehicule) {
        transaction.begin();
        em.merge(vehicule);
        transaction.commit();
    }

    public Vehicule trouver(int id) throws ServiceException {
        try {
            return em.find(Vehicule.class, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void creer(Vehicule vehicule) throws ServiceException {
        try {
            em.persist(vehicule);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Vehicule> lister() throws ServiceException {
        try {
            TypedQuery<Vehicule> query = em.createNamedQuery("Vehicule.lister", Vehicule.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void suppressionLogique (Vehicule vehicule){
        try {
            transaction.begin();
            vehicule.setActifVehicule(false);
            em.persist(vehicule);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}

package com.controller;

import com.connection.EMF;
import com.entity.Entrepot;
import com.exception.ServiceException;
import com.service.EntrepotService;
import com.service.VehiculeService;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */
@WebServlet(name = "GestionEntrepotServlet")
public class GestionEntrepotServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        List<Entrepot> entrepotList = null;
        EntrepotService entrepotService = new EntrepotService(em);

        try {

            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("entrepotList", entrepotList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionEntrepot.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*String idReq = request.getParameter("idModif");
        int id = Integer.parseInt(idReq);

        List<Entrepot> entrepotList = null;
        EntrepotService entrepotService = new EntrepotService();
        Entrepot entrepot = null;

        try {

            entrepot = entrepotService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        entrepot.getModelesByIdModele().getMarquesByIdMarque().setNomMarque(request.getParameter("marque"));
        entrepot.getModelesByIdModele().setNomModele(request.getParameter("modele"));
        entrepot.setPuissance(Integer.parseInt(request.getParameter("puissance")));
        entrepot.setCylindree(Integer.parseInt(request.getParameter("cylindree")));
        entrepot.setImmatriculation(request.getParameter("immatriculation"));
        entrepot.setDateAchat(Date.valueOf(request.getParameter("dateAchat")));
        entrepot.setNumChassis(request.getParameter("numChassis"));
        entrepot.setPrixJournalier(Float.parseFloat(request.getParameter("prixJournalier")));
        entrepot.setActifVehicule(Boolean.parseBoolean(request.getParameter("status")));

        try {

            entrepotService.update(entrepot);
            entrepotList = entrepotService.lister();
        } catch (Exception e) {

            //message erreur
        } finally {

            em.close();
        }

        request.setAttribute("entrepotList", entrepotList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionEntrepot.jsp" ).forward( request, response );*/
    }
}

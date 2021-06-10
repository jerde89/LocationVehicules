package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @authors Wets Jeoffroy / Vanconingsloo Kevin
 */
@Entity
@Table(name = "modeles", schema = "location_vehicules")
public class Modele {
    private int idModele;
    private String nomModele;
    private boolean actifOption;
    private Marque marquesByIdMarque;
    private List<Vehicule> vehiculesByIdModele;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_modele", nullable = false)
    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    @Basic
    @Column(name = "Nom_modele", nullable = false, length = 50)
    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    @Basic
    @Column(name = "Actif_option", nullable = false)
    public boolean isActifOption() {
        return actifOption;
    }

    public void setActifOption(boolean actifOption) {
        this.actifOption = actifOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modele modele = (Modele) o;
        return idModele == modele.idModele && actifOption == modele.actifOption && Objects.equals(nomModele, modele.nomModele);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModele, nomModele, actifOption);
    }

    @ManyToOne
    @JoinColumn(name = "Id_marque", referencedColumnName = "Id_marque", nullable = false)
    public Marque getMarquesByIdMarque() {
        return marquesByIdMarque;
    }

    public void setMarquesByIdMarque(Marque marquesByIdMarque) {
        this.marquesByIdMarque = marquesByIdMarque;
    }

    @OneToMany(mappedBy = "modelesByIdModele")
    public List<Vehicule> getVehiculesByIdModele() {
        return vehiculesByIdModele;
    }

    public void setVehiculesByIdModele(List<Vehicule> vehiculesByIdModele) {
        this.vehiculesByIdModele = vehiculesByIdModele;
    }
}

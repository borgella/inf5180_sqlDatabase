/*
 */
package TABLES;

import java.sql.Date;

/**
 *
 * @author Borgella
 */
public class Catalogue {

    private final  String nomTable = "Catalogue";
    private int noReferenceCatalogue;// number(10) NOT NULL,

    private double prixVente;// precision DEFAULT 0 NOT NULL,
    private Date dateDisponibilite;//    date,
    private int seuilMinimum;//         number(10),

    private int prioriteFournisseur;

    public Catalogue() {

    }

    public Catalogue(int noReferenceCatalogue, double prixVente, Date dateDisponibilite, int seuilMinimum, int prioriteFournisseur) {
        this.noReferenceCatalogue = noReferenceCatalogue;

        this.prixVente = prixVente;
        this.dateDisponibilite = dateDisponibilite;
        this.seuilMinimum = seuilMinimum;

        this.prioriteFournisseur = prioriteFournisseur;
    }

}

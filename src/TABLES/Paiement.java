
package TABLES;

import java.sql.Date;

/**
 *
 * @author Borgella
 */
public class Paiement {
    protected String nomTable;
    protected Date datePaiement;//  DATE NOT NULL,
    protected double montant;//       DOUBLE PRECISION   NOT NULL,
    protected int noLivraison;//   INTEGER  NOT NULL,
    
    public Paiement(String nomTable, Date datePaiement, double montant, int noLivraison) {
        this.nomTable = nomTable;
        this.datePaiement = datePaiement;
        this.montant = montant;
        this.noLivraison = noLivraison;
    }
    

}

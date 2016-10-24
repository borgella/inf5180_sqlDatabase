
package TABLES;

import java.sql.Date;

/**
 *
 * @author Borgella
 */
public class PaiementParCarteDeCredit extends Paiement {

    private String noCarte;//     
    private String typeCarte;//   

    public PaiementParCarteDeCredit(String nomTable, Date datePaiement, double montant, int noLivraison,
            String noCarte, String typeCarte) {
        
        super(nomTable, datePaiement, montant, noLivraison);
        super.nomTable = "PaiementParCarteDeCredit";
        this.noCarte = noCarte;
        this.typeCarte = typeCarte;
    }

}

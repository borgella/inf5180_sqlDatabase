
package TABLES;

import java.sql.Date;

/**
 *
 * @author Borgella
 */
public class PaiementParCheque extends Paiement {
    
    
    private String noCheque;//   varchar2(255)  NOT NULL,
    private String institution;// varchar2(255) NOT NUll,
   
    public PaiementParCheque(String noCheque, String institution, 
            String nomTable, Date datePaiement, double montant, int noLivraison) {
        super(nomTable, datePaiement, montant, noLivraison);
         super.nomTable = "PaiementParCheque";
        this.noCheque = noCheque;
        this.institution = institution;
    }


}

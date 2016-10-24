
package TABLES;

import java.sql.Date;

/**
 *
 * @author Borgella
 */
public class Facture {

    private final  String nomTable = "Facture";
    private int noFacture;//       INTEGER  NOT NULL,
    private String typeProduit;//     varchar2(255) NOT NULL,
    private Date dateLivraison;//   DATE NOT NULL,
    private double montantTotale;//   DOUBLE PRECISION NOT NULL,
    private int estPaye;//         INTEGER  NOT NULL,
    private int noPaiement;//      INTEGER NOT NULL,
    private double balance;//         DOUBLE PRECISION NOT NULL,
    private int codeClient;//      INTEGER  NOT NULL,
    private int noLivraison;//     INTEGER  NOT NULL,

}

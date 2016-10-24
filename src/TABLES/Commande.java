
package TABLES;

import java.sql.Date;

/**
 *
 * @author Borgella
 */
public class Commande {

    private final  String nomTable = "Commande";
    private int noCommande;//   INTEGER  NOT NULL,
    private int noCatalogue;//  INTEGER  NOT NULL,
    private int codeClient;//   INTEGER  NOT NULL,
    private Date dateCommande;// DATE NOT NULL,

}

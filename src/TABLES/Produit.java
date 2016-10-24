
package TABLES;

/**
 *
 * @author Borgella
 */
public class Produit {

    private final  String nomTable = "Produit";
    private int noProduit;//	   INTEGER NOT NULL,
    private String typeProduit;//	   varchar2(255) NOT NULL,
    private String description;//	   varchar2(255) NOT NULL,
    private double prixEnCours;//	   DOUBLE PRECISION DEFAULT 0 NOT NULL,
    private int prioriteFournisseur;// INTEGER NOT NULL,
    private int noReferenceCatalogue;// INTEGER NOT NULL,
    private int quantite;//         INTEGER NOT NULL,


}


package DAO;

import GUI.Insertion_gui;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.Collection;

import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;

import java.util.Date;

/**
 *
 * @author Borgella
 */
public class SQL_dao implements DAO_bd {

    private Connection conn_database;
    private Insertion_gui preparerLivraison_gui;
    private Insertion_gui inscrirePaiement_gui;

    private Vector<Object[]> vectorValeurColonnes;
    private HashMap hashValeurColonnes = new HashMap();

    private String infosPersoClient;
    private final String[] infosClient = {"nom", "prenom", "noTelephone", "numeroMaison", "nomRue", "nomVille", "pays", "codePostal"};
    private final String[] descZonesSaisies_preparerLivraison = {"codeUtilisateur", "noCommande",
        "noProduit", "description", "quantite", "codeZebre"};
    private final String[] descZonesSaisies_inscrirelivraison = {"noCommande", "codeUtilisateur", "noLivraison",
        "noProduit", "description", "quantite", "prixProduit", "Totale achat", "datePaiement", " Montant", "nbrVersements"};

    private HashMap hashSaisiesRetour;
    Object codeClient;
    Object noCommande;

    private Statement creeEtatSQL;
    private ResultSet selectTable;
////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean connectionBD() {
        try {
            /// start tp2_INF5180;
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn_database = DriverManager.getConnection("jdbc:oracle:thin:@zeta2.labunix.uqam.ca:1521:baclab", "fa491423", "WcsbWtCD");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Ipossible de se connecter a la base de donnée",
                    "ERREUR CONNECTION",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean preparerLivraison(Object codeClient) {

        this.codeClient = codeClient;
        if (!validerCodeUtilisateurClient_preparerlivraison()) {
            return false;
        }

        preparerLivraison_gui = new Insertion_gui(descZonesSaisies_preparerLivraison, infosPersoClient, "PREPARER LIVRAISON", " ");
        if (createState_preparerlivraison() && preparerLivraison_gui.ajouterDansJTable(hashValeurColonnes)) {// 
            preparerLivraison_gui.affiche_gui();
            if (preparerLivraison_gui.isEstOK()) {
                hashSaisiesRetour = preparerLivraison_gui.getHashSaisiesRetour();
                prepaState_preparerlivraison();
            }

        } else {
            return false;
        }
        return true;
    }

////////////////////////////////////////////////////////////////////////////////////////
    public boolean validerCodeUtilisateurClient_preparerlivraison() {
        try {

            creeEtatSQL = conn_database.createStatement();
            selectTable = creeEtatSQL.executeQuery("SELECT * FROM Utilisateur WHERE codeUtilisateur = " + //WHERE codeUtilisateur = 500
                    +Integer.parseInt(codeClient.toString()) + "");

            /// Jlabel use : <html>Line1 <br> Line2 <br> Line3</html>
            infosPersoClient = "<html>   Numero client: " + codeClient.toString() + "<br>";
            while (selectTable.next()) {
                infosPersoClient += infosClient[0] + ": " + selectTable.getString(infosClient[0]) + "<br>";
                infosPersoClient += infosClient[1] + ": " + selectTable.getString(infosClient[1]) + "<br>";
                infosPersoClient += infosClient[2] + ": " + selectTable.getString(infosClient[2]) + "<br>";
            }
            selectTable.close();

            selectTable = creeEtatSQL.executeQuery("SELECT * FROM AdresseUtilisateur WHERE codeUtilisateur = " + //WHERE codeUtilisateur = 500
                    +Integer.parseInt(codeClient.toString()) + "");

            while (selectTable.next()) {
                infosPersoClient += infosClient[3] + ": " + selectTable.getString(infosClient[3]) + "<br>";
                infosPersoClient += infosClient[4] + ": " + selectTable.getString(infosClient[4]) + "<br>";
                infosPersoClient += infosClient[5] + ": " + selectTable.getString(infosClient[5]) + "<br>";
                infosPersoClient += infosClient[6] + ": " + selectTable.getString(infosClient[6]) + "<br>";
                infosPersoClient += infosClient[7] + ": " + selectTable.getString(infosClient[7]) + "</html>";
            }
            selectTable.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Il y eu une erreur lors de la validation du numero de client",
                    "ERROR_MESSAGE : Validation du numero de client",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////

    public boolean createState_preparerlivraison() {
        try {
            creeEtatSQL = conn_database.createStatement();
            /// {"codeUtilisateur", "noCommande", "noProduit", "description", "quantite"};
            selectTable = creeEtatSQL.executeQuery("SELECT * FROM Client WHERE codeUtilisateur = " + //WHERE codeUtilisateur = 500
                    +Integer.parseInt(codeClient.toString()) + "");

            /*codeUtilisateur*/
            selectTable = creeEtatSQL.executeQuery("SELECT noCommande, noProduit, quantite FROM "
                    + "Commande NATURAL JOIN LigneDeCommande  WHERE "
                    + "Commande.codeUtilisateur = " + Integer.parseInt(codeClient.toString()) + "");

            /// descZonesSaisies_preparerLivraison= {"codeUtilisateur", "noCommande", "noProduit", "description", "quantite"};
            System.out.println("createState_preparerlivraison before next");
            while (selectTable.next()) {
                System.out.println("createState_preparerlivraison  selectTable next");
                Object[] objliste = new Object[descZonesSaisies_preparerLivraison.length];
                /*codeUtilisateur*/
                objliste[0] = Integer.parseInt(codeClient.toString()) + "";


                /*noCommande*/
                objliste[1] = selectTable.getInt(descZonesSaisies_preparerLivraison[1]);
                System.out.println("selectTable.getInt(descZonesSaisies[1]); = " + selectTable.getInt(descZonesSaisies_preparerLivraison[1]));

                /*description*/
                objliste[3] = "  ";

                /*noProduit*/
                objliste[2] = selectTable.getInt(descZonesSaisies_preparerLivraison[2]);
                System.out.println("selectTable.getInt(descZonesSaisies[2]); = " + selectTable.getInt(descZonesSaisies_preparerLivraison[2]));

                /*quantite*/
                objliste[4] = selectTable.getInt(descZonesSaisies_preparerLivraison[4]);
                System.out.println("selectTable.getInt(descZonesSaisies[4]); = " + selectTable.getInt(descZonesSaisies_preparerLivraison[4]));

                hashValeurColonnes.put(objliste[2], objliste);
            }
            selectTable.close();
            if (hashValeurColonnes.size() > 0) {
                selectTable = creeEtatSQL.executeQuery("SELECT * FROM Produit ");
                while (selectTable.next()) {
                    /*  WHERE LigneDeCommande.noProduit = Produit.noProduit  */
                    Object[] objlisteMod = (Object[]) hashValeurColonnes.get(selectTable.getInt(descZonesSaisies_preparerLivraison[2]));

                    /*description*/
                    objlisteMod[3] = selectTable.getString(descZonesSaisies_preparerLivraison[3]);

                    /*codeZebre*/
                    objlisteMod[5] = selectTable.getString(descZonesSaisies_preparerLivraison[5]);

                }
                selectTable.close();

                System.out.println("________________________________________________________");
                Collection<Object[]> values = hashValeurColonnes.values();
                values.stream().map((objects) -> {
                    for (Object object : objects) {
                        System.out.println(" = " + object);
                    }
                    return objects;
                }).forEach((_item) -> {
                    System.out.println("_____________________");
                });

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Il y eu une erreur lors de la creation des lignes pour l'affichage",
                    "ERROR_MESSAGE : Creation des lignes pour l'affichage",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;

    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    private boolean prepaState_preparerlivraison() {

        try {

            creeEtatSQL = conn_database.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            int prochaineClePromaire = 0;
            selectTable = creeEtatSQL.executeQuery("SELECT * FROM Livraison ");
            while (selectTable.next()) {
                prochaineClePromaire++;
            }
            if (prochaineClePromaire < 1) {
                prochaineClePromaire = 1;
            }
            selectTable.close();

            conn_database.setAutoCommit(false);
            PreparedStatement addParentStmt = null;

            Date aujourdhui = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.format(aujourdhui);

            /// {"codeUtilisateur", "noCommande", "noProduit", "description", "quantite", "codeZebre"};
            Collection<Object[]> saisiesRetour = hashSaisiesRetour.values();

            int cleLivraison = prochaineClePromaire;

            for (int i = 0; i < saisiesRetour.size(); i++) {
                String insertLivraison = "INSERT INTO Livraison (noLivraison, dateLivraison) "
                        + "VALUES (" + cleLivraison + " ,"
                        + "To_date ('" + dateFormat.format(aujourdhui) + "', 'DD/MM/YYYY'))";

                addParentStmt = conn_database.prepareStatement(insertLivraison);
                addParentStmt.executeUpdate();
                addParentStmt.close();

                cleLivraison++;

            }

            int cleLigneDeCommande = prochaineClePromaire;

            for (Object[] objects : saisiesRetour) {
                String insertLigneDeCommande = "INSERT INTO LigneDeLivraison "
                        + "(noLivraison, noCommande, noProduit, quantiteLivree) "
                        + "VALUES (?, ?, ?, ?)";

                addParentStmt = conn_database.prepareStatement(insertLigneDeCommande);

                System.out.println("(int) objects[1] = " + Integer.parseInt((String) objects[1]));
                System.out.println("(int) objects[2] = " + Integer.parseInt((String) objects[2]));
                System.out.println("(int) objects[4] = " + Integer.parseInt((String) objects[4]));
                System.out.println("cleLigneDeCommande = " + cleLigneDeCommande);

                addParentStmt.setInt(1, cleLigneDeCommande);
                addParentStmt.setInt(2, Integer.parseInt((String) objects[1]));
                addParentStmt.setInt(3, Integer.parseInt((String) objects[2]));
                addParentStmt.setInt(4, Integer.parseInt((String) objects[4]));

                addParentStmt.executeUpdate();
                addParentStmt.close();

                cleLigneDeCommande++;
            }
            conn_database.commit();

        } catch (SQLException sqle) {
            try {
                conn_database.rollback();
            } catch (SQLException e) {
            }
            JOptionPane.showMessageDialog(null,
                    "Il y eu une erreur lors de la modification des tables de la base de donnees",
                    "ERROR_MESSAGE : Modification des tables de la base de donnees",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                conn_database.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Il y eu une erreur lors de la fermeture de la connection a la base de donnees",
                        "ERROR_MESSAGE : Fermeture de la connection a la base de donnees",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean inscrirePaiement(Object noCommande) {

        this.noCommande = noCommande;
        if (!validerNoCommande_inscrirePaiement()) {
            return false;
        }

        Object[] possibleValues = {"Cheque", "Carte credit"};
        Object choixTraitement = JOptionPane.showInputDialog(null,
                "Choississez quel type de paiement", "Saisie",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[1]);
//

        inscrirePaiement_gui = new Insertion_gui(descZonesSaisies_inscrirelivraison,
                infosPersoClient, "INSCRIRE PAIEMENT",choixTraitement.toString() );
        inscrirePaiement_gui.ajouterDansJTable(hashValeurColonnes);

        if (createState_inscrirePaiement() && inscrirePaiement_gui.ajouterDansJTable(hashValeurColonnes)) {// 
            inscrirePaiement_gui.affiche_gui();

            if (inscrirePaiement_gui.isEstOK()) {
                hashSaisiesRetour = inscrirePaiement_gui.getHashSaisiesRetour();
                return prepaState_inscrirePaiement();
            }
            return false;
        }
        return false;

    }
//////////////////////////////////////////////////////////////////////////////////////// 

    boolean validerNoCommande_inscrirePaiement() {
        try {

            creeEtatSQL = conn_database.createStatement();

            selectTable = creeEtatSQL.executeQuery("SELECT codeUtilisateur, nom, prenom, noTelephone, "
                    + "motPasse FROM Utilisateur NATURAL JOIN Commande WHERE noCommande = "
                    + Integer.parseInt(noCommande.toString()) + "");

            /// Jlabel use : <html>Line1 <br> Line2 <br> Line3</html>
            infosPersoClient = "<html>   Numero de commande: " + noCommande.toString() + "<br>";
            while (selectTable.next()) {
                infosPersoClient += infosClient[0] + ": " + selectTable.getString(infosClient[0]) + "<br>";
                infosPersoClient += infosClient[1] + ": " + selectTable.getString(infosClient[1]) + "<br>";
                infosPersoClient += infosClient[2] + ": " + selectTable.getString(infosClient[2]) + "<br>";
            }
            selectTable.close();
////
            System.out.println("validerNoCommande_inscrirePaiement 01 ___" + infosPersoClient);
            selectTable = creeEtatSQL.executeQuery("SELECT AdresseUtilisateur.codeUtilisateur, "
                    + "numeroMaison, nomRue, nomVille, pays, codePostal FROM AdresseUtilisateur, Commande "
                    + "WHERE Commande.codeUtilisateur = AdresseUtilisateur.codeUtilisateur AND noCommande = "
                    + Integer.parseInt(noCommande.toString()) + "");

            while (selectTable.next()) {
                infosPersoClient += infosClient[3] + ": " + selectTable.getString(infosClient[3]) + "<br>";
                infosPersoClient += infosClient[4] + ": " + selectTable.getString(infosClient[4]) + "<br>";
                infosPersoClient += infosClient[5] + ": " + selectTable.getString(infosClient[5]) + "<br>";
                infosPersoClient += infosClient[6] + ": " + selectTable.getString(infosClient[6]) + "<br>";
                infosPersoClient += infosClient[7] + ": " + selectTable.getString(infosClient[7]) + "</html>";
            }
            selectTable.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Il y eu une erreur lors de la validation du numero de commande",
                    "ERROR_MESSAGE : Validation du numero de commande",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    public boolean createState_inscrirePaiement() {
        try {
            creeEtatSQL = conn_database.createStatement();
            selectTable = creeEtatSQL.executeQuery("SELECT noCommande, codeUtilisateur, noLivraison, noProduit,"
                    + "description, quantite, prixProduit FROM Commande NATURAL JOIN LigneDeLivraison "
                    + "NATURAL JOIN Produit WHERE noCommande = "
                    + Integer.parseInt(noCommande.toString()) + "");

            while (selectTable.next()) {
                System.out.println("createState_inscrirePaiement ____________________________");
                Object[] objliste = new Object[descZonesSaisies_inscrirelivraison.length];
                /*noCommande*/
                objliste[0] = Integer.parseInt(noCommande.toString()) + "";

                /*codeUtilisateur*/
                objliste[1] = selectTable.getInt(descZonesSaisies_inscrirelivraison[1]);
                System.out.println("selectTable.getInt(descZonesSaisies[1]); = " + selectTable.getInt(descZonesSaisies_inscrirelivraison[1]));

                /*noLivraison*/
                objliste[2] = selectTable.getInt(descZonesSaisies_inscrirelivraison[2]);
                System.out.println("selectTable.getInt(descZonesSaisies[2]); = " + selectTable.getInt(descZonesSaisies_inscrirelivraison[2]));

                /*noProduit*/
                objliste[3] = selectTable.getInt(descZonesSaisies_inscrirelivraison[3]);
                System.out.println("selectTable.getInt(descZonesSaisies[3]); = " + selectTable.getInt(descZonesSaisies_inscrirelivraison[3]));

                /*description*/
                objliste[4] = selectTable.getString(descZonesSaisies_inscrirelivraison[4]);
                System.out.println("selectTable.getInt(descZonesSaisies[4]); = " + selectTable.getString(descZonesSaisies_inscrirelivraison[4]));

                /*quantite*/
                objliste[5] = selectTable.getInt(descZonesSaisies_inscrirelivraison[5]);
                System.out.println("selectTable.getInt(descZonesSaisies[5]); = " + selectTable.getInt(descZonesSaisies_inscrirelivraison[5]));

                /*prixProduit*/
                objliste[6] = selectTable.getDouble(descZonesSaisies_inscrirelivraison[6]);
                System.out.println("selectTable.getInt(descZonesSaisies[6]); = " + selectTable.getDouble(descZonesSaisies_inscrirelivraison[6]));

                /*paiementRecu*/
                objliste[7] = selectTable.getInt(descZonesSaisies_inscrirelivraison[5]) * selectTable.getInt(descZonesSaisies_inscrirelivraison[6]);
                System.out.println("selectTable.getInt(descZonesSaisies[7]); = "
                        + (selectTable.getInt(descZonesSaisies_inscrirelivraison[5]) * selectTable.getInt(descZonesSaisies_inscrirelivraison[6])));

                Date aujourdhui = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                /*Date du paiement*/
                objliste[8] = dateFormat.format(aujourdhui);

                objliste[9] = 10000;
                objliste[10] = 1;

                hashValeurColonnes.put(objliste[0], objliste);
            }
            selectTable.close();

            System.out.println("________________________________________________________");
            Collection<Object[]> values = hashValeurColonnes.values();
            values.stream().map((objects) -> {
                for (Object object : objects) {
                    System.out.println(" = " + object);
                }
                return objects;
            }).forEach((_item) -> {
                System.out.println("_____________________");
            });

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Il y eu une erreur lors de la creation des lignes pour l'affichage",
                    "ERROR_MESSAGE : Creation des lignes pour l'affichage",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;

    }

    ////////////////////////////////////////////////////////////////////////////////////////
    private boolean prepaState_inscrirePaiement() {
        System.out.println("prepaState_inscrirePaiement DEBUT");

        Collection<Object[]> saisiesRetour = hashSaisiesRetour.values();
        for (Object[] objects : saisiesRetour) {
            double montantRecu = Double.parseDouble(objects[9].toString()) * Double.parseDouble(objects[10].toString());//* Integer.parseInt(objects[9].toString())
            double totale = Double.parseDouble(objects[7].toString());
            System.out.println("prepaState_inscrirePaiement");
            if (montantRecu > totale) {
                String infos = "REFUSE: montant est plus grand que le totale \n"
                        + "Totale achat : " + totale + ". Le montant recu : " + montantRecu + " pour le numero de produit: "
                        + objects[3].toString() + ", description: " + objects[4].toString();

                JOptionPane.showMessageDialog(null,
                        infos,
                        "ERROR_MESSAGE : Inscription paiement refuse",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }

}
////////////////////////////////////////////////////////////////////////////////////////

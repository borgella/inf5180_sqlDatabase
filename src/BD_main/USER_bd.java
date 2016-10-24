
package BD_main;

import DAO.SQL_dao;
import DAO.DAO_bd;
import javax.swing.JOptionPane;

/**
 *
 * @author Borgella
 */
public class USER_bd {

    private DAO_bd dao_bd;
////////////////////////////////////////////////////////////////////////////////////////

    public USER_bd() {
        this.dao_bd = new SQL_dao();
    }
////////////////////////////////////////////////////////////////////////////////////////

    public void choixDeExecution() {
        boolean estConnecter = dao_bd.connectionBD();
        if (estConnecter) {
            Object[] possibleValues = {"Préparer une livraison", "Inscrire un paiement"};
            Object choixTraitement = JOptionPane.showInputDialog(null,
                    "Choississez ce que vous voulez executer", "Saisie",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[1]);

            if (choixTraitement.equals(possibleValues[0])) {//"Préparer une livraison"
                preparerLivraison();

            } else if (choixTraitement.equals(possibleValues[1])) {//"Inscrire un paiement"
                inscrirePaiement();
            }
        }

    }
////////////////////////////////////////////////////////////////////////////////////////

    public void preparerLivraison() {

        Object codeClient = JOptionPane.showInputDialog(null,
                "Tapez le numero du client", "Numero du client",
                JOptionPane.INFORMATION_MESSAGE, null,
                null, "600");//numero client ? 500 ; 600 ; 601

        dao_bd.preparerLivraison(codeClient);

        JOptionPane.showMessageDialog(null,
                "USER :public class USER_bd  FCT preparerLivraison()",
                "USER : public class USER_bd FCT preparerLivraison()",
                JOptionPane.WARNING_MESSAGE);

    }
////////////////////////////////////////////////////////////////////////////////////////

    public void inscrirePaiement() {

        Object noCommande = JOptionPane.showInputDialog(null,
                "Tapez le numero de la commande", "Numero de la commande",
                JOptionPane.INFORMATION_MESSAGE, null,
                null, "1");

        dao_bd.inscrirePaiement(noCommande);

        JOptionPane.showMessageDialog(null,
                "USER :public class USER_bd FCT inscrirePaiement()",
                "USER : public class USER_bd FCT inscrirePaiement()",
                JOptionPane.WARNING_MESSAGE);
    }

}

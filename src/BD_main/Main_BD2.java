
package BD_main;

import javax.swing.*;

/**
 *
 * reference : http://www.labunix.uqam.ca/~godin_r/INF5180.html;
 * www.labunix.uqam.ca/~godin/ExemplesJava/AppletteJDBC.doc http://www.labunix.uqam.ca/~godin_r/SiteWebGodin2006/
 * http://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
 * http://www.mkyong.com/jdbc/connect-to-oracle-db-via-jdbc-driver-java/
 * http://stackoverflow.com/questions/1081234/java-date-insert-into-database
 *
 *
 * @author Borgella
 */
public class Main_BD2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        (new USER_bd()).choixDeExecution();
        
        JOptionPane.showMessageDialog(null,
                "FIN DU PROGRAMME : Skynet vous dit au revoir! ",
                "INFORMATION_MESSAGE",
                JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }

}

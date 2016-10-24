package GUI;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Borgella
 */
public class Insertion_gui implements ActionListener {

    private JDialog lafenetre = new JDialog();
    private HashMap listeJButton = new HashMap();
    private HashMap listeJTextField = new HashMap();
    private HashMap listeJLabel = new HashMap();
    private final String[] nomBoutons = {"ok", "cancel"};
    private JTable laTable;
    private String[] descZonesSaisies;
    private String infosPersoClient;
    private boolean estOK = false;
    private int nbrInfosClient;
    private String infosAdditionnel;
    private HashMap hashSaisiesRetour = new HashMap();

////////////////////////////////////////////////////////////////////////////////////////
    public Insertion_gui(String[] descZonesSaisies, String infosPersoClient, String nomFenetre, String infosAdditionnel) {
        this.descZonesSaisies = descZonesSaisies;
        this.infosPersoClient = infosPersoClient;
        this.infosAdditionnel = infosAdditionnel;
        initFenetre(nomFenetre);
    }

    public HashMap getHashSaisiesRetour() {
        return hashSaisiesRetour;
    }

    public void affiche_gui() {
        lafenetre.setVisible(true);
    }

    private void ferme_gui() {
        lafenetre.setVisible(false);
        lafenetre.dispose();
    }

    public boolean isEstOK() {
        return estOK;
    }

    public boolean ajouterDansJTable(HashMap hashValeurColonnes) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) laTable.getModel();
        Collection<Object[]> leslignes = hashValeurColonnes.values();

        leslignes.stream().forEach((Object[] objects) -> {
            defaultTableModel.addRow(objects);

        });

        laTable.setEnabled(true);
        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////

    private void initFenetre(String nomFenetre) {
        int dernierAjouteDansFenetre = 10;
        int proPos = 50;

        JLabel titre = new JLabel(nomFenetre);
        titre.setBounds(200, dernierAjouteDansFenetre, 400, 40);
        lafenetre.add(titre);

        JLabel infosPersoClientJLabel = new JLabel(infosPersoClient);
        infosPersoClientJLabel.setBounds(20, 50, 400, 150);
        lafenetre.add(infosPersoClientJLabel);

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        laTable = new JTable(defaultTableModel);
        laTable.setRowSelectionAllowed(true);
        laTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        dernierAjouteDansFenetre += 250;
        laTable.setBounds(20, dernierAjouteDansFenetre, 700, 50);

        for (String nom : descZonesSaisies) {
            defaultTableModel.addColumn(nom);
        }

        JScrollPane scrollPane = new JScrollPane(laTable);
        scrollPane.setBounds(20, dernierAjouteDansFenetre, 1050, 50);
        lafenetre.add(scrollPane);

        dernierAjouteDansFenetre += 50;
        JLabel jLabel = new JLabel("Paiement par " + infosAdditionnel.toString() + " ");
        jLabel.setBounds(20, dernierAjouteDansFenetre, 200, 40);
        listeJLabel.put(infosAdditionnel.toString(), jLabel);
        lafenetre.add(jLabel);

        /**
         * INSERT INTO PaiementParCarteDeCredit VALUES('4595209044339888','Visa',1)
         *
         * INSERT INTO PaiementParCheque VALUES('459520904433',778977,2)
         */
        if (infosAdditionnel.equals("Cheque")) {
            dernierAjouteDansFenetre += 30;
            JLabel jLabel01 = new JLabel("noCheque");
            jLabel01.setBounds(20, dernierAjouteDansFenetre, 200, 40);
            listeJLabel.put("noCheque", jLabel01);
            lafenetre.add(jLabel01);

            JTextField jtext01 = new JTextField("459520904433");
            jtext01.setBounds(200, dernierAjouteDansFenetre, 400, 40);
            listeJTextField.put("noCheque", jtext01);
            lafenetre.add(jtext01);

            dernierAjouteDansFenetre += 40;
            JLabel jLabel02 = new JLabel("idBanque");
            jLabel02.setBounds(20, dernierAjouteDansFenetre, 200, 40);
            listeJLabel.put("idBanque", jLabel02);
            lafenetre.add(jLabel02);

            JTextField jtext02 = new JTextField("778977");
            jtext02.setBounds(200, dernierAjouteDansFenetre, 400, 40);
            listeJTextField.put("idBanque", jtext02);
            lafenetre.add(jtext02);

        } else if (infosAdditionnel.equals("Carte credit")) {
            dernierAjouteDansFenetre += 30;
            JLabel jLabel01 = new JLabel("noCarte");
            jLabel01.setBounds(20, dernierAjouteDansFenetre, 200, 40);
            listeJLabel.put("noCarte", jLabel01);
            lafenetre.add(jLabel01);

            JTextField jtext01 = new JTextField("4595209044339888");
            jtext01.setBounds(200, dernierAjouteDansFenetre, 400, 40);
            listeJTextField.put("noCarte", jtext01);
            lafenetre.add(jtext01);

            dernierAjouteDansFenetre += 40;
            JLabel jLabel02 = new JLabel("typeCarte");
            jLabel02.setBounds(20, dernierAjouteDansFenetre, 200, 40);
            listeJLabel.put("typeCarte", jLabel02);
            lafenetre.add(jLabel02);

            JTextField jtext02 = new JTextField("Master Card ou Visa ou American Express");
            jtext02.setBounds(200, dernierAjouteDansFenetre, 400, 40);
            listeJTextField.put("typeCarte", jtext02);
            lafenetre.add(jtext02);
        }

        dernierAjouteDansFenetre += 100;
        JButton btn1 = new JButton(nomBoutons[0]);
        btn1.setVisible(true);
        btn1.setBounds(10, dernierAjouteDansFenetre, 100, 40);
        btn1.addActionListener(this);
        listeJButton.put(nomBoutons[0], btn1);
        lafenetre.add(btn1);

        btn1 = new JButton(nomBoutons[1]);
        btn1.setVisible(true);
        btn1.setBounds(130, dernierAjouteDansFenetre, 100, 40);
        btn1.addActionListener(this);
        listeJButton.put(nomBoutons[1], btn1);
        lafenetre.add(btn1);

        dernierAjouteDansFenetre += 100;
        lafenetre.setSize(1100, dernierAjouteDansFenetre);
        lafenetre.setLayout(null);
        lafenetre.setModal(true);
        lafenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void actionPerformed(ActionEvent source) {
        try {
            if (source.getSource() == listeJButton.get("ok")) {
                System.out.println("OKO OKO KO KO  ");

                int[] selectedRow = laTable.getSelectedRows();
                int derniereInsertion = 0;
                for (int i = 0; i < selectedRow.length; i++) {
                    Object[] objTemp = new Object[descZonesSaisies.length + 2];
                    for (int j = 0; j < descZonesSaisies.length; j++) {
                        System.out.println("laTable.getValueAt(selectedRow[i], j).toString() ==" + laTable.getValueAt(selectedRow[i], j).toString());
                        objTemp[j] = laTable.getValueAt(selectedRow[i], j).toString();
                    }
                    // //infosAdditionnel.equals("Cheque") noCheque idBanque               
                    // //infosAdditionnel.equals("Carte credit") noCarte typeCarte
                    if (infosAdditionnel.equals("Cheque")) {
                        objTemp[descZonesSaisies.length] = ((JTextField) listeJTextField.get("noCheque")).getText();
                        objTemp[descZonesSaisies.length + 1] = ((JTextField) listeJTextField.get("idBanque")).getText();

                    } else if (infosAdditionnel.equals("Carte credit")) {
                        objTemp[descZonesSaisies.length] = ((JTextField) listeJTextField.get("noCarte")).getText();
                        objTemp[descZonesSaisies.length + 1] = ((JTextField) listeJTextField.get("typeCarte")).getText();
                    }
                    hashSaisiesRetour.put(i, objTemp);
                }

                System.out.println("OKO OKO KO KO end end end end  ");
                estOK = true;

            } else {
                estOK = false;
            }
            lafenetre.setVisible(false);
            lafenetre.dispose();

        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(null,
                    "Il y eu une erreur lors de la saisie des donnees",
                    "ERREUR : Saisie des donnes",
                    JOptionPane.ERROR_MESSAGE);
            estOK = false;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

/**
 *
 * @author Borgella
 */
public interface DAO_bd {
    public boolean preparerLivraison(Object codeClient);
    public boolean inscrirePaiement(Object noCommande);
    public boolean connectionBD();
    
}

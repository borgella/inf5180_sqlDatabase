/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TABLES;

/**
 *
 * @author Borgella
 */
public class Administration {

    private final String nomTable = "Administration";//                   varchar2(12) NOT NULL UNIQUE,
    private int noAdministrateur;//           INTEGER NOT NULL,
    private String motPasse;//                   varchar2(12) NOT NULL UNIQUE,
    private int droitAdministrateur;//        INTEGER NOT NULL,
    private int codeUtilisateur;//            INTEGER NOT NULL,

}

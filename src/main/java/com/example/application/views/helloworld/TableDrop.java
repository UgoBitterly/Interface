/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.views.helloworld;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author matlu
 */
public class TableDrop {
      public static void tabledrop(Connection con, String nomtable) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try { 
            con.setAutoCommit(false);
            try (Statement st = con.createStatement()) {
                st.executeUpdate("drop table " + nomtable);
            }
        } catch (Exception e) {
           
            con.rollback();
            System.out.println("table inexistante");
        }
    }
      
    
 public static void DropTable(Connection con) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
       tabledrop(con,"etudiant");
       //tabledrop(con,"inscription");
       tabledrop(con,"module"); 
       tabledrop(con,"semestre");
       tabledrop(con,"groupeModule");
      //tabledrop(con,"historique");
        
          //tabledrop(con,"creneau");
           //tabledrop(con,"moduleOuvert");
            tabledrop(con,"administrateur");
            
    }}
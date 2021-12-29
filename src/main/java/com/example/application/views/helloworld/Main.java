/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.views.helloworld;

import com.example.application.views.helloworld.ConnexionAdmin;
import com.example.application.views.helloworld.AfficheBdd;
import fr.lutz.projetinfoS3.ConnexionEtudiant;
import fr.lutz.projetinfoS3.TableDrop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author matlu
 */
public class Main {
   


    public static Connection connectPostgresql(String host, int port,
            String database, String user, String pass)
            throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port + "/" + database, user, pass);
        // fixe le plus haut degré d'isolation entre transactions
        
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }
//...
 
         
            public static int trouveEtudiant(Connection con, String nom)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from etudiant where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
               System.out.println("salutsalut"); 
               return -1;
                
            }
            return findP.getInt(1);
        }
    }
       
            


   public static boolean inscriptionExists(Connection con, int etudiantId, int moduleId, int semestreId) throws SQLException {
        try (Statement st = con.createStatement();
                ResultSet test = st.executeQuery("select * from inscription where "
                        + "etudiant = " + etudiantId
                        + " and module = " + moduleId
                        + " and semestre = " + semestreId)) {
            return test.next();
        }
    }
   


 
    
   
    public static void main(String[] args) {
        try ( Connection con = connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) {
            
            
           TableDrop.DropTable(con);
   
         CreateTable.createTableEtudiant(con);
         CreateTable.createTableModule(con);
         CreateTable.createTableSemestre(con);
         CreateTable.createTableGroupeModule(con);
         CreateTable.createTableAdmin(con);
         CreateTable.createTableInscription(con);
         CreateTable.createTableCreneau(con);
         CreateTable.createTableHistorique(con);
         
         
            LocalDate ld = LocalDate.of(1985, Month.MARCH, 23);
            java.sql.Date sqld = java.sql.Date.valueOf(ld);
            CreationLigne.createEtudiant(con, "Toto","Titi", sqld,"matthieu.lutz@insa-strasbourg.fr","mat");
            CreationLigne.createModule(con,"toto",1,1);
           
            CreationLigne.createAdmin(con,"toto","titi","salut","matt");
            CreationLigne.createSemestre(con,1,2);
            CreationLigne.createEtudiant(con,"Lutz","Marcel",sqld,"lutzmarcel@estvideo.fr","1945");
            CreationLigne.createEtudiant(con,"Lutz","Michele",sqld,"lutzmic@estvideo.fr","1");
           CreationLigne.createSemestre(con,2,1);
            CreationLigne.createGroupeModule(con,1);
           
              CreationLigne.createGroupeModule(con,2);
            CreationLigne.createSemestre(con,1,3);
            CreationLigne.createSemestre(con,8,3);
            AfficheBdd.afficheEtudiant(con);
            AfficheBdd.afficheSemestre(con);
            CreateTable.createTableModuleOuvert(con);
            AfficheBdd.afficheModule(con);
            CreationLigne.createModuleOuvert(con,1,1);
            CreationLigne.createEtudiant(con,"matthieu","lut",sqld,"ugo.bietterly@insa-strasbourg.fr","mooo");
            ModifieBdd.changeNomEtudiant(con,"benjamin","matthieu");
            ModifieBdd.changeNombrePlaceMax(con,50,"toto");
           int result= trouveEtudiant(con,"Toto");
            
             CreationLigne.createInscription(con,3,1);
            CreationLigne.createCreneau(con, 1, 1);
            CreationLigne.createHistorique(con, 3, 1);
            
            String verif= ConnexionEtudiant.connexionEtudiant(con,"ugo.bietterly@ins-strasbourg.fr","tp");
           System.out.println(verif);
            String verifica = ConnexionAdmin.connexionAdmin(con,"matt","salut");
            System.out.println(verifica);
        } catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
    }
}

    


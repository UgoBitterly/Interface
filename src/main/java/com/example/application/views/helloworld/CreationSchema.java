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
public class CreationSchema {
   
       public static void createSchema(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            // on veut que le schema soit entierement créé ou pas du tout
            // il nous faut plusieurs ordres pour créer le schema
            // on va donc explicitement gérer les connections
            con.setAutoCommit(false);
            st.executeUpdate(
              """
               create table semestre(
                 id integer primary key generated always as identity,
                   annee INTEGER NOT NULL,
                   numero INTEGER NOT NULL)
                     
               """);
            st.executeUpdate(
                 """
               create table etudiant(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                prenom varchar(50) not null,
                 datedenaissance  date ,
                motDePasse varchar(50) not null,
               email varchar(50) not null)
                
               """);
           
            
            st.executeUpdate(
              """
               create table administrateur(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 prenom varchar(50) not null,
                 motDePasse varchar(50) not null,
                 email varchar(50) not null
               )
               """);
            st.executeUpdate(            
             """
               create table module(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 nbrePersonneMax INTEGER not null,
                nbrePersonneMin INTEGER not null    
            )
               """);
            st.executeUpdate(
               """
               create table GroupeModule(
                 id integer primary key generated always as identity,
                 creneau  INTEGER NOT NULL
                     
            )
               """);
            
            
           
            st.executeUpdate(
            """
            CREATE TABLE ModuleOuvert(
              idSemestre INTEGER,
              idModule INTEGER)            
            """);
            st.executeUpdate(
            """
            alter table ModuleOuvert 
              add constraint fk_ModuleOuvert_idModule
              foreign key(idModule) references Module(idModule)
              on delete restrict
              on update restrict
            """);
            st.executeUpdate(
            """
            alter table ModuelOuvert 
              add constraint fk_ModuleOuvert_idSemestre
              foreign key(idSemestre) references Semestre(idSemestre)
              on delete restrict
              on update restrict
            """);
            
           
            st.executeUpdate(
            """
            CREATE TABLE Creneau (
              idModule INTEGER,
              idGroupe INTEGER)
            """);
            st.executeUpdate(
            """
            alter table Creneau 
              add constraint fk_Creneau_idModule
              foreign key(idModule) references Module(idModule)
              on delete restrict
              on update restrict
            """);
            st.executeUpdate(
            """
            alter table Creneau
              add constraint fk_Creneau_idGroupe
              foreign key(idGroupe) references GroupeModule(idGroupeModule)
              on delete restrict
              on update restrict
            """);
            
            st.executeUpdate(
            """
            CREATE TABLE HistoriqueModules (
              idEtudiant INTEGER,
              idModule INTEGER)
            """);
            st.executeUpdate(
            """
            alter table HistoriqueModules
              add constraint fk_Historique_Modules_idEtudiant
              foreign key(idEtudiant) references Etudiant(idEtudiant)
              on delete restrict
              on update restrict
            """);
            st.executeUpdate(
            """
            alter table HistoriqueModules
              add constraint fk_Historique_Modules_idModule
              foreign key(idModule) references Module(idModule) 
              on delete restrict
              on update restrict
            """);
             st.executeUpdate(
            """
            CREATE TABLE Inscription (
              idEtudiant INTEGER,
              idModule INTEGER)
            """);
            st.executeUpdate(
            """
            alter table Inscription
              add constraint fk_Inscription_idEtudiant
              foreign key(idEtudiant) references Etudiant(idEtudiant)
              on delete restrict
              on update restrict
            """);
            st.executeUpdate(
            """
            alter table Inscription
              add constraint fk_Inscription_idModule
              foreign key(idModule) references Module(idModule) 
              on delete restrict
              on update restrict
            """);
            // si j'arrive ici, c'est que tout s'est bien passé
            // je valide la transaction
            con.commit();
        } catch (SQLException ex) {
            // si quelque chose se passe mal, j'annule la transaction
            // avant de resignaler l'exception
            con.rollback();
            throw ex;
        } finally {
            // pour s'assurer que le autoCommit(true) reste le comportement
            // par défaut (utile dans la plupart des "select"
            con.setAutoCommit(true);
        }
    }
}

    


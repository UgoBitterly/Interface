/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.views.helloworld;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author matlu
 */
//création de l'objet étudiant (pas utilisé dans le projet)
public class CreationEtudiant {
   protected int id;
   protected String nom, prenom, email,mdp;
   protected java.sql.Date dateDeNaissance;

    public CreationEtudiant (int id, String nom, String prenom, java.sql.Date dateDeNaissance, String email, String mdp) {
        this.id = id;
        this.nom= nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.dateDeNaissance = dateDeNaissance;
    }
    
    public String getNom(){
        return this.nom;
        
    }

   public String getPrenom(){
        return this.prenom;
        
    }
   public String getMdp(){
        return this.mdp;
        
    }
   public String getEmail(){
        return this.email;
        
    }
   public int getId(){
        return this.id;
        
    }
public java.sql.Date getDatedeNaissance(){
        return this.dateDeNaissance;
        
    }
public void setId(int id){ 
        this.id=id;
    }  
public void setMdp(String mdp){ 
        this.mdp=mdp;
    }  
    public void setEmail(String email){ 
        this.email=email;
    }  
    public void setNom(String nom){ 
        this.nom=nom;
    }  
    public void setPrenom(String prenom){ 
        this.prenom=prenom;
    }  
    public void setDatedeNaissance(java.sql.Date ld){ 
        this.dateDeNaissance=ld;
    }  
    
      public void AjouteEtudiant(Connection con,CreationEtudiant etudiant) 
        throws SQLException {
         try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into etudiant (nom,prenom,datedenaissance,motdepasse,email)
          values (?,?,?,?,?)
        """)) {
            
     
            pst.setString(1, this.getNom());
            pst.setString(2,this.getPrenom());
            pst.setDate(3, this.getDatedeNaissance());
            pst.setString(4, this.getEmail());
            pst.setString(5,this.getMdp());
            
            pst.executeUpdate();
        }
}}

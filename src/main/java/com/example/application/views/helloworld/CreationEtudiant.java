/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.views.helloworld;

/**
 *
 * @author matlu
 */
public class CreationEtudiant {
     private Long id;
    private String nom, prenom, email,mdp;
    private java.sql.Date dateDeNaissance;

    public CreationEtudiant (Long id, String nom, String prenom, java.sql.Date dateDeNaissance, String email, String mdp) {
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

   public String getPrenoom(){
        return this.prenom;
        
    }
   public String getMdp(){
        return this.mdp;
        
    }
   public String getEmail(){
        return this.email;
        
    }
   public Long getId(){
        return this.id;
        
    }
public java.sql.Date getDatedeNaissance(){
        return this.dateDeNaissance;
        
    }
public void setId(long id){ 
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
}

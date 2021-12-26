/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.views.helloworld;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 *
 * @author matlu
 */
@Component
public class Etudiantcode {
  
    @Autowired

    public List<CreationEtudiant>AfficheEtudiant(Connection con)throws SQLException {
        try (Statement st = con.createStatement();
                ResultSet rres = st.executeQuery(
                        "select * from etudiant" )) {
            List<CreationEtudiant> res = new ArrayList<>();
            while (rres.next()) {
                CreationEtudiant etud= new CreationEtudiant(rres.getInt("id"),rres.getString("nom"),rres.getString("prenom"),rres.getDate("dateDeNaissance"),rres.getString("email"),rres.getString("motdepasse"));
                res.add(etud);
            }
            return res;
        }
            
                
            }
    
        }
    

            
    


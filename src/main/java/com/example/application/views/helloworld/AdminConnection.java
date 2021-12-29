package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ugobitterly
 */

@PageTitle("Connection")
@Route(value = "connection1", layout = MainLayout.class)
public class AdminConnection extends VerticalLayout {

    public AdminConnection() 
            throws SQLException {
         try ( Connection con = Main.connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) { 
        //creation du login avec adresse mail et mdp pour s'indentifier
        setId("connection-view");
        setAlignItems(Alignment.CENTER);
        EmailField email = new EmailField("Adresse mail");
        email.setLabel("Adresse mail");
        email.getElement().setAttribute("nom", "email");
        email.setPlaceholder("utilisateur@insa-strasbourg.fr");
        email.setErrorMessage("Veuillez entrer une adresse mail INSA");
        email.setClearButtonVisible(true);
        email.setPattern("^.+@insa-strasbourg\\.fr$");
        add(email);
        var password = new PasswordField("Mot de passe");
        add(
        new H2("Connection en tant qu'administrateur"),
                email,
                password,
                new Button("Se connecter", event -> {
                String email1= email.getValue();
                String mdp = password.getValue();
            try {
                add(new Paragraph ("ok"));
                var resultat = ConnexionAdmin.connexionAdmin(con, email1, mdp);
               add(new Paragraph ("salut"));
                if (resultat=="ok"){
                    add(new Paragraph ("tout est ok"));
                   
                }
                else {
                    add(new Paragraph ("mot de passe ou nom d'utilistaeur incorrect"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                add(new Paragraph ("probleme bdd"));
            }
               
                
                password.setValue("");    
            
                })
                
        ); }
        catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
           
    }
}
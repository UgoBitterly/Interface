package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import static com.example.application.views.helloworld.Admin.connectPostgresql;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
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
@PageTitle("ConnectionEtudiant")
@Route(value = "connection2", layout = MainLayout.class)
public class ConnectionEtudiant extends VerticalLayout {
    public ConnectionEtudiant() 
            throws SQLException {
        
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
        new H2("Connection en tant qu'étudiant"),
                email,
                password,
                new Button("Se connecter", event -> {
                                String email1= email.getValue();
                String mdp = password.getValue();
            
                add(new Paragraph ("ok"));
                try ( Connection con = Main.connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) {
                String resultat = ConnexionEtudiant.connexionEtudiant1(con, email1, mdp);
               add(new Paragraph ("salut"));
                if ("ok".equals(resultat)){
                    add(new Paragraph ("tout est ok"));
                   
                }
                else {
                    add(new Paragraph ("mot de passe ou nom d'utilistaeur incorrect"));
                }
             
               
                
                password.setValue("");   }
                catch (Exception ex) {
            System.out.println("Probleme : " + ex);
             add(new Paragraph ("probleme bdd2"));
        }
            
                })
                
        ); }
        
    
}

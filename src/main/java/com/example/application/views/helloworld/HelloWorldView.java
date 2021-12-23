package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
/**
 *
 * @author ugobitterly
 */

@PageTitle("Connection")
@Route(value = "connection1", layout = MainLayout.class)
public class HelloWorldView extends VerticalLayout {

    public HelloWorldView() { 
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
                new Button("Se connecter")
        );   
    }
}
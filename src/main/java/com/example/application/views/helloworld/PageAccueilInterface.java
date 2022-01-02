package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 *
 * @author ugobitterly
 */
//interface page acceuil
@PageTitle("PageAccueil")
@Route(value = "pageaccueil")
@RouteAlias(value = "", layout = MainLayout.class)
public class PageAccueilInterface extends VerticalLayout {
    public PageAccueilInterface(){
        //dans cette classe, on crée "simplement" l'interface d'accueil 
        //avec les deux boutons etudiant et administrateur qui dirige vers les pages d'authentification respectives
        setAlignItems(Alignment.CENTER);
        
        Button admin = new Button("Administrateur");
        admin.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        admin.addClickListener(e ->
                admin.getUI().ifPresent(ui ->
                        ui.navigate("connection1"))
        );

        Button etudiant = new Button("Etudiant");
        etudiant.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        etudiant.addClickListener(e ->
                etudiant.getUI().ifPresent(ui ->
                        ui.navigate("connection2"))
        );

        HorizontalLayout choix = new HorizontalLayout(admin,etudiant);
        choix.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(
        new H1("Extranet INSA Strasbourg"),
                new H3("Veuillez sélectionner votre statut d'utilisateur"),
                choix
        );         
    }
}
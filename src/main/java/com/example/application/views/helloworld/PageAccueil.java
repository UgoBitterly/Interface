package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
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
@PageTitle("PageAccueil")
@Route(value = "pageaccueil")
@RouteAlias(value = "", layout = MainLayout.class)
public class PageAccueil extends VerticalLayout {
    public PageAccueil(){
        //dans cette classe, on crée "simplement" l'interface d'accueil 
        //avec les deux boutons etudiant et administrateur qui dirige vers les pages d'authentification respectives
        setAlignItems(Alignment.CENTER);
        
        NativeButton admin = new NativeButton("Administrateur");
        admin.addClickListener(e ->
                admin.getUI().ifPresent(ui ->
                        ui.navigate("connection1"))
        );
        NativeButton etudiant = new NativeButton("Etudiant");
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
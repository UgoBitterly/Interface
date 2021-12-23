package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
/**
 *
 * @author ugobitterly
 */
@PageTitle("Etudiant")
@Route(value = "etudiant", layout = MainLayout.class)
public class Etudiant extends VerticalLayout {
}
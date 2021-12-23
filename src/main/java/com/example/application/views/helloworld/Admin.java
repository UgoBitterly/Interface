package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
/**
 *
 * @author ugobitterly
 */
@PageTitle("Administrateur")
@Route(value = "admin", layout = MainLayout.class)
public class Admin extends VerticalLayout {
    public Admin() {
        Button semestre = new Button("Semestre");
        Button groupe = new Button("Groupe");
        Button module = new Button("Module");
        Button etudiant = new Button("Etudiant");
        
        HorizontalLayout creer = new HorizontalLayout(semestre,groupe,module,etudiant);
        creer.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(new H2("Veuillez sélectionner ce que vous voulez créer"),
                creer
        );
    }
}

package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
/**
 *
 * @author ugobitterly
 */
@PageTitle("Administrateur")
@Route(value = "admin", layout = MainLayout.class)
public class Admin extends VerticalLayout {
    private final Tab accueil;
    private final Tab semestre;
    private final Tab groupe;
    private final Tab module;
    private final Tab etudiant;
    private final VerticalLayout content;
    
    public Admin() {
        accueil = new Tab("Accueil");
        semestre = new Tab("Semestre");
	groupe = new Tab("Groupe");
	module = new Tab("Module");
        etudiant = new Tab("Etudiant");

	Tabs tabs = new Tabs(accueil, semestre, groupe, module, etudiant);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
	tabs.addSelectedChangeListener(event ->
		setContent(event.getSelectedTab())
	);

	content = new VerticalLayout();
	content.setSpacing(false);
	setContent(tabs.getSelectedTab());

	add(tabs, content);
    }
    private void setContent(Tab tab) {
	content.removeAll();
        //ici on défini le contenu à afficher pour chaque "tab" de l'interface
        //semestre
        TextField numero = new TextField("Numéro");
        TextField annee = new TextField("Année");
        TextField idsemestre = new TextField("Id");
        Button creersemestre = new Button("Créer");
        Button rsemestre = new Button("Réinitialiser", event -> {
            numero.setValue("");
            annee.setValue("");
            idsemestre.setValue("");
        });
        rsemestre.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationsemestre = new HorizontalLayout(numero,annee,idsemestre,creersemestre,rsemestre);
        creationsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
                
        //groupe (de modules)
        TextField idgroupe = new TextField("Id");
        TextField creneau = new TextField("Créneau horaire");
        Button creergroupe = new Button("Créer");
        Button rgroupe = new Button("Réinitialiser", event -> {
                idgroupe.setValue("");
                creneau.setValue("");
        });
        rgroupe.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationgroupe = new HorizontalLayout(idgroupe,creneau,creergroupe,rgroupe);
        creationsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
                
        //module
        TextField idmodule = new TextField("Id");
        TextField nommodule = new TextField("Nom");
        TextField nbmax = new TextField("Capacité maximale");
        TextField nbmin = new TextField("Capacité minimale");
        Button creermodule = new Button("Créer");
        Button rmodule = new Button("Réinitialiser", event -> {
                idmodule.setValue("");
                nommodule.setValue("");
                nbmax.setValue("");
                nbmin.setValue("");
        });
        rmodule.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationmodule = new HorizontalLayout(idmodule,nommodule,nbmax,nbmin,creermodule,rmodule);
        creationmodule.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
                
	if (tab.equals(accueil)) {
            content.add(new Paragraph("Veuillez choisir ce que vous voulez créer en cliquant sur l'icone correspondant"));
        } else if (tab.equals(semestre)) {
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau semestre"),creationsemestre);
	} else if (tab.equals(groupe)) {
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau groupe"),creationgroupe);
	} else if (tab.equals(module)){
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau module"),creationmodule);
	} else {
            content.add(new Paragraph("Créer étudiant"));
        }
    }
}

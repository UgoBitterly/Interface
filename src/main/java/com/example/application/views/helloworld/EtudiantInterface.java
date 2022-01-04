package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
/**
 *
 * @author ugobitterly
 */
//interafce étudiant
@PageTitle("Etudiant")
@Route(value = "etudiant", layout = MainLayout.class)
public class EtudiantInterface extends VerticalLayout {
    private final Tab accueil;
    private final Tab groupe1;
    private final Tab groupe2;
    private final Tab groupe3;
    private final Tab historique;
    private final Tab deconnexion;
    private final VerticalLayout content;

    public EtudiantInterface() {
	accueil = new Tab("Consignes");
	groupe1 = new Tab("Groupe 1");
	groupe2 = new Tab("Groupe 2");
        groupe3 = new Tab("Groupe 3");
        historique = new Tab("Historique");
        deconnexion = new Tab("Déconnexion");

	Tabs tabs = new Tabs(accueil, groupe1, groupe2, groupe3,historique,deconnexion);
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
        //Groupe 1
        TextField modulesgroupe1 = new TextField("Module");
        EmailField mailetudiant1 = new EmailField("Adresse mail");
        mailetudiant1.setPlaceholder("eleve@insa-strasbourg.fr");
        mailetudiant1.setErrorMessage("Veuillez entrer une adresse mail INSA");
        mailetudiant1.setPattern("^.+@insa-strasbourg\\.fr$");
        PasswordField mdpetudiant1 = new PasswordField("Mot de passe");
        Button valider1 = new Button("Valider inscription à ce module",new Icon(VaadinIcon.CHECK_CIRCLE));
        valider1.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        valider1.addClickListener(e ->{
            Notification.show("Votre inscription a bien été prise en compte", 3000, Notification.Position.MIDDLE);
        });
        HorizontalLayout confirmation1 = new HorizontalLayout(mailetudiant1,mdpetudiant1,valider1);
        confirmation1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //Groupe2
        TextField modulesgroupe2 = new TextField("Module");
        EmailField mailetudiant2 = new EmailField("Adresse mail");
        mailetudiant2.setPlaceholder("eleve@insa-strasbourg.fr");
        mailetudiant2.setErrorMessage("Veuillez entrer une adresse mail INSA");
        mailetudiant2.setPattern("^.+@insa-strasbourg\\.fr$");
        PasswordField mdpetudiant2 = new PasswordField("Mot de passe");
        Button valider2 = new Button("Valider inscription à ce module",new Icon(VaadinIcon.CHECK_CIRCLE));
        valider2.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        valider2.addClickListener(e ->{
            Notification.show("Votre inscription a bien été prise en compte", 3000, Notification.Position.MIDDLE);
        });
        HorizontalLayout confirmation2 = new HorizontalLayout(mailetudiant2,mdpetudiant2,valider2);
        confirmation2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //Groupe3
        TextField modulesgroupe3 = new TextField("Module");
        EmailField mailetudiant3 = new EmailField("Adresse mail");
        mailetudiant3.setPlaceholder("eleve@insa-strasbourg.fr");
        mailetudiant3.setErrorMessage("Veuillez entrer une adresse mail INSA");
        mailetudiant3.setPattern("^.+@insa-strasbourg\\.fr$");
        PasswordField mdpetudiant3 = new PasswordField("Mot de passe");
        Button valider3 = new Button("Valider inscription à ce module",new Icon(VaadinIcon.CHECK_CIRCLE));
        valider3.addClickListener(e ->{
            Notification.show("Votre inscription a bien été prise en compte", 3000, Notification.Position.MIDDLE);
        });
        valider3.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        HorizontalLayout confirmation3 = new HorizontalLayout(mailetudiant3,mdpetudiant3,valider3);
        confirmation3.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //Historique
        Button affichehistorique = new Button("Afficher l'historique", event -> {
            Notification.show("Pas d'historique disponible pour le moment, rééssayez à la fin du semestre", 3000, Notification.Position.MIDDLE);
        });
        affichehistorique.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        //Déconnexion
        Button deco = new Button("Déconnexion",new Icon(VaadinIcon.EXCLAMATION_CIRCLE_O));
        deco.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_LARGE);
        deco.getElement().setAttribute("aria-label", "Add item");
        deco.addClickListener(e -> {
            deco.getUI().ifPresent(ui -> ui.navigate("pageaccueil"));
            Notification.show("Vous avez bien été déconnecté de votre session", 2500, Notification.Position.MIDDLE);
        });

        if (tab.equals(accueil)) {
		content.add(new Paragraph("Pour chacun des 3 groupes de modules suivants vous allez devoir choisir quels sont les modules qui vous intéressent."),
                new Paragraph("Veuillez en choisir un seul pour chaque groupe."),
                new Paragraph("Choisissez bien et vérifiez avant de valider vos choix, pas de modifications possibles !"));
	} else if (tab.equals(groupe1)) {
                content.add(new Paragraph("Entrez le nom du module que vous souhaitez suivre au semestre prochain, pour le groupe 1."),
                        new Paragraph("Tenez bien compte des critères donnés sur la liste."),modulesgroupe1,
                        new Paragraph("Pour valider votre inscription à ce module, merci de renseigner les informations suivantes :"),
                        confirmation1);
	} else if (tab.equals(groupe2)){
		content.add(new Paragraph("Entrez le nom du module que vous souhaitez suivre au semestre prochain, pour le groupe 2"),
                        new Paragraph("Tenez bien compte des critères donnés sur la liste."),modulesgroupe2,
                        new Paragraph("Pour valider votre inscription à ce module, merci de renseigner les informations suivantes :"),
                        confirmation2);
	} else if (tab.equals(groupe3)){
                content.add(new Paragraph("Entrez le nom du module que vous souhaitez suivre au semestre prochain, pour le groupe 3"),
                        new Paragraph("Tenez bien compte des critères donnés sur la liste."),modulesgroupe3,
                        new Paragraph("Pour valider votre inscription à ce module, merci de renseigner les informations suivantes :"),
                        confirmation3);
        } else if (tab.equals(historique)){
                content.add(new Paragraph("Vous pouvez visualiser votre historique de modules suivis ici :"),affichehistorique);
        } else if (tab.equals(deconnexion)) {
            content.add(new Paragraph("Si vous souhaitez vous déconnecter, merci de presser le bouton correspondant."),
            new Paragraph("Toutes les actions que vous avez effectuées seront enregistrées et vous serez redirigé"
                    + "vers la page d'accueil"),deco);
        }
    }
}
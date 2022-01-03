package com.example.application.views.helloworld;
;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
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
    private final VerticalLayout content;

    public EtudiantInterface() {
	accueil = new Tab("Consignes");
	groupe1 = new Tab("Groupe 1");
	groupe2 = new Tab("Groupe 2");
        groupe3 = new Tab("Groupe 3");
        historique = new Tab("Historique");

	Tabs tabs = new Tabs(accueil, groupe1, groupe2, groupe3,historique);
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
        Button valider1 = new Button("Valider inscription à ce module");
        valider1.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        valider1.addClickListener(clickEvent ->{
            add(new Paragraph("Votre inscription à ce module a bien été prise en compte."));
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
        Button valider2 = new Button("Valider inscription à ce module");
        valider2.addClickListener(clickEvent ->{
            add(new Paragraph("Votre inscription à ce module a bien été prise en compte."));
        });
        valider2.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        HorizontalLayout confirmation2 = new HorizontalLayout(mailetudiant2,mdpetudiant2,valider2);
        confirmation2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //Groupe3
        TextField modulesgroupe3 = new TextField("Module");
        EmailField mailetudiant3 = new EmailField("Adresse mail");
        mailetudiant3.setPlaceholder("eleve@insa-strasbourg.fr");
        mailetudiant3.setErrorMessage("Veuillez entrer une adresse mail INSA");
        mailetudiant3.setPattern("^.+@insa-strasbourg\\.fr$");
        PasswordField mdpetudiant3 = new PasswordField("Mot de passe");
        Button valider3 = new Button("Valider inscription à ce module");
        valider3.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        valider3.addClickListener(clickEvent ->{
            add(new Paragraph("Votre inscription à ce module a bien été prise en compte."));
        });
        HorizontalLayout confirmation3 = new HorizontalLayout(mailetudiant3,mdpetudiant3,valider3);
        confirmation3.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //Historique
        Button affichehistorique = new Button("Afficher l'historique", event -> {
            //ici tu peux rajouter ta méthode pour afficher l'historique
        });
        affichehistorique.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

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
        } else {
                content.add(new Paragraph("Vous pouvez visualiser votre historique de modules suivis ici :"),affichehistorique);
        }
    }
}
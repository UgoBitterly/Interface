package com.example.application.views.helloworld;
;
import com.example.application.views.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
/**
 *
 * @author ugobitterly
 */
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

		if (tab.equals(accueil)) {
			content.add(new Paragraph("Pour chacun des 3 groupes de modules suivants vous allez devoir choisir quels sont les modules qui vous intéressent."),
                        new Paragraph("Veuillez en choisir un seul pour chaque groupe."),
                        new Paragraph("Choisissez bien et vérifiez avant de valider vos choix, pas de modifications possibles !"));
		} else if (tab.equals(groupe1)) {
			content.add(new Paragraph("Voici les modules proposés dans le groupe 1"),new Paragraph("Sélectionnez celui de votre choix"));
		} else if (tab.equals(groupe2)){
			content.add(new Paragraph("Voici les modules proposés dans le groupe 2"),new Paragraph("Sélectionnez celui de votre choix"));
		} else if (tab.equals(groupe3)){
                    content.add(new Paragraph("Voici les modules proposés dans le groupe 3"),new Paragraph("Sélectionnez celui de votre choix"));
                } else {
                    content.add(new Paragraph("Vous pouvez visualiser votre historique de modules suivis ici :"));
                }
	}
}
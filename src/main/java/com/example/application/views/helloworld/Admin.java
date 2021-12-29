package com.example.application.views.helloworld;


import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.example.application.views.helloworld.CreationEtudiant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.time.LocalDate;
import java.util.logging.Logger;
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
    private CreationEtudiant etudiant1;
    
    
    public Admin() throws SQLException {
        accueil = new Tab("Accueil");
        semestre = new Tab("Semestre");
	groupe = new Tab("Groupe");
	module = new Tab("Module");
        etudiant = new Tab("Etudiant");

	Tabs tabs = new Tabs(accueil, semestre, groupe, module, etudiant);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
	tabs.addSelectedChangeListener((var event) ->
		{
            try {
                setContent(event.getSelectedTab());
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	);

	content = new VerticalLayout();
	content.setSpacing(false);
	setContent(tabs.getSelectedTab());

	add(tabs, content);
    }
     public static Connection connectPostgresql(String host, int port,
            String database, String user, String pass)
            throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port + "/" + database, user, pass);
        // fixe le plus haut degré d'isolation entre transactions
        
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }
    
    private void setContent(Tab tab)  
        throws SQLException {
         try ( Connection con = connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) {
            
	content.removeAll();
        //ici on défini le contenu à afficher pour chaque "tab" de l'interface
        //semestre
        TextField numero = new TextField("Numéro");
        TextField annee = new TextField("Année");
        Button creersemestre = new Button("Créer", event -> {
        String numero1 = numero.getValue();
        String annee1= annee.getValue();
        try{
            int num = Integer.parseInt(numero1);
            try{
            int anne= Integer.parseInt(annee1);
            CreationLigne.createSemestre(con, anne, num);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }       catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        numero.setValue("");
            annee.setValue("");
            
        });
        Button rsemestre = new Button("Réinitialiser", event -> {
            numero.setValue("");
            annee.setValue("");
        });
        rsemestre.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationsemestre = new HorizontalLayout(numero,annee);
        creationsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttonsemestre = new HorizontalLayout(creersemestre,rsemestre);
        buttonsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //groupe (de modules)
        TextField creneau = new TextField("Créneau horaire");
        Button creergroupe = new Button("Créer", event -> {
            String creneau1 = creneau.getValue();
            try{
            int horraire = Integer.parseInt(creneau1);
            CreationLigne.createGroupeModule(con, horraire);
            }
            catch (NumberFormatException ex){
            ex.printStackTrace();
        }   catch (SQLException ex) { 
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            
        creneau.setValue("");} 
        });
        Button rgroupe = new Button("Réinitialiser", event -> {
                creneau.setValue("");
        });
        rgroupe.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationgroupe = new HorizontalLayout(creneau);
        creationsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttongroupe = new HorizontalLayout(creergroupe,rgroupe);
        buttongroupe.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //module
        TextField nommodule = new TextField("Nom");
        IntegerField nbmax = new IntegerField("Capacité maximale");
        IntegerField nbmin = new IntegerField("Capacité minimale");
        nbmax.setPlaceholder("Entier max");
        nbmin.setPlaceholder("Entier min");
        Button creermodule = new Button("Créer" , event -> {
            String nom = nommodule.getValue();
            Integer nbmax1 = nbmax.getValue();
            Integer nbmin1 = nbmin.getValue();
            try{
            int numbermax = nbmax1;
            try{
            int numbermin = nbmin1;
            CreationLigne.createModule(con, nom, numbermax,numbermin);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }       catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
            
           nommodule.setValue("");
                nbmax.setValue(0);
                nbmin.setValue(0);    
              
        });
        Button rmodule = new Button("Réinitialiser", event -> {
                nommodule.setValue("");
                nbmax.setValue(0);
                nbmin.setValue(0);     
        });
        rmodule.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationmodule = new HorizontalLayout(nommodule,nbmax,nbmin);
        creationmodule.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttonmodule = new HorizontalLayout(creermodule,rmodule);
        buttonmodule.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //etudiant
        TextField nom = new TextField("Nom");
        TextField prenom = new TextField("Prénom");
        EmailField mail = new EmailField("Adresse mail INSA");
        mail.setPlaceholder("utilisateur@insa-strasbourg.fr");
        mail.setErrorMessage("Veuillez entrer une adresse mail INSA");
        TextField mdp = new TextField("Mot de Passe");
        DatePicker datenaissance = new DatePicker("Date de naissance");
        Button creeretudiant = new Button("Créer", event -> { 
           String nom1 = nom.getValue();
           String prenom1=prenom.getValue();
           String email1 = mail.getValue();
           String mdp1 = mdp.getValue();
          LocalDate date1 = datenaissance.getValue(); 
           // try {
               // CreationLigne.createEtudiant(con, nom1, prenom1, date1, email1, mdp1);
            //} catch (SQLException ex) {
               //Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
          // }
          
        nom.setValue("");
           prenom.setValue("");
           mail.setValue("");
           mdp.setValue("");
        });
        Button retudiant = new Button("Réinitialiser", event -> {
           nom.setValue("");
           prenom.setValue("");
           mail.setValue("");
           mdp.setValue("");
       });
        retudiant.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationetudiant = new HorizontalLayout(nom,prenom,mail,mdp,datenaissance);
        creationetudiant.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttonetudiant = new HorizontalLayout(creeretudiant,retudiant);
        buttonetudiant.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //ici on affiche le menu correspondant en fonction du choix de création
	if (tab.equals(accueil)) {
            content.add(new Paragraph("Veuillez choisir ce que vous voulez créer en cliquant sur l'icone correspondant"));
        } else if (tab.equals(semestre)) {
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau semestre"),creationsemestre,buttonsemestre);
	} else if (tab.equals(groupe)) {
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau groupe"),creationgroupe,buttongroupe);
	} else if (tab.equals(module)){
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau module"),creationmodule,buttonmodule);
	} else {
            content.add(new Paragraph("Veuillez entrer les attributs du nouvel étudiant"),creationetudiant,buttonetudiant);
        }
    }catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
}}

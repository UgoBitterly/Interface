package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.time.LocalDate;
import java.util.logging.Logger;
/**
 *
 * @author ugobitterly
 */
//interafce administrateur

@PageTitle("Administrateur")
@Route(value = "admin", layout = MainLayout.class)
public class InterfaceAdmin extends VerticalLayout {
    private final Tab accueil;
    private final Tab semestre;
    private final Tab groupe;
    private final Tab module;
    private final Tab etudiant;
    private final Tab parametres;
    private final Tab deconnexion;
    private final VerticalLayout content;
    private CreationEtudiant etudiant1;
    
    
    public InterfaceAdmin() throws SQLException {
        accueil = new Tab("Accueil");
        semestre = new Tab("Semestre");
	groupe = new Tab("Groupe");
	module = new Tab("Module");
        etudiant = new Tab("Etudiant");
        parametres = new Tab("Paramètres");
        deconnexion = new Tab("Déconnexion");

	Tabs tabs = new Tabs(accueil, semestre, groupe, module, etudiant, parametres,deconnexion);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
	tabs.addSelectedChangeListener((var event) ->
		{
            try {
                setContent(event.getSelectedTab());
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
        //Semestre
        //creation d'un semestre
        IntegerField numero = new IntegerField("Numéro");
        IntegerField annee = new IntegerField("Année");
        Button creersemestre = new Button("Créer", event -> {
            Notification.show("Semestre créé !", 2500, Notification.Position.MIDDLE);
            Integer numero1 = numero.getValue();
            Integer annee1= annee.getValue();
            try{
                int num = numero1;
                try{
                int anne= annee1;
                add(new Paragraph("salut toi"));
                CreationLigne.createSemestre(con, anne, num);//problème de connection avec la bdd actuellement
                add (new Paragraph ("ouf"));
            try ( Statement st = con.createStatement()) {
                ResultSet res = st.executeQuery("select * from semestre");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                Integer annee2 = res.getInt("annee");
                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
                
               
                add(new Paragraph(id + "année du semestre" + annee));
            }
        
    }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            
        }       catch (SQLException ex) {
                    Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    add(new Paragraph("probleme"));
                }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        numero.setValue(null);
            annee.setValue(null);
            
        });
        Button rsemestre = new Button("Réinitialiser", event -> {
            numero.setValue(null);
            annee.setValue(null);
        });
        rsemestre.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationsemestre = new HorizontalLayout(numero,annee);
        creationsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttonsemestre = new HorizontalLayout(creersemestre,rsemestre);
        buttonsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //supprimer un semestre
        IntegerField semestreasupprimer = new IntegerField("Numéro du semestre");
        IntegerField anneesemestreasupprimer = new IntegerField("Année du semestre");
        Button effacersemestre = new Button("Effacer",new Icon(VaadinIcon.TRASH),event -> {
            Notification.show("Semestre supprimé !", 2500, Notification.Position.MIDDLE);
            semestreasupprimer.setValue(null);
            anneesemestreasupprimer.setValue(null);
            Integer semestre1 = semestreasupprimer.getValue();
            Integer annee1 = anneesemestreasupprimer.getValue();
             try{
            int horraire = semestre1;
             try{
            int horraire1 = annee1;
                 try {
                     SupprimeLigne.supprimeSemestre(con, horraire1, horraire);
                 } catch (SQLException ex) {
                     Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
                 }}
                 catch (NumberFormatException ex){
            ex.printStackTrace();
        } 

            }
            catch (NumberFormatException ex){
            ex.printStackTrace();
            }
            
            
        });
        effacersemestre.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout supprimersemestre = new HorizontalLayout(semestreasupprimer,anneesemestreasupprimer,effacersemestre);
        supprimersemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        
        //Groupe (de modules)
        //creation d'un groupe de module
        IntegerField creneau = new IntegerField("Créneau horaire");
        Button creergroupe = new Button("Créer", event -> {
            Notification.show("Nouveau groupe de module créé !", 2500, Notification.Position.MIDDLE);
            Integer creneau1 = creneau.getValue();
            try{
            int horraire = creneau1;
            CreationLigne.createGroupeModule(con, horraire);
            }
            catch (NumberFormatException ex){
            ex.printStackTrace();
        }   catch (SQLException ex) { 
                Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
            
        creneau.setValue(null);} 
        });
        Button rgroupe = new Button("Réinitialiser", event -> {
                creneau.setValue(null);
        });
        rgroupe.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationgroupe = new HorizontalLayout(creneau);
        creationsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttongroupe = new HorizontalLayout(creergroupe,rgroupe);
        buttongroupe.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //supprimer un groupe de module
        IntegerField groupeasupprimer = new IntegerField("Créneau du groupe");
        Button effacergroupe = new Button("Effacer",new Icon(VaadinIcon.TRASH),event -> {
            Notification.show("Groupe de modules supprimé !", 2500, Notification.Position.MIDDLE);
            groupeasupprimer.setValue(null);
             Integer creneau1 = groupeasupprimer.getValue();
             try{
            int horraire = creneau1;
                 try {
                     SupprimeLigne.supprimeGroupeModule(con, horraire);
                 } catch (SQLException ex) {
                     Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
            catch (NumberFormatException ex){
            ex.printStackTrace();
        } 
            
            
        });
        effacergroupe.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout supprimergroupe = new HorizontalLayout(groupeasupprimer,effacergroupe);
        supprimergroupe.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        
        //Module
        //création d'un module
        TextField nommodule = new TextField("Nom");
        IntegerField nbmax = new IntegerField("Capacité maximale");
        nbmax.setPlaceholder("Entier max");
        IntegerField nbmin = new IntegerField("Capacité minimale");
        nbmin.setPlaceholder("Entier min");
        IntegerField choixgroupemodule = new IntegerField("Groupe du module");
        IntegerField numerodusemestre = new IntegerField("Numéro du semestre");
        IntegerField anneedusemestre = new IntegerField("Année");
        Button creermodule = new Button("Créer" , event -> {
            Notification.show("Module créé !", 2500, Notification.Position.MIDDLE);
            String nom = nommodule.getValue();
            Integer nbmax1 = nbmax.getValue();
            Integer nbmin1 = nbmin.getValue();
            Integer choixgroupe = choixgroupemodule.getValue();
            try{
            int numbermax = nbmax1;
            try{
            int numbermin = nbmin1;
            CreationLigne.createModule(con, nom, numbermax,numbermin);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }       catch (SQLException ex) {
                    Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
            
           nommodule.setValue("");
                nbmax.setValue(null);
                nbmin.setValue(null);    
              
        });
        Button rmodule = new Button("Réinitialiser", event -> {
                nommodule.setValue("");
                nbmax.setValue(null);
                nbmin.setValue(null);
                choixgroupemodule.setValue(null);
                numerodusemestre.setValue(null);
                anneedusemestre.setValue(null);
                
        });
        rmodule.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationmodule = new HorizontalLayout(nommodule,nbmax,nbmin,choixgroupemodule);
        creationmodule.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout semestredumodule = new HorizontalLayout(numerodusemestre,anneedusemestre);
        semestredumodule.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttonmodule = new HorizontalLayout(creermodule,rmodule);
        buttonmodule.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
                
        //modifier ou supprimer un module
        TextField moduleasupprimer = new TextField("Module à supprimer");
        Button effacermodule = new Button("Effacer",new Icon(VaadinIcon.TRASH),event -> {
            Notification.show("Module supprimé !", 2500, Notification.Position.MIDDLE);
            moduleasupprimer.setValue("");
            String etudiant1 = moduleasupprimer.getValue();
            try {
                SupprimeLigne.supprimeModule(con, etudiant1);
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        effacermodule.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);
        Button modifiermodule = new Button("Modifier");
        HorizontalLayout supprimermodule = new HorizontalLayout(moduleasupprimer,modifiermodule,effacermodule);
        supprimermodule.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        
        //Etudiant
        //creation d'un etudiant
        TextField nom = new TextField("Nom");
        TextField prenom = new TextField("Prénom");
        EmailField mail = new EmailField("Adresse mail INSA");
        mail.setPlaceholder("Générer");
        Button generermail = new Button("Générer adresse mail", event ->{
            String prenomM=prenom.getValue();
            String nomM=nom.getValue();
            String prenomminuscule = prenomM.toLowerCase();
            String nomminuscule = nomM.toLowerCase();
            mail.setValue(prenomminuscule+"."+nomminuscule+"@insa-strasbourg.fr");
        });
        TextField mdp = new TextField("Mot de Passe");
        DatePicker datenaissance = new DatePicker("Date de naissance");
        Button creeretudiant = new Button("Créer", event -> {
           Notification.show("Nouvel étudiant créé !", 2500, Notification.Position.MIDDLE);
           String nom1=nom.getValue();
           String prenom1=prenom.getValue();
           String email1 = mail.getValue();
           String mdp1 = mdp.getValue();
          LocalDate date1 = datenaissance.getValue(); 
           // try {
               // CreationLigne.createEtudiant(con, nom1, prenom1, date1, email1, mdp1);
            //} catch (SQLException ex) {
               //Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
           datenaissance.setValue(null);
       });
        retudiant.addThemeVariants(ButtonVariant.LUMO_ERROR);
        HorizontalLayout creationetudiant = new HorizontalLayout(prenom,nom,mail,mdp,datenaissance);
        creationetudiant.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        HorizontalLayout buttonetudiant = new HorizontalLayout(generermail,creeretudiant,retudiant);
        buttonetudiant.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //supprimer ou modifier un etudiant
        TextField etudiantasupprimer = new TextField("Etudiant à supprimer");
        Button effaceretudiant = new Button("Effacer",new Icon(VaadinIcon.TRASH),event -> {
            Notification.show("Etudiant supprimé !", 2500, Notification.Position.MIDDLE);
            etudiantasupprimer.setValue("");
            String etudiant1 = etudiantasupprimer.getValue();
            try {
                SupprimeLigne.supprimeEtudiant(con, etudiant1);
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        effaceretudiant.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);
        Button modifieretudiant = new Button("Modifier");
        HorizontalLayout supprimeretudiant = new HorizontalLayout(etudiantasupprimer,modifieretudiant,effaceretudiant);
        supprimeretudiant.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //Paramètres
        Button effacerinscription = new Button("Effacer Inscription",new Icon(VaadinIcon.TRASH), event ->{
            Notification.show("Inscription effacé !", 2500, Notification.Position.MIDDLE);
            try {
                SupprimeTable.supprimeInscription(con);
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        effacerinscription.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR); //pour que ça soit en rouge
        HorizontalLayout effacerinscriptionsemestre = new HorizontalLayout(effacerinscription);
        effacerinscriptionsemestre.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        
        //Déconnexion
        Button deco = new Button("Déconnexion",new Icon(VaadinIcon.EXCLAMATION_CIRCLE_O));
        deco.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_LARGE);
        deco.getElement().setAttribute("aria-label", "Add item");
        deco.addClickListener(e -> {
            deco.getUI().ifPresent(ui -> ui.navigate("pageaccueil"));
            Notification.show("Vous avez bien été déconnecté de votre session", 2500, Notification.Position.MIDDLE);
        });
        
        //ici on affiche le menu correspondant en fonction du choix de création
	if (tab.equals(accueil)) {
            content.add(new Paragraph("Veuillez choisir ce que vous voulez créer ou supprimer en cliquant sur l'icone correspondant."),
            new Paragraph("Vous pouvez également modifier un module ou un étudiant."));
            
        } else if (tab.equals(semestre)) {
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau semestre :"),creationsemestre,buttonsemestre,
                    new Paragraph(""),new Paragraph(""),//je mets ça pour sauter deux lignes
                    new Paragraph("Si vous souhaitez supprimer un semestre, veuillez rentrer son numéro ci-dessous :"),
                    supprimersemestre);
            
	} else if (tab.equals(groupe)) {
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau groupe :"),creationgroupe,buttongroupe,
                    new Paragraph(""),new Paragraph(""), 
                    new Paragraph("Si vous souhaitez supprimer un groupe de module, veuillez rentrer son créneau ci-dessous :"),
                    supprimergroupe);
            
	} else if (tab.equals(module)){
            content.add(new Paragraph("Veuillez entrer les attributs du nouveau module, ainsi que le semestre auquel il appartient :"),
                    creationmodule,semestredumodule,buttonmodule,
                    new Paragraph(""),new Paragraph(""),
                    new Paragraph("Si vous souhaitez modifier ou supprimer un module, veuillez rentrer son nom ci-dessous :"),
                    supprimermodule);
            
	} else if (tab.equals(etudiant)){
            content.add(new Paragraph("Veuillez entrer les attributs du nouvel étudiant :"),creationetudiant,buttonetudiant,
                    new Paragraph(""),new Paragraph(""),
                    new Paragraph("Si vous souhaitez modifier ou supprimer un étudiant, veuillez rentrer son nom ci-dessous :"),
                    supprimeretudiant);
        } else if (tab.equals(parametres)){
            content.add(new Paragraph("La fonction 'Effacer l'inscription' vous permet de remettre à zéro la table inscription."),
            new Paragraph("Cette table contient toutes les informations du semestre en cours, à savoir quel étudiant est inscrit à quel module."),
            new Paragraph("Il est donc nécessaire de tout effacer à la fin de chaque semestre, elle sera remplie à nouveau le semestre suivant"),
            new Paragraph("Enfin, tout ne sera pas effacé, la globalité sera stockée dans un historique disponible pour chaque étudiant."),
            new Paragraph("Cette action est irréversible et ne doit être effectuée qu'une fois le semestre totalement achevé !!"),
            effacerinscriptionsemestre);
        } else if (tab.equals(deconnexion)){
            content.add(new Paragraph("Si vous souhaitez vous déconnecter, merci de presser le bouton correspondant."),
            new Paragraph("Toutes les actions que vous avez effectuées seront enregistrées et vous serez redirigé"
                    + "vers la page d'accueil"),deco);
        }
    }catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
}}

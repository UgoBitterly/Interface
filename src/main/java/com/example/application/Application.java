package com.example.application;

import com.example.application.views.helloworld.CreateTable;
import com.example.application.views.helloworld.CreationLigne;
import com.vaadin.flow.component.dependency.NpmPackage;
import static com.example.application.views.helloworld.Main.connectPostgresql;
import com.example.application.views.helloworld.TableDrop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer {
//spring-boot:run
    
    
    
    public static void main(String[] args) {
       {
           try ( Connection con = connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) {
               TableDrop.DropTable(con);
               CreateTable.createTableEtudiant(con);
                CreateTable.createTableModule(con);
         CreateTable.createTableSemestre(con);
         CreateTable.createTableGroupeModule(con);
         CreateTable.createTableAdmin(con);
               LocalDate ld = LocalDate.of(1985, Month.MARCH, 23);
            java.sql.Date sqld = java.sql.Date.valueOf(ld);
           CreationLigne.createEtudiant(con,"matthieu","lut",sqld,"ugo.bietterly@insa-strasbourg.fr","mooo");
        
    } catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));

}}}

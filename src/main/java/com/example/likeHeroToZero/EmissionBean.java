/**
 * Managed bean Klasse für die Emissionen.
 * CDI nutzt diese Klasse als Controller, um Daten hin und her von der View
 * zur JPA-Entity (EmissionEntity) und anders herum zu senden.
 * Die Controller Methoden referenzieren die Data Access Object "EmissionDAO",
 * diese verwaltet den Entity Manager und damit die Queries zur Database.
 *
 * @author Alessio Nocita
 * @version 1.0
 */

package com.example.likeHeroToZero;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped // weil die Bean ihr State auch nach Benutzerangaben und Reloads behalten sollte
public class EmissionBean implements Serializable {

    /**
     * Managed Bean, der die EntityManager-Methoden (Queries und Transactions) verwaltet
     */
    @Inject
    private EmissionDAO emissionDAO;

    /**
     * JPA-verwaltete EmissionEntity. Dient als Model für die Database.
     */

    private EmissionEntity emissionEntity = new EmissionEntity();

    // Der parameterlose Konstruktor wird implizit instantiiert.

    // obligatorische Getters und Setters

    public int getId() {
        return emissionEntity.getId();
    }

    public void setId(int id) {
        emissionEntity.setId(id);
    }

    public String getCountry() {
        return emissionEntity.getCountry();
    }

    public void setCountry(String country) {
        emissionEntity.setCountry(country);
    }

    public double getEmission() {
        return emissionEntity.getEmission();
    }

    public void setEmission(double emission) {
        emissionEntity.setEmission(emission);
    }

    /**
     * Speichert einen neuen Eintrag in der Emissionen-Tabelle
     * indem es die entsprechende Methode des Service-Controller triggert.
     * Falls das Land schon in der Database ist, so wird den Eintrag geändert, ansonsten neu angelegt.
     * Erschafft ausserdem nach Speicherung eine neue Emission Entity
     * und schickt der growl-Komponente in der add-emission.xhtml eine Erfolgsmeldung.
     */

    public void saveEmission() {
        String country = emissionEntity.getCountry();
        EmissionEntity retrievedEmission = emissionDAO.findEmission(country);
        if (retrievedEmission != null) {
            retrievedEmission.setEmission(emissionEntity.getEmission());
            emissionDAO.changeEmission(retrievedEmission);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Land already in database. Entry updated.", "Land already in database. Entry updated."));
        } else {
        emissionDAO.saveEmission(emissionEntity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry added successfully", "Entry added successfully!"));
         }
        emissionEntity = new EmissionEntity();
    }

    /**
     * Modifiziert ein Emissions-Eintrag.
     * Triggert eine Suche nach dem Länder Name durch die entsprechende Methode der Emission DAO.
     * Das Ergebnis wird in einer neuen EmissionEntity gespeichert.
     * Danach wird Emission-Wert dieser geändert
     * und die Entity wird durch die entsprechende Methode des Entity Manager updated.
     * Ein Reset der Emission Entity folgt.
     * Zum Schluss wird eine Erfolgsmeldung an change-emissions.xhtml gesendet.
     *
     * @param emission die von Formular in change-emission.xhtml empfangene Entity.
     */

    public void changeEmission(EmissionEntity emission) {
        emissionEntity = emissionDAO.findEmission(emission.getId());
        emissionEntity.setEmission(emission.getEmission());
        emissionDAO.changeEmission(emissionEntity);
        emissionEntity = new EmissionEntity();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Field updated successfully!", "Field updated successfully!"));
    }

    /**
     * Holt die Emissions-Daten für die Emission-Tabellen in index.html und change-emissions.xhtml.
     * Optimiert, um mit einem Suchfeld verwendet zu werden.
     * Jeder Tastendruck im Suchformular in index.html triggert diese Methode.
     * Falls der eingegebene wert leer ist (auch nach Löschung)
     * so gibt die Methode die komplette Emissionsliste zurück.
     * Anderseits erschafft sie eine neue Liste
     * die nur das gesuchte Land inkl. Emissionsdaten enthält
     *
     * @return Liste der Länder und ihrer Emissionsdaten
     */

    public List<EmissionEntity> getAllEmissions() {
        String country = StringUtils.capitalize(emissionEntity.getCountry());
        if(country == null || country.isEmpty()) {
            return emissionDAO.getAllEmissions();
        } else {
            EmissionEntity retrievedEmission = emissionDAO.findEmission(country);
            if(retrievedEmission == null) {
                return emissionDAO.getAllEmissions();
            } else {
                List<EmissionEntity> singleCountry = new ArrayList<>();
                singleCountry.add(emissionDAO.findEmission(country));
                return singleCountry;
            }
        }
    }
}

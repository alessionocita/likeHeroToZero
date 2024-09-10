/** JPA-Entität der SQL-Table "emissions"
 * Model für die Emissions-Daten.
 * Wird durch das Bean "EmissionsBean"
 * und durch das Controller Bean "EmissionDAO",
 * der den JPA-EntityManager enthält, verwaltet.
 *
 * @author Alessio Nocita
 * @version 1.0
 *
 */

package com.example.likeHeroToZero;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity                         // als JPA-Entity deklariert.
@Table(name = "emissions")      // ist verbunden mit der SQL-Table "emissions"
public class EmissionEntity {

    // Attribute entsprechen der Tabellenfelder
    @Id                                                 // als ID deklariert
    @GeneratedValue(strategy = GenerationType.IDENTITY) // überlässt MariaDB die ID-Verwaltung
    private int id;

    @NotNull
    @Size(min = 2, max = 100)
    private String country;

    @NotNull
    private double emission;

    // Getters und Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getEmission() {
        return emission;
    }

    public void setEmission(double emission) {
        this.emission = emission;
    }
    }


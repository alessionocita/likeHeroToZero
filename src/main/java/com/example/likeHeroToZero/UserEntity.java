/** JPA-Entität der SQL-Table "users"
 * Model für die User-Daten.
 * Wird durch das backing bean "UserBean"
 * und durch das Controller Bean "UserDAO",
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

@Entity                  // als JPA-Entity deklariert.
@Table(name = "users")  // ist verbunden mit der SQL-Table "users"
public class UserEntity {

    // Attribute entsprechen der Datenbank Columns

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // überlässt die ID-Verwaltung der Datenbank
    private int id;

    @NotNull
    @Size(min = 2, max = 100)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 100)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 100)
    private String email;

    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 100)
    private String userName;

    @NotNull
    @Size(min = 2, max = 256)
    private String password;


    // Getters und Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

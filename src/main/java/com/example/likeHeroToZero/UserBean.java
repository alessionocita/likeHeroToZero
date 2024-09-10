/**
 * Backing bean Klasse für die User-Verwaltung.
 * CDI nutzt diese Klasse als Controller, um Daten hin und her von der View
 * zur JPA-Entity (EmissionEntity) und andersherum zu senden.
 * Die Controller Methoden referenzieren die Data Access Object "UserDAO",
 * diese verwaltet den Entity Manager und damit die Queries zur Database.
 * Referenziert ausserdem für Login und Logout die "UserCookieBean",
 * eine Backing Bean die den Zustand "eingeloggt" für den User speichert.
 *
 * @author Alessio Nocita
 * @version 1.0
 */

package com.example.likeHeroToZero;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import jakarta.faces.context.FacesContext;
import jakarta.validation.ValidationException;

import java.io.Serializable;

@Named              // ist eine Backing bean
@SessionScoped      // bleibt erhalten bis der User den Browser schließt.
public class UserBean implements Serializable {

    /**
     * Managed Bean, die die EntityManager-Methoden (Queries und Transactions) verwaltet
     */
    @Inject
    private UserDAO userDAO;

    /**
     * Managed Bean, die den Logged In - Logged Out Zustand des Users speichert
     */
    @Inject
    private UserCookieBean userCookieBean;

    /**
     * JPA-verwaltete User Entity. Dient als Model für die Database.
     */
    private UserEntity userEntity = new UserEntity();


    // Parameterloser Constructor wird implizit von Java gesetzt.

    // Obligatorische Getters und Setters

    public int getId() {
        return userEntity.getId();
    }

    public void setId(int id) {
        userEntity.setId(id);
    }

    public String getUserName() {
        return userEntity.getUserName();
    }

    public void setUserName(String userName) {
        userEntity.setUserName(userName);
    }

    public String getFirstName() {
        return userEntity.getFirstName();
    }

    public void setFirstName(String firstName) {
        userEntity.setFirstName(firstName);
    }

    public String getLastName() {
        return userEntity.getLastName();
    }

    public void setLastName(String lastName) {
        userEntity.setLastName(lastName);
    }

    public String getEmail() {
        return userEntity.getEmail();
    }

    public void setEmail(String email) {
        userEntity.setEmail(email);
    }

    public String getPassword() {
        return userEntity.getPassword();
    }

    /**
     * Dieser Setter ruft die Custom-statische Methode PasswordUtils.hashPassword auf.
     * Dadurch wird jedes eingegebene Passwort vor der Generierung sofort gehashed.
     * @param password
     * @throws NoSuchAlgorithmException
     */
    public void setPassword(String password) throws NoSuchAlgorithmException {
            String hashedPassword = PasswordUtils.hashPassword(password);
            userEntity.setPassword(hashedPassword);
    }

    /**
     * Registriert einen neuen User.
     * Dafür wird die passende CRUD-Methode der User DAO aufgerufen.
     * Setzt danach die JPA-Entity zurück.
     */
    public void registerUser() throws ValidationException {
        String userName = userEntity.getUserName();
        UserEntity retrievedUser = userDAO.findUser(userName);
        if (retrievedUser != null) {
            FacesContext.getCurrentInstance().addMessage("register-form:user-name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exists", "Username already exists"));
            userEntity = new UserEntity();
        } else {
            userDAO.registerUser(userEntity);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User successfully registered", "User successfully registered"));
            userEntity = new UserEntity();
        }
    }

    /**
     * Verwaltet den User-Login.
     * Die durch die View gelieferten Username und Password werden in der JPA-Entity gespeichert.
     * Danach wird durch die User DAO nach dieser Entity durchgeführt.
     * Wenn der Username existiert und das Passwort mit den DB-Passwort übereinnstimmt,
     * dann wird die Admin-Seite aufgerufen und die Cookie Bean auf "Eingeloggt" gesetzt.
     * Wenn der User nicht gefunden wird, dann wird eine no-user-found.xhtml aufgerufen.
     * Ansonsten wird login-error.xhtml aufgerufen.
     * In beiden Fällen bleibt der User ausgeloggt.
     *
     * @return den String, der die passende Seite aufruft.
     */
    public String login() {
        String userName = userEntity.getUserName();
        String password = userEntity.getPassword();
        UserEntity retrievedUser = userDAO.findUser(userName);
        if (retrievedUser != null && retrievedUser.getPassword().equals(password)) {
            userCookieBean.setUserName(userName);
            userCookieBean.setIsLoggedIn("Y");
            return "admin";
        } else if (retrievedUser == null) {
            userCookieBean.setUserName("");
            userCookieBean.setIsLoggedIn("N");
            userEntity = new UserEntity();
            return "no-user-found";
        } else {
            userCookieBean.setUserName("");
            userCookieBean.setIsLoggedIn("N");
            userEntity = new UserEntity();
            return "login-error";
        }
    }

    /**
     * Verwaltet das Logout.
     * Setzt die User Cookie Bean auf "ausgeloggt" zurück
     * und ruft die Hauptseite auf.
     *
     * @return den passenden String für index.xhtml
     */
    public String logout() {
        userCookieBean.setUserName("");
        userCookieBean.setIsLoggedIn("N");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout successful", "Logout successful"));
        return "index";
    }

    /**
     * Checkt ob der User eingeloggt ist.
     * Wird bei Seiten aufgerufen, die nur für den eingeloggten User zugänglich sein sollen.
     * Falls isLoggedIn beim User Cookie Bean nicht auf "Y" gesetzt ist,
     * dann wird der User zur Login-Seite umgeleitet.
     */
    public void checkIfLoggedIn() {
        String isLoggedIn = userCookieBean.getIsLoggedIn();
        if(!isLoggedIn.equals("Y")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checkt, ob der User sich schon eingeloggt hat.
     * Wird bei der Login Seite aufgerufen.
     * Falls isLoggedIn beim User Cookie Bean auf "Y" gesetzt ist,
     * dann wird der User beim direkten Aufrufen von login.xhtml auf admin.xhtml umgeleitet.
     */
    public void checkIfAlreadyLoggedIn() {
        String isLoggedIn = userCookieBean.getIsLoggedIn();
        if(isLoggedIn.equals("Y")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Setzt alle Formulare zurück
            setUserName("");
            setFirstName("");
            setLastName("");
            setEmail("");
            userCookieBean.setUserName("");
            userCookieBean.setIsLoggedIn("N");
        }
    }
}

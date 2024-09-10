/**
 * Managed Bean für die Verwaltung des Logged in / Logged Out Zustand.
 * Wird für verschiedene methoden des UserBean verwendet.
 *
 * @author Alessio Nocita
 * @version 1.0
 */

package com.example.likeHeroToZero;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserCookieBean implements Serializable {

    /**
     * Username der eingeloggten User
     */
    private String username;

    /**
     * Speichert der Login-Zustand als "Y" oder "N" String.
     */
    private String isLoggedIn;

    /**
     * Obligatorisches parameterloses Constructor.
     * Setzt der Standard Zustand auf "ausgeloggt".
     */
    public UserCookieBean() {
        this.username = "";
        this.isLoggedIn = "N";
    }

    // Obligatorische Getter und Setter
    public String getUserName() {
        return this.username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(String isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}

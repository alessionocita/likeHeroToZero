/**
 * Utility Class mit statischen Methoden für Regex-Validierung
 *
 * @author Alessio Nocita
 * @version 1.0
 */

package com.example.likeHeroToZero;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator {

    /**
     * Validiert ein Standard-Feld.
     * Nur alphanumerische Zeichen und deutsche Buchstaben sind erlaubt.
     * Der String muss von Anfang bis zum Ende mit dem Regex übereinstimmen und darf nicht leer sein.
     *
     * @param field das zu checkende String
     * @return true wenn die Regex-Validierung bestanden wird, ansonsten false.
     */
    public static boolean validateStandardField(String field) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9öäüÄÖÜß]+$");
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }

    /**
     * Validiert ein Email-Feld.
     * Alphanumerische Zeichen, hyphen, underscore und das @-Zeichen sind erlaubt.
     * Der String muss von Anfang bis zum Ende mit dem Regex übereinstimmen und darf nicht leer sein.
     *
     * @param field das zu checkende String
     * @return true wenn die Regex-Validierung bestanden wird, ansonsten false.
     */
    public static boolean validateEmailField(String field) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9@-_.]+$");
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }

    /**
     * Validiert ein Password-Feld.
     * Alphanumerische Zeichen, deutsche Sonderbuchstaben, hyphen, underscore und alle aufgelistete Symbole sind erlaubt.
     * Potentiell gefährliche, Schad-Code generierende Zeichen sind ausgeschlossen.
     * Der String muss von Anfang bis zum Ende mit dem Regex übereinstimmen und darf nicht leer sein.
     *
     * @param field das zu checkende String
     * @return true wenn die Regex-Validierung bestanden wird, ansonsten false.
     */
    public static boolean validatePasswordField(String field) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9öäüÄÖÜß .'?!,;:@$#-_]*$");
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }
}


/**
 * Diese Klasse liefert eine statische Methode für das Hashen des Passworts.
 *
 * @author Alessio Nocita
 * version 1.0
 */

package com.example.likeHeroToZero;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    /**
     * Statische Methode zum Hashen des Passworts nach dem SHA-512 Algorithmus.
     * Message Digest liefert den Algorithmus und verwandelt den String in einem Byte Array,
     * dieses wird dann zu einem 128-Zeichen Hexadezimal String verwandelt.
     * @param password das Passwort, was gehashed werden soll
     * @return das gehashte Passwort als Hex-String
     * @throws NoSuchAlgorithmException falls SHA-512 nicht unterstützt wird
     */
        public static String hashPassword(String password) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
    }


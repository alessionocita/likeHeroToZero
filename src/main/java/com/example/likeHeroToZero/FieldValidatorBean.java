/** Validator-Bean
 * Managed Controller Bean, um Felder zu validieren.
 * Arbeitet zusammen mit der RegexValidator Klasse.
 * Wird bei allen Formular-Feldern benutzt.
 */

package com.example.likeHeroToZero;

import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;



@Named
@ApplicationScoped
public class FieldValidatorBean {

    /**
     * Felder-Validierungs-Methode (ausser für Zahlenfelder)
     * Konvertiert die eingabe zu einem String,
     * ermittelt den Feld-Typ,
     * setzt einen isValid-Flag (Standard: false),
     * führt eine max-length Validierung (nicht über 100 zeichen), wirft eine Exception mit einer Fehler-Messagge wenn nicht bestanden,
     * führt eine min-length Validierung (nicht unter 2 Zeichen), wirft eine Exception mit einer Fehler-Messagge wenn nicht bestanden,
     * und ruft ansonsten die zum Feldtyp passende statische RegexValidator-Methode auf.
     * Checkt anschließend die isValid-Flag und wirft eine wirft eine Exception mit einer Fehler-Messagge wenn false.
     * @param context der Faces Context. Wird in diesem Fall nicht benötigt, nur aus Konvention eingebaut
     * @param component die zu validierende Component
     * @param value das vom Formular übergebene Wert
     * @throws ValidatorException die Exception mit der Fehler-Nachricht
     */
    public void validateField(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String field = (String) value;
        String fieldtype = (String) component.getAttributes().get("fieldtype");
        boolean isValid = false;
        if(field.length()>100) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field is too long", "Cannot be longer than 100 characters"));
        } else if(field.length()<2) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field is too short", "Cannot be shorter than 2 characters"));
        } else {
            if (fieldtype.equals("email")) {
                isValid = RegexValidator.validateEmailField(field);
            } else if(fieldtype.equals("password")) {
                isValid = RegexValidator.validatePasswordField(field);
            } else {
                isValid = RegexValidator.validateStandardField(field);
            }
            if (!isValid) {
                throw new ValidatorException(new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Wrong input format", "This field may only contain alphanumeric characters"));
            }
        }
    }

    public void validateNumberField(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        double numberField = (double) value;
        if (numberField <=0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field equal or less then 0", "The value must be higher than zero"));
        }
    }
}




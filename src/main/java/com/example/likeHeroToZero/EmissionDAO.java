/** Data Access Object für das handeln der Zugriffsoperationen durch ein EntityManager.
 * Die Bean verwaltet die CURD Operationen der Entity (EmissionEntity)
 * und wird abgerufen durch die Controller Bean "EmissionBean",
 * die für CDI und die Kommunikation mit der View sorgt.
 *
 * @author Alessio Nocita
 * @version 1.0
 *
 */

package com.example.likeHeroToZero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped              // die Logik der DAO sollte für alle Benutzer bestehen
public class EmissionDAO {

    /**
     * Das Entity Manager. Besorgt alle benötigten CURD-Methoden
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Speichert neue Emissions-Einträge in der Database.
     * @param emission der neue Emissions-Eintrag
     */
    @Transactional   // weil Write-Operation
    public void saveEmission(EmissionEntity emission) {
        em.persist(emission);
    }

    /**
     * Führt einen Update eines spezifischen Emissions-Eintrags
     * @param emission der Eintrag mit den neuen Emissions-Wert
     */
    @Transactional // weil Write-Operation
    public void changeEmission(EmissionEntity emission) {
        em.merge(emission);
    }

    /**
     * Sucht einen Emission-Eintrag nach der ID.
     * Reine Commodity-Methode. Wird aktuell von der App nicht genutzt.
     * Nicht transactional, da keine gefahr von non Repeatable Read (weil einzelne Read-Operation)
     * @param id die ID des Emission-Eintrags
     * @return der gefundene Eintrag.
     */

    public EmissionEntity findEmission(int id) {
        return em.find(EmissionEntity.class, id);
    }

    /**
     * Sucht einen Emission-Eintrag nach dem Ländernamen.
     * Falls keinen gefunden wird, so gibt die Methode null zurück.
     * Nicht transactional, da keine gefahr von non Repeatable Read (weil einzelne Read-Operation)
     * @param country der Ländername
     * @return eine einzelne Emission Entity mit dem gefundenen Eintrag (falls keinen gefunden: null).
     */

    public EmissionEntity findEmission(String country) {
        TypedQuery<EmissionEntity> query = em.createQuery("SELECT e FROM EmissionEntity e WHERE e.country = ?1", EmissionEntity.class);
        query.setParameter(1, country);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Gibt eine Liste aller Emission-Einträge zurück.
     * Nicht transactional, da keine gefahr von non Repeatable Read (weil einzelne Read-Operation).
     * Die Verwendung vom Parameter EmissionEntity.class erlaubt Type-Checking
     * @return Liste aller Emission-Einträge.
     */

    public List<EmissionEntity> getAllEmissions() {
        return em.createQuery("SELECT s FROM EmissionEntity s ORDER BY emission DESC", EmissionEntity.class).getResultList();
    }
}

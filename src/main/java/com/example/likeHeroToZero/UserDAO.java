/** Data Access Object für das handeln der Zugriffsoperationen durch ein EntityManager.
 * Die Bean verwaltet die CURD Operationen der Entity (UserEntity)
 * und wird abgerufen durch die Controller Bean "UserBean",
 * die für CDI und die Kommunikation mit der View sorgt.
 *
 * @author Alessio Nocita
 * @version 1.0
 *
 */

package com.example.likeHeroToZero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped      // die Logik der DAO sollte für alle Benutzer bestehen
public class UserDAO {

    /**
     * Das Entity Manager. Besorgt alle benötigten CURD-Methoden
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Methode zur User-Registrierung.
     * Wird verwendet, um einen neuen User und Passwort in der Datenbank einzutragen.
     * Benutzt dafür die persist() Methode des Entity Manager.
     * @param user die einzutragende User Entity
     */
    @Transactional       // weil Write-Methode
    public void registerUser(UserEntity user) {
        em.persist(user);
    }

    /**
     * Methode zum Finden eines Users nach der ID.
     * Reine Commodity-Methode. Wird durch die APP nie verwendet.
     * Benutzt die find() Methode des Entity Manager.
     * @param id die zu suchende ID.
     * @return die gefundene User Entity.
     */
    public UserEntity findUser(int id) {
        return em.find(UserEntity.class, id);
    }

    /**
     * Methode zum Finden eines Users nach dem User Name.
     * Wird beim Login verwendet.
     * Benutzt die find() Methode des Entity Manager.
     * Falls der Username nicht gefunden wird, gibt sie null zurück.
     * Die Verwendung vom Interface TypedQuery und vom Parameter UserEntity.class macht Type-Checking möglich.
     * @param userName der zu suchende Username.
     * @return die gefundene User Entity (oder null, falls keine gefunden wird)
     */
    public UserEntity findUser(String userName) {
        TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM UserEntity u WHERE u.userName = ?1", UserEntity.class);
        query.setParameter(1, userName);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

}

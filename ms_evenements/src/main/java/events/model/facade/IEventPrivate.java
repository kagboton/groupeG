package events.model.facade;

import events.model.domaine.EventPrivate;

import java.util.Optional;

public interface IEventPrivate {
    /**
     * Recuperer tous les evenement prives par pseudo de createur
     * @param pseudo
     * @return
     */
    Iterable<EventPrivate> getAllEventsByPseudo(String pseudo);

    /**
     * Recuperer un evenement prive par son ID
     * @param idEvent
     * @return
     */
    Optional<EventPrivate> getEvent(Long idEvent);

    /**
     * Mettre a jour un evenement
     * @param eventPrivate
     * @param idEvent
     */
    void updateEvent(EventPrivate eventPrivate, Long idEvent);

    /**
     * Supprimer un evenement
     * @param idEvent
     */
    void deleteEvent(Long idEvent);

    /**
     * Creer un evenement privee
     * @param eventPrivate
     * @return
     */
    EventPrivate createEvent(EventPrivate eventPrivate);
}

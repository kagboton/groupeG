package events.model.facade;

import events.model.domaine.EventPublic;
import events.model.exceptions.EventNotFoundException;

import java.io.IOException;
import java.util.List;

public interface IEventPublic {

    /**
     * Recuperer un evenement public par ID
     * @param idEvent
     * @return
     * @throws EventNotFoundException
     */
    EventPublic getEventPublicById(String idEvent) throws EventNotFoundException;

    /**
     * Recuperer des evenements publics
     * @param rows : le nombre d evenements
     * @param start : l'index de l evenement de depart
     * @return List evenements
     * @throws IOException
     * @throws EventNotFoundException
     */
    List<EventPublic> getAllPublicEvents(int rows, int start) throws IOException, EventNotFoundException;

}

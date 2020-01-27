package events.controller;

import events.model.domaine.EventPrivate;
import events.model.domaine.EventPublic;
import events.model.exceptions.EventNotFoundException;
import events.model.facade.Facade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "EventControllerAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, tags = "Gestion des évènements")
public class EventController {

    @Autowired
    private Facade facade;

    @CrossOrigin
    @ApiOperation(value = "Récupérer un evenement public")
    @GetMapping(value = "/public-events/{id}")
    public ResponseEntity<EventPublic> publicEvents(@PathVariable String id) throws EventNotFoundException {
        return ResponseEntity.ok().body(facade.getEventPublicById(id));
    }

    @CrossOrigin
    @ApiOperation(value = "Récupérer la liste de tous les évènements publics")
    @GetMapping(value = "/public-events")
    public ResponseEntity<List<EventPublic>> getPublicEvents(@RequestParam int rows, @RequestParam int start) throws IOException, EventNotFoundException {
        return ResponseEntity.ok().body(facade.getAllPublicEvents(rows, start));
    }

    @CrossOrigin
    @ApiOperation(value = "Créer un évènement privé")
    @PostMapping(value = "/private-events", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createPrivateEvents(@RequestBody EventPrivate eventPrivate) {
        EventPrivate eventPrivate1 = facade.createEvent(eventPrivate);
        if (eventPrivate1 == null)
            return ResponseEntity.noContent().build();
        else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(eventPrivate1.getIdEvent())
                    .toUri();

            return ResponseEntity.created(location).build();
        }

    }

    @CrossOrigin
    @ApiOperation(value = "Récupérer la liste des évènements privés")
    @GetMapping(value = "/private-events")
    public ResponseEntity<Iterable<EventPrivate>> getPrivateEvents(@RequestParam String pseudo) {
        return ResponseEntity.ok().body(facade.getAllEventsByPseudo(pseudo));
    }

    @CrossOrigin
    @ApiOperation(value = "Récupérer un evenement privé")
    @GetMapping(value = "/private-events/{idEvent}")
    public ResponseEntity<Optional<EventPrivate>> getPrivateEvent(@PathVariable Long idEvent) {
        return ResponseEntity.ok().body(facade.getEvent(idEvent));
    }

    @CrossOrigin
    @ApiOperation(value = "Mettre à jour un evenement privé")
    @PutMapping(value = "/private-events/{idEvent}")
    public ResponseEntity<String> updatePrivateEvent(@RequestBody EventPrivate eventPrivate, @PathVariable Long idEvent) {
        facade.updateEvent(eventPrivate, idEvent);
        return ResponseEntity.ok("Mise à jour effectuée avec succès");
    }

    @CrossOrigin
    @ApiOperation(value = "Supprimer un évènement privé")
    @DeleteMapping(value = "/private-events/{idEvent}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long idEvent) {
        facade.deleteEvent(idEvent);
        return ResponseEntity.noContent().build();
    }
}

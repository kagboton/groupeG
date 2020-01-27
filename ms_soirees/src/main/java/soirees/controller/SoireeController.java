package soirees.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import soirees.enumeration.EtatSoiree;
import soirees.facade.Facade;
import soirees.model.Commentaire;
import soirees.model.Soiree;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "SoireeControllerAPI",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,  tags = "Gestion des soirees")
public class SoireeController {
    @Autowired
    private Facade facade;

    @CrossOrigin
    @ApiOperation(value = "Creer une soiree")
    @PostMapping(value = "/soirees" ,  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity creerSoiree(@RequestBody Soiree soiree, @RequestParam String pseudo){
        Soiree soireeTmp= facade.creerSoiree(soiree, pseudo);
        if (soireeTmp == null)
            return ResponseEntity.noContent().build();
        else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(soireeTmp.getIdSoiree())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recuperer les soirees que j'ai cree")
    @GetMapping(value = "/soirees")
    public ResponseEntity<List<Soiree>> recupererMesSoirees(@RequestParam String pseudo){
        List<Soiree> soirees =  facade.mesSoirees(pseudo);
        return ResponseEntity.ok().body(soirees);
    }

    @CrossOrigin
    @ApiOperation(value = "Recuperer une soiree par identifiant")
    @GetMapping(value = "/soirees/{idSoiree}")
    public ResponseEntity<Soiree> recupererSoiree(@PathVariable Long idSoiree){
        Soiree soiree =  facade.getSoiree(idSoiree);
        return ResponseEntity.ok().body(soiree);
    }

    @CrossOrigin
    @ApiOperation(value = "Supprimer une soiree")
    @DeleteMapping(value = "/soirees/{idSoiree}")
    public ResponseEntity supprimerSoiree(@PathVariable Long idSoiree){
        boolean result =  facade.deleteSoiree(idSoiree);
        if(result == true){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Liste des soirees auxquelles je suis invite")
    @GetMapping(value = "/soirees/inv-soirees")
    public ResponseEntity<List<Soiree>> inviteAuxSoirees(@RequestParam String pseudo){
        List<Soiree> soirees = facade.inviteAuxSoirees(pseudo);
        return ResponseEntity.ok().body(soirees);
    }

    @CrossOrigin
    @ApiOperation(value = "Changer l'etat d'une soiree")
    @PutMapping(value = "/soirees/inv-soirees/{idSoiree}")
    public ResponseEntity changerEtatSoiree(@PathVariable Long idSoiree, @RequestParam EtatSoiree etatSoiree, @RequestParam String pseudo){
        facade.changerEtatSoiree(idSoiree, etatSoiree, pseudo);
        return ResponseEntity.ok("Mise à jour effectuée avec succès");
    }

    @CrossOrigin
    @ApiOperation(value = "Creer un commentaire")
    @PostMapping(value = "/soirees/{idSoiree}/Commentaires", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity creerCommentaire(@PathVariable Long idSoiree, @RequestParam String pseudo,@RequestBody Commentaire commentaire){
        Commentaire commentaireTmp= facade.ajoutCommentaire(idSoiree, pseudo, commentaire);
        if (commentaireTmp == null)
            return ResponseEntity.noContent().build();
        else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(commentaireTmp.getIdCom())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Liste des commentaire d'une soiree")
    @GetMapping(value = "/soirees/{idSoiree}/Commentaires")
    public ResponseEntity<List<Commentaire>> getCommentaires(@PathVariable Long idSoiree){
        List<Commentaire> commentaires = facade.getCommentaires(idSoiree);
        return ResponseEntity.ok().body(commentaires);
    }

    @CrossOrigin
    @ApiOperation(value = "Supprimer un commentaire")
    @DeleteMapping(value = "/soirees/{idSoiree}/Commentaires/{idCom}")
    public ResponseEntity supprimerCommentaire(@PathVariable Long idSoiree, @PathVariable Long idCom){
        facade.supprimerCommentaire(idSoiree, idCom);
        return ResponseEntity.ok().body("Commentaire supprimé avec succès");
    }

    @CrossOrigin
    @ApiOperation(value = "Supprimer un evenement prive ")
    @DeleteMapping(value = "/soirees/{idSoiree}/evenement-prive/{idEvent}")
    public ResponseEntity supprimerEvenementPrive(@PathVariable Long idSoiree, @PathVariable Long idEvent){
        boolean result = facade.supprimerPrivateEvent(idSoiree, idEvent);
        if(result == true){
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @CrossOrigin
    @ApiOperation(value = "Supprimer un evenement public ")
    @DeleteMapping(value = "/soirees/{idSoiree}/evenement-public/{idEvent}")
    public ResponseEntity supprimerEvenementPublic(@PathVariable Long idSoiree, @PathVariable String idEvent){
        boolean result = facade.supprimerPublicEvent(idSoiree, idEvent);
        if(result == true){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }


}

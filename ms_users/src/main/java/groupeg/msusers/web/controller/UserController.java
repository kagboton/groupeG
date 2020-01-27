package groupeg.msusers.web.controller;

import groupeg.msusers.modele.domain.Invitation;
import groupeg.msusers.modele.domain.User;
import groupeg.msusers.proxies.MsAuthentificationProxy;
import groupeg.msusers.service.exceptions.InvitationInexistanteException;
import groupeg.msusers.service.exceptions.UserDejaExistantException;
import groupeg.msusers.service.exceptions.UserInnexistantException;
import groupeg.msusers.service.friend_service.IFriendManager;
import groupeg.msusers.service.user_service.IUserService;
import groupeg.msusers.web.utils.Friend;
import groupeg.msusers.web.utils.InvPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@Api(value = "UserControllerAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, tags = "Gestion des utilisateurs")
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IFriendManager friendManager;

    @Autowired
    private MsAuthentificationProxy msAuthentificationProxy;


    @ApiOperation(value = "Récupérer la liste de tous les utilisateurs du système")
    @GetMapping(value = "users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @ApiOperation(value = "Incription d'un nouvel utilisateur")
    @PostMapping(value = "users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity inscription(@RequestBody User user) throws UserDejaExistantException {
        //msAuthentificationProxy.createUser(user);
        userService.signup(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{pseudo}")
                .buildAndExpand(user.getPseudo())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Modifier le profil d'un utilisateur")
    @PutMapping(value = "users/{pseudo}")
    public ResponseEntity modifierProfil(@RequestBody User user, @PathVariable("pseudo") String pseudo) throws UserInnexistantException {
        userService.updateUserInfos(pseudo, user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{pseudo}")
                .buildAndExpand(user.getPseudo())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Récupérer un utilisateur grâce à son pseudo")
    @GetMapping(value = "users/{pseudo}")
    public ResponseEntity<User> getUserByPseudo(@PathVariable("pseudo") String pseudo) throws UserInnexistantException {
        User u = userService.findUserByPseudo(pseudo).get();
        //User u = msAuthentificationProxy.getUser()
        return ResponseEntity.ok().body(u);
    }


    @ApiOperation(value = "Récupérer la liste des amis d'un utilisateur")
    @GetMapping(value = "users/{pseudo}/friends")
    public ResponseEntity<Collection<String>> getAllFriendsOfUserByPseudo(@PathVariable("pseudo") String pseudo) throws UserInnexistantException {
        return ResponseEntity.ok().body(friendManager.getAllFriendsOfUser(pseudo));
    }


    @ApiOperation(value = "Ajouter un nouvel ami dans la liste des amis d'un utilisateur ")
    @PostMapping(value = "users/{pseudo}/friends", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addNewFriendInUserFriendList(@RequestBody Friend friend) throws UserDejaExistantException, UserInnexistantException {
        friendManager.addNewFriendForUser(friend.getPseudoOfUser(), friend.getPseudoOfFriend());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{pseudo}")
                .buildAndExpand(friend.getPseudoOfFriend())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @ApiOperation(value = "Supprimer un utilisateur du système")
    @DeleteMapping(value = "users/{pseudoU}/friends/{pseudoF}")
    public ResponseEntity deleteAFriendOfUser(@RequestBody Friend friendToDel) throws UserInnexistantException {
        friendManager.deleteUserFriend(friendToDel.getPseudoOfUser(), friendToDel.getPseudoOfFriend());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ApiOperation(value = "Ajouter une invitation dans liste des invitations reçues d'un utilisateur")
    @PostMapping(value = "users/{pseudo}/invitation-in", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addInvitationReceivedInUserInbox(@PathVariable("pseudo") String pseudo, @RequestBody Invitation invitation) throws UserInnexistantException {
        friendManager.addInvitationReceivedInUserSendbox(invitation, pseudo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(invitation.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @ApiOperation(value = "Récupérer une invitation reçue")
    @GetMapping(value = "users/{pseudo}/invitation-in/{idInvR}")
    public ResponseEntity<Invitation> retreiveReceivedInvitation(@PathVariable("pseudo") String pseudo, @PathVariable("idInvR") Long idInvR) throws UserInnexistantException {
        return ResponseEntity.ok().body(friendManager.getUserOneReceivedInvitation(pseudo, idInvR));
    }

    @ApiOperation(value = "Récupérer toutes les invitations reçues")
    @GetMapping(value = "users/{pseudo}/invitation-in")
    public ResponseEntity<Collection<Invitation>> retreiveAllInvivationReceived(@PathVariable("pseudo") String pseudo) throws UserInnexistantException {
        return ResponseEntity.ok().body(friendManager.getAllReceivedInvitation(pseudo));
    }
    @CrossOrigin
    @ApiOperation(value = "Mettre à jour le statut d'une invitation reçue")
    @PutMapping(value = "users/{pseudo}/invitation-in/{idInvR}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateReceivedInvitationState(@RequestBody InvPojo invPojo, @RequestParam String invitationState) throws UserInnexistantException {
        friendManager.updateInvitationReceivedState(invPojo.getPseudo(), invPojo.getIdInv(), invitationState);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idInvR}")
                .buildAndExpand(friendManager.getUserOneReceivedInvitation(invPojo.getPseudo(), invPojo.getIdInv()).getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @ApiOperation(value = "Récupérer une invitation envoyée")
    @GetMapping(value = "users/{pseudo}/invitation-out/{idInvE}")
    public ResponseEntity<Invitation> retrieveInvitationSent(@PathVariable("pseudo") String pseudo, @PathVariable("idInvE") Long idInvE) throws InvitationInexistanteException, UserInnexistantException {
        return ResponseEntity.ok().body(friendManager.getUserOneInvitationSent(pseudo, idInvE));
    }

    @ApiOperation(value = "Récupérer toutes les invitations envoyées")
    @GetMapping(value = "users/{pseudo}/invitation-out")
    public ResponseEntity<Collection<Invitation>> retreiveAllInvivationSent(@PathVariable("pseudo") String pseudo) throws UserInnexistantException {
        return ResponseEntity.ok().body(friendManager.getAllInvitationSent(pseudo));
    }


    @ApiOperation(value = "Ajouter une invitation dans liste des invitations envoyées d'un utilisateur")
    @PostMapping(value = "users/{pseudo}/invitation-out", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addNewInvitationInOutbox(@PathVariable("pseudo") String pseudo, @RequestBody Invitation invitation) throws UserInnexistantException {
        friendManager.addInvitationSentInUserOutbox(invitation, pseudo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idInv}")
                .buildAndExpand(invitation.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Mettre à jour le statut d'une invitation envoyée")
    @PutMapping(value = "users/{pseudo}/invitation-out/{idInvE}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateSentInvitationState(@RequestBody InvPojo invPojo, @RequestParam String invitationState) throws InvitationInexistanteException, UserInnexistantException {
        friendManager.updateInvitationSentState(invPojo.getPseudo(), invPojo.getIdInv(), invitationState);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idInvR}")
                .buildAndExpand(friendManager.getUserOneInvitationSent(invPojo.getPseudo(), invPojo.getIdInv()).getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}

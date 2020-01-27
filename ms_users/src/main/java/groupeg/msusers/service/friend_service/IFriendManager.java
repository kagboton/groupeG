package groupeg.msusers.service.friend_service;

import groupeg.msusers.modele.domain.Invitation;
import groupeg.msusers.service.exceptions.InvitationInexistanteException;
import groupeg.msusers.service.exceptions.UserDejaExistantException;
import groupeg.msusers.service.exceptions.UserInnexistantException;

import java.util.Collection;


public interface IFriendManager {


    Collection<String> getAllFriendsOfUser(String userPseudo) throws UserInnexistantException;

    String addNewFriendForUser(String userPseudo, String newFriend) throws UserInnexistantException, UserDejaExistantException;

    void deleteUserFriend(String userPseudo, String friendPseudo) throws UserInnexistantException;

    Invitation getAnyInvitationById(Long idInv);

    Collection<Invitation> getAllReceivedInvitation(String userPseudo) throws UserInnexistantException;

    Invitation getUserOneReceivedInvitation(String userPseudo, Long invitationID) throws UserInnexistantException;

    void updateInvitationReceivedState(String userPseudo, Long invitationID, String invitationState) throws UserInnexistantException;

    Collection<Invitation> getAllInvitationSent(String userPseudo) throws UserInnexistantException;

    void addInvitationSentInUserOutbox(Invitation invitation, String userPseudo) throws UserInnexistantException;

    void addInvitationReceivedInUserSendbox(Invitation invitation, String userPseudo) throws UserInnexistantException;

    Invitation getUserOneInvitationSent(String userPseudo, Long invitationID) throws InvitationInexistanteException, UserInnexistantException;

    void updateInvitationSentState(String userPseudo, Long invitationID, String invitationState) throws InvitationInexistanteException, UserInnexistantException;


}

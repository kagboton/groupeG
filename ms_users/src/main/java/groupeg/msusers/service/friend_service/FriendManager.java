package groupeg.msusers.service.friend_service;

import groupeg.msusers.modele.domain.Invitation;
import groupeg.msusers.modele.domain.User;
import groupeg.msusers.repository.InvitationRepository;
import groupeg.msusers.repository.UserRepository;
import groupeg.msusers.service.exceptions.InvitationInexistanteException;
import groupeg.msusers.service.exceptions.UserDejaExistantException;
import groupeg.msusers.service.exceptions.UserInnexistantException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FriendManager implements IFriendManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationRepository invitationRepository;



    @Override
    public Collection<String> getAllFriendsOfUser(String userPseudo) throws UserInnexistantException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo))
                .orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));
        return optionalUser.get().getFriendList();
    }

    @Override
    public String addNewFriendForUser(String userPseudo, String newFriend) throws UserInnexistantException, UserDejaExistantException {
        Optional<User> userRequesting = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo))
                .orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));
        Optional<User> friend = Optional.ofNullable(userRepository.findUserByPseudo(newFriend))
                .orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));
        if (userRequesting.get().getFriendList().contains(newFriend)) {
            throw new UserDejaExistantException("Cet utilisateur est déjà dans votre liste d'amis !");

        } else {
            userRequesting.get().addFriend(friend.get().getPseudo());
            //friend.get().addFriend(userPseudo);
            userRepository.save(userRequesting.get());
            //userRepository.save(friend.get());
            return userRepository.findUserByPseudo(newFriend).get().getPseudo();

        }
    }


    @Override
    public void addInvitationReceivedInUserSendbox(Invitation invitation, String userPseudo) throws UserInnexistantException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo))
                .orElseThrow(() -> new UserInnexistantException("Cet utilisateur n'existe pas !"));
        invitationRepository.save(invitation);
        userOptional.get().addInvitationReceived(invitationRepository.findById(invitation.getId()).get().getId());
        userRepository.save(userOptional.get());
    }

    @Override
    public Collection<Invitation> getAllReceivedInvitation(String userPseudo) throws UserInnexistantException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo))
                .orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));

        Collection<Long> invListTypeLong = userOptional.get().getInvitationReceived();

        return invListTypeLong.stream().map(l -> getAnyInvitationById(l)).collect(Collectors.toSet());

    }

    @Override
    public Invitation getUserOneReceivedInvitation(String userPseudo, Long invitationID) throws UserInnexistantException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo))
                .orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));

        Invitation optionalInvitation = invitationRepository.findById(invitationID).get();

        Long inv = userOptional.get().getInvitationReceived()
                .stream().filter(invitation -> invitation == optionalInvitation.getId()).findFirst().get();

        return getAnyInvitationById(inv);
    }


    @Override
        public void updateInvitationReceivedState(String userPseudo, Long invitationID, String invitationState) throws UserInnexistantException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo)).orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));


        Invitation inv = invitationRepository.findById(invitationID).get(); // getUserOneReceivedInvitation(userOptional.get().getPseudo(), invitationID);
        inv.setInvitationState(invitationState);
        invitationRepository.save(inv);
        userRepository.save(userOptional.get());
    }

    @Override
    public Collection<Invitation> getAllInvitationSent(String userPseudo) throws UserInnexistantException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo)).orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));
        Collection<Long> invListTypeLong = userOptional.get().getInvitationSent();

        return invListTypeLong.stream().map(l -> getAnyInvitationById(l)).collect(Collectors.toSet());
    }

    @Override
    public Invitation getUserOneInvitationSent(String userPseudo, Long invitationID) throws InvitationInexistanteException, UserInnexistantException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo))
                .orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));

//        Invitation optionalInvitation = Optional.ofNullable(invitationRepository.findById(invitationID).get())
//                .orElseThrow(() -> new InvitationInexistanteException("L'invitation n'existe pas dans notre sytème."));


        Invitation optionalInvitation = invitationRepository.findById(invitationID).get();

        Long invitation = userOptional.get().getInvitationSent()
                .stream().filter(i -> i == optionalInvitation.getId()).findFirst().get();

        return getAnyInvitationById(invitation);
    }

    @Override
    public void addInvitationSentInUserOutbox(Invitation invitation, String userPseudo) throws UserInnexistantException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo))
                .orElseThrow(() -> new UserInnexistantException("L'utilisateur n'existe pas !"));
        invitationRepository.save(invitation);
        userOptional.get().addInvitationSent(getAnyInvitationById(invitation.getId()).getId());
        userRepository.save(userOptional.get());
    }

    @Override
    public void updateInvitationSentState(String userPseudo, Long invitationID, String invitationState) throws InvitationInexistanteException, UserInnexistantException {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo)).orElseThrow(() -> new UserInnexistantException("Cet utilisateur n'existe pas !"));


        Invitation invitationSent = invitationRepository.findById(invitationID).get();//getUserOneInvitationSent(user.get().getPseudo(), invitationID);
        invitationSent.setInvitationState(invitationState);
        invitationRepository.save(invitationSent);
        userRepository.save(user.get());
    }

    @Override
    public void deleteUserFriend(String userPseudo, String friendPseudo) throws UserInnexistantException {
        Optional<User> originUser = Optional.ofNullable(userRepository.findUserByPseudo(userPseudo)).orElseThrow(UserInnexistantException::new);
        Optional<User> friendUser = Optional.ofNullable(userRepository.findUserByPseudo(friendPseudo)).orElseThrow(UserInnexistantException::new);
        //if (!originUser.get().getFriendList().contains(friendUser.get())){
        // throw new UserInnexistantException("Vous n'êtes pas amis, la suppression ne peut se faire");
        //}else{
        originUser.get().getFriendList().remove(friendUser.get().getPseudo());
        userRepository.save(originUser.get());
        // }
    }

    @Override
    public Invitation getAnyInvitationById(Long idInv) {
        return invitationRepository.findById(idInv).get();
    }
}

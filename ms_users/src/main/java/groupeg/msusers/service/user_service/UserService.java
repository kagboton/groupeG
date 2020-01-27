package groupeg.msusers.service.user_service;

import groupeg.msusers.modele.domain.User;
import groupeg.msusers.repository.UserRepository;
import groupeg.msusers.service.exceptions.UserDejaExistantException;
import groupeg.msusers.service.exceptions.UserInnexistantException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserRepository repository;

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<User> signup(User u) throws UserDejaExistantException{
        Optional<User> existingUser = repository.findUserByPseudo(u.getPseudo());
        if(existingUser.isPresent()) throw  new UserDejaExistantException("Pseudo déjà utilisé ! Choisissez un autre");
        repository.save(u);
        logger.info("un nouvel utilisateur vient d'être créé : " + u.getPseudo());

        return repository.findUserByPseudo(u.getPseudo());

    }

    @Override
    public Optional<User> findUserByPseudo(String pseudo) throws UserInnexistantException {
        return Optional.ofNullable(
                repository.findUserByPseudo(pseudo))
                .orElseThrow(() -> new UserInnexistantException("Utilisateur innexistant"));
    }

    @Override
    public void updateUserInfos(String pseudo, User u) throws UserInnexistantException {
        Optional<User> userSearched = Optional.ofNullable(repository.findUserByPseudo(pseudo)).orElseThrow(() -> new UserInnexistantException("Utilisateur innexistant"));

        if (!userSearched.isPresent())
            throw new UserInnexistantException("L'utilisateur n'existe pas dans notre système");

        userSearched.get().setNom(u.getNom());
        userSearched.get().setPrenom(u.getPrenom());
        userSearched.get().setDateDeNaissance(u.getDateDeNaissance());
        userSearched.get().setPhoto(u.getPhoto());
        userSearched.get().setEmail(u.getEmail());
        userSearched.get().setPasswd(u.getPasswd());

        repository.save(userSearched.get());

        logger.debug("Les changements de l'utilisateur ont été sauvegardés", pseudo);
    }


    @Override
    public void deleteByPseudo(String pseudo) throws UserInnexistantException {
        Optional<User> existing = repository.findUserByPseudo(pseudo);
        if (!existing.isPresent())
            throw new UserInnexistantException("Utilisateur innexistant, impossible d'effectuer l'action de suppression");
        repository.delete(existing.get());
        logger.debug("L'utilisateur a bien été supprimé", pseudo);
    }
}

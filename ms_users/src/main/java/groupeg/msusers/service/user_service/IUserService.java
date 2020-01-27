package groupeg.msusers.service.user_service;

import groupeg.msusers.modele.domain.User;
import groupeg.msusers.service.exceptions.*;

import java.util.Optional;


public interface IUserService {

    /**
     * Récupérer tous les utilisateurs
     * @return une collection de tous les utilisateurs de la base de données
     */
    Iterable<User> getAllUsers();


    /**
     * Inscription d'un nouvelle utilisateir
     * @param u
     * @return l'utilisateur créé et le connecte directement (on garde l'utilisateur sur notre site
     * @throws UserDejaExistantException
     */
    Optional<User> signup(User u) throws UserDejaExistantException;

    /**
     * Modifier le profil d'un utilisateur
     * @param pseudo
     * @param u
     * @throws UserInnexistantException
     */
    void updateUserInfos(String pseudo, User u) throws UserInnexistantException;


    /**
     * Trouver un utilisateur par son pseudo
     * @param pseudo
     * @return une instance User
     */
    Optional<User> findUserByPseudo(String pseudo) throws UserInnexistantException;


    /**
     * Supprimer un compte utilisateur
     * @param pseudo
     * @throws UserInnexistantException
     */
    void deleteByPseudo(String pseudo) throws UserInnexistantException;
}

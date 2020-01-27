package soirees.facade;

import soirees.model.Commentaire;
import soirees.enumeration.EtatSoiree;
import soirees.model.Soiree;

import java.util.List;

public interface ISoiree {

    /**
     *  Creer une soiree par un utilisateur
     * @param soiree
     * @param pseudo
     * @return Soiree
     */
    Soiree creerSoiree(Soiree soiree, String pseudo);

    /**
     * Recupérer la liste des soirees que j'ai cree
     * @param pseudo
     * @return la liste de mes soirees
     */
    List<Soiree> mesSoirees(String pseudo);

    /**
     * Recuperer une soiree
     * @param idSoiree
     * @return Soiree
     */
    Soiree getSoiree(Long idSoiree);

    /**
     * Supprimer soiree
     * @param idSoiree
     */
    boolean deleteSoiree(Long idSoiree);


    /**
     * La liste des soirees  auxquelles je suis invite
     * @param pseudo
     * @return Liste soiree
     */
    List<Soiree> inviteAuxSoirees(String pseudo);


    /**
     * Changer l'etat d'une soiree
     * @param etatSoiree
     */
    void changerEtatSoiree(Long idSoiree, EtatSoiree etatSoiree, String pseudo);

    /**
     * ajouter un commentaire
     * @param idSoiree
     * @param pseudo
     * @param Commentaire
     * @return Commentaire
     */
    Commentaire ajoutCommentaire(Long idSoiree, String pseudo, Commentaire commentaire);

    /**
     * Recuperer les commentaire d'une soiree
     * @param idSoiree
     * @return list des commentaires
     */
    List<Commentaire> getCommentaires(Long idSoiree);

    /**
     * Supprimer une commentaire d'une Soiree
     * note : le commentaire est supprime par son proprietaire
     * @param idSoiree
     * @param idCommentaire
     */
    void supprimerCommentaire(Long idSoiree, Long idCommentaire);

    /**
     * Supprimer un evenement prive d'une soiree
     * @param idsoiree
     * @param idEvent
      * @return confirmation
     */
    boolean supprimerPrivateEvent(Long idsoiree, Long idEvent);


    /**
     * Supprimer un evenement public d'une soiree
     * @param idsoiree
     * @param idEvent
     * @return confirmation
     */
    boolean supprimerPublicEvent(Long idsoiree, String idEvent);

}

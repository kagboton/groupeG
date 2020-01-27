package soirees.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soirees.repository.CommentaireRepository;
import soirees.repository.EtatUtilisateurRepository;
import soirees.repository.SoireeRepository;
import soirees.enumeration.EtatSoiree;
import soirees.model.Commentaire;
import soirees.model.EtatUtilisateur;
import soirees.model.Soiree;

import java.util.ArrayList;
import java.util.List;

@Service
public class Facade  implements ISoiree{
    @Autowired
    private SoireeRepository soireeRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private EtatUtilisateurRepository etatUtilisateurRepository;

    @Override
    public Soiree creerSoiree(Soiree soiree, String pseudo) {
        soiree.setPseudoCreateur(pseudo);
        Soiree soireeTmp = soireeRepository.save(soiree);
        commentaireRepository.saveAll(soiree.getCommentaires());
        etatUtilisateurRepository.saveAll(soiree.getEtatUtilisateur());
        return soireeTmp;
    }

    @Override
    public List<Soiree> mesSoirees(String pseudo) {
        return soireeRepository.findSoireesByPseudoCreateur(pseudo);
    }

    @Override
    public Soiree getSoiree(Long idSoiree) {
        return soireeRepository.findById(idSoiree).get();
    }

    @Override
    public boolean deleteSoiree(Long idSoiree) {
        Iterable<Soiree> soirees  = soireeRepository.findAll();
        for (Soiree soiree:soirees){
            if(soiree.getIdSoiree() == idSoiree){
                soireeRepository.deleteById(idSoiree);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Soiree> inviteAuxSoirees(String pseudo) {
         Iterable<Soiree> soireeIterable =  soireeRepository.findAll();
        List<Soiree> soirees = new ArrayList<>();
        List<EtatUtilisateur> userState = null;
        for (Soiree soiree:
                soireeIterable) {
            userState =  soiree.getEtatUtilisateur();
            for (EtatUtilisateur user : userState){
                if(user.getPseudo().equals(pseudo)){
                    soirees.add(soiree);
                    break;
                }
            }
        }
        return soirees;
    }

    @Override
    public void changerEtatSoiree(Long idSoiree, EtatSoiree etatSoiree, String pseudo) {
        Soiree soiree = soireeRepository.findById(idSoiree).get();
        List<EtatUtilisateur> userState =  soiree.getEtatUtilisateur();
        for (EtatUtilisateur user : userState){
            if(user.getPseudo().equals(pseudo)){
                user.setEtatSoiree(etatSoiree);
                break;
            }
        }
        soireeRepository.save(soiree);
    }

    @Override
    public Commentaire ajoutCommentaire(Long idSoiree, String pseudo, Commentaire commentaire) {
        Soiree soiree = soireeRepository.findById(idSoiree).get();
        commentaire.setPseudo(pseudo);
        commentaire.setSoiree(soiree);
        soiree.getCommentaires().add(commentaire);
        soireeRepository.save(soiree);
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> getCommentaires(Long idSoiree) {
        return commentaireRepository.findAllBySoireeIdSoiree(idSoiree);
    }

    @Override
    public void supprimerCommentaire(Long idSoiree, Long idCommentaire) {
        Soiree soiree =  soireeRepository.findById(idSoiree).get();
        Commentaire commentaire =  commentaireRepository.findById(idCommentaire).get();
        soiree.getCommentaires().remove(commentaire);
        soireeRepository.save(soiree);
        commentaireRepository.delete(commentaire);
    }


    @Override
    public boolean supprimerPrivateEvent(Long idsoiree, Long idEvent) {
        Soiree soiree =  soireeRepository.findById(idsoiree).get();
        if(soiree.getIdPrivateEvents().contains(idEvent)){
            soiree.getIdPrivateEvents().remove(idEvent);
            soireeRepository.save(soiree);
            return true;
        }
        return false;
    }

    @Override
    public boolean supprimerPublicEvent(Long idsoiree, String idEvent) {
        Soiree soiree =  soireeRepository.findById(idsoiree).get();
        if(soiree.getIdPublicEvents().contains(idEvent)){
            soiree.getIdPublicEvents().remove(idEvent);
            soireeRepository.save(soiree);
            return true;
        }
        return false;
    }


}

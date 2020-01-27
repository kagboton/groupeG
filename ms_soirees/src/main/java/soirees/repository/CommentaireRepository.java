package soirees.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import soirees.model.Commentaire;

import java.util.List;
@Repository
public interface CommentaireRepository extends CrudRepository<Commentaire, Long> {

    List<Commentaire> findAllBySoireeIdSoiree(Long idSoiree);
}

package soirees.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import soirees.model.Soiree;

import java.util.List;

@Repository
public interface SoireeRepository  extends CrudRepository<Soiree, Long> {
    List<Soiree> findSoireesByPseudoCreateur(String pseudo);
}

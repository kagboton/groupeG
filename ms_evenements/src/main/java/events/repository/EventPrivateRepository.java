package events.repository;

import events.model.domaine.EventPrivate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventPrivateRepository extends CrudRepository<EventPrivate, Long> {
    Iterable<EventPrivate> findEventPrivatesByPseudo(String pseudo);
    void deleteById(Long aLong);
}

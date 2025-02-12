package groupeg.msusers.repository;

import groupeg.msusers.modele.domain.Invitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Long> {
}

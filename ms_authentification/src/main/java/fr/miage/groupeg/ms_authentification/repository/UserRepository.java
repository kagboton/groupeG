package fr.miage.groupeg.ms_authentification.repository;

import fr.miage.groupeg.ms_authentification.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}

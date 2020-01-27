package fr.miage.groupeg.ms_authentification.service;

import fr.miage.groupeg.ms_authentification.model.User;
import fr.miage.groupeg.ms_authentification.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Override
    public void create(User user) {

        Optional<User> existing = repository.findById(user.getPseudo());
        existing.ifPresent(it-> {throw new IllegalArgumentException("L'utilisateur existe déjà: " + it.getUsername());});
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        repository.save(user);

        logger.info("Un nouvel utilisateur vient d'êre créé: {}", user.getUsername());
    }
}

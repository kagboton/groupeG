package fr.miage.groupeg.ms_authentification.service.security;

import fr.miage.groupeg.ms_authentification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DataBaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        return repository.findById(pseudo).orElseThrow(()->new UsernameNotFoundException(pseudo));
    }
}

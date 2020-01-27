package groupeg.msusers.proxies;

import groupeg.msusers.modele.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@FeignClient(name = "gateway",decode404 = true)
public interface MsAuthentificationProxy {

    @PostMapping(value = "ms_authentification/uaa/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createUser(User user);

    @GetMapping(value = "ms_authentification/uaa/users/current")
    Principal getUser(Principal principal);

}

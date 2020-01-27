package groupeg.msusers.web.utils;

import javax.validation.constraints.NotNull;

public class Login {

    @NotNull
    private String pseudo;
    @NotNull
    private  String password;

    public Login() {
    }

    public Login(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package groupeg.msusers.web.utils;

public class InvPojo {

    private String pseudo;
    private Long idInv;

    public InvPojo(String pseudo, Long idInv) {
        this.pseudo = pseudo;
        this.idInv = idInv;
    }

    public InvPojo() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public Long getIdInv() {
        return idInv;
    }
}

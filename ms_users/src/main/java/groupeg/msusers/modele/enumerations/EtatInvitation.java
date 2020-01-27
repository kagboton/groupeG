package groupeg.msusers.modele.enumerations;

public enum EtatInvitation {
    VIDE("Pas d'invitation(s)"),
    ACCEPTE("Vous êtes maintenant amis"),
    ENVOYE("En attente de confirmation"),
    ANNULER("Invitation(s) annulée(s)");

    private String val;

    EtatInvitation(String val) {
        this.val = val;
    }

    public String val() {
        return val;
    }
}

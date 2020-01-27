package groupeg.msusers.modele.enumerations;

public enum Situation {
    CELIBATAIRE("Célibataire"),
    COUPLE("En couple"),
    INCONNU("Inconnu");


    private String value;

    Situation(String value) {
        this.value = value;
    }

    public String value(){
        return value;
    }
}

package groupeg.msusers.modele.enumerations;

public enum Sexe {
    HOMME("Homme"),
    FEMME("Femme"),
    INCONNU("Inconnu");

    private String value;

    Sexe(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }

}

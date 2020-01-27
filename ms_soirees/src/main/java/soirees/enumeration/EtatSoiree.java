package soirees.enumeration;

public enum EtatSoiree {
    ACCEPTED("Soiree acceptee"),
    REFUSED("Soiree refusee"),
    STAND_BY("Soiree en attente");

    String str ;
    EtatSoiree(String str){
        this.str = str;
    }

}

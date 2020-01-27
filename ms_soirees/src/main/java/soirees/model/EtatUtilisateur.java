package soirees.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import soirees.enumeration.EtatSoiree;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class EtatUtilisateur implements Serializable{
    @Id
    @GeneratedValue
    private Long idEtat;

    private String pseudo;

    private EtatSoiree etatSoiree;

    @JsonIgnore
    @ManyToOne
    private Soiree soiree;

    public EtatUtilisateur(String pseudo, EtatSoiree etatSoiree, Soiree soiree) {
        this.pseudo = pseudo;
        this.etatSoiree = etatSoiree;
        this.soiree = soiree;
    }

    public EtatUtilisateur() {

    }

    public Long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Long idEtat) {
        this.idEtat = idEtat;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public EtatSoiree getEtatSoiree() {
        return etatSoiree;
    }

    public void setEtatSoiree(EtatSoiree etatSoiree) {
        this.etatSoiree = etatSoiree;
    }

    public Soiree getSoiree() {
        return soiree;
    }

    public void setSoiree(Soiree soiree) {
        this.soiree = soiree;
    }
}

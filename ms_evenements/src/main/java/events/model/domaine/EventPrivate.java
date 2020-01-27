package events.model.domaine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;



@Entity
public class EventPrivate implements Serializable {

    @Id
    @GeneratedValue
    private Long idEvent;

    private String pseudo;

    private String titre;

    private String tarification;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateDebut;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateFin;

    private String Ville;

    private String lieuEvent;

    private String adresse;
    private String tags;
    private String description;

    public EventPrivate(String pseudo, String titre, String tarification, LocalDate dateDebut, LocalDate dateFin, String ville, String lieuEvent, String adresse, String tags, String description) {
        this.pseudo = pseudo;
        this.titre = titre;
        this.tarification = tarification;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.Ville = ville;
        this.lieuEvent = lieuEvent;
        this.adresse = adresse;
        this.tags = tags;
        this.description = description;
    }

    public EventPrivate() {

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTarification() {
        return tarification;
    }

    public void setTarification(String tarification) {
        this.tarification = tarification;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

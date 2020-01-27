package soirees.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import soirees.enumeration.EtatSoiree;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Soiree implements Serializable{
    @Id
    @GeneratedValue
    private Long idSoiree;
    private String pseudoCreateur;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;

    @ElementCollection
    private List<String> idPublicEvents;

    @ElementCollection
    private List<Long> idPrivateEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "soiree", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}) @JsonManagedReference
    private List<EtatUtilisateur> EtatUtilisateur;
    private int nbrPlaces;

    @JsonIgnore
    @OneToMany(mappedBy = "soiree",cascade = {CascadeType.ALL}) @JsonManagedReference
    private List<Commentaire> commentaires;

    public Soiree(String pseudoCreateur, String description, LocalDateTime dateTime, List<String> idPublicEvents, List<Long> idPrivateEvents, List<soirees.model.EtatUtilisateur> etatUtilisateur, int nbrPlaces, List<Commentaire> commentaires) {
        this.pseudoCreateur = pseudoCreateur;
        this.description = description;
        this.dateTime = dateTime;
        this.idPublicEvents = idPublicEvents;
        this.idPrivateEvents = idPrivateEvents;
        EtatUtilisateur = etatUtilisateur;
        this.nbrPlaces = nbrPlaces;
        this.commentaires = commentaires;
    }

    public Soiree() {

    }

    public List<EtatUtilisateur> getEtatUtilisateur() {
        return EtatUtilisateur;
    }

    public void setEtatUtilisateur(List<EtatUtilisateur> etatUtilisateur) {
        EtatUtilisateur = etatUtilisateur;
    }

    public Long getIdSoiree() {
        return idSoiree;
    }

    public void setIdSoiree(Long idSoiree) {
        this.idSoiree = idSoiree;
    }

    public String getPseudoCreateur() {
        return pseudoCreateur;
    }

    public void setPseudoCreateur(String pseudoCreateur) {
        this.pseudoCreateur = pseudoCreateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<String> getIdPublicEvents() {
        return idPublicEvents;
    }

    public void setIdPublicEvents(List<String> idPublicEvents) {
        this.idPublicEvents = idPublicEvents;
    }

    public List<Long> getIdPrivateEvents() {
        return idPrivateEvents;
    }

    public void setIdPrivateEvents(List<Long> idPrivateEvents) {
        this.idPrivateEvents = idPrivateEvents;
    }
}

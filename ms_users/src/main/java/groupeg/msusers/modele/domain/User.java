package groupeg.msusers.modele.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import groupeg.msusers.modele.enumerations.Sexe;
import groupeg.msusers.modele.enumerations.Situation;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Data
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "pseudo",unique=true,columnDefinition="VARCHAR(64)")
    private String pseudo;

    @NotEmpty
    private String passwd;

    private String nom;

    private String prenom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateDeNaissance;

    private String sexe;

    private String email;

    private String telephone;

    private String photo;

    private String situation;

    @JsonIgnore
    @ElementCollection
    private Collection<String> friendList;

    @JsonIgnore
    @ElementCollection
    private Collection<Long> invitationSent;

    @JsonIgnore
    @ElementCollection
    private Collection<Long> invitationReceived;


    public User(String pseudo, @NotEmpty String passwd, String prenom,
                String nom, String email, String telephone, String photo,
                LocalDate dateDeNaissance) {
        this.pseudo = pseudo;
        this.passwd = passwd;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.dateDeNaissance = dateDeNaissance;
        this.sexe = Sexe.INCONNU.value();
        this.photo = photo;
        this.situation = Situation.INCONNU.value();
        this.friendList = new ArrayList<>();
        this.invitationReceived = new ArrayList<>();
        this.invitationReceived = new ArrayList<>();
    }

    public User() {
        this.sexe = Sexe.INCONNU.value();
        this.situation = Situation.INCONNU.value();
        this.friendList = new ArrayList<>();
        this.invitationReceived = new HashSet<>();
        this.invitationReceived = new HashSet<>();
    }

    public void addFriend(String newFriend) {
        getFriendList().add(newFriend);
    }

    public void addInvitationSent(Long invitationSent) {
        getInvitationSent().add(invitationSent);
    }

    public void addInvitationReceived(Long invitationReceived) {
        getInvitationReceived().add(invitationReceived);
    }


    public Collection<String> getFriendList() {
        return friendList;
    }

    public Collection<Long> getInvitationSent() {
        return invitationSent;
    }

    public Collection<Long> getInvitationReceived() {
        return invitationReceived;
    }
}

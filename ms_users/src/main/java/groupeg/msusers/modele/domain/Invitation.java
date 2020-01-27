package groupeg.msusers.modele.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import groupeg.msusers.modele.enumerations.EtatInvitation;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "invitation")
public class Invitation implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateInvitation;

    private String userSendingInvitation; //personne qui envoie l'invitation

    private String userReceivingInvitation; //personne invitée

    private String invitationState; //état de l'invitation

    public Invitation(LocalDate dateInvitation, String userSendingInvitation, String userReceivingInvitation, String invitationState) {
        this.dateInvitation = dateInvitation;
        this.userSendingInvitation = userSendingInvitation;
        this.userReceivingInvitation = userReceivingInvitation;
        this.invitationState = EtatInvitation.VIDE.val();
    }

    public Invitation() {
        this.invitationState = EtatInvitation.VIDE.val();
    }
}

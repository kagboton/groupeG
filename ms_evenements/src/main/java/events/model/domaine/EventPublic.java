package events.model.domaine;

import java.io.Serializable;
import java.time.LocalDate;

public class EventPublic implements Serializable {
    private String idEvent;
    private String titre;
    private String nomLieu;
    private String tarification;
    private String imageUrl;
    private LocalDate dateDebut;
    private String espaceTemps;
    private String departement;
    private String Ville;
    private String lienEvent;
    private String textLibre;
    private String adresse;
    private String region;
    private LocalDate dateFin;
    private String tags;
    private String description;

    public EventPublic(String idEvent, String titre, String nomLieu, String tarification, String imageUrl, LocalDate dateDebut, String espaceTemps, String departement, String ville, String lienEvent, String textLibre, String adresse, String region, LocalDate dateFin, String tags, String description) {
        this.idEvent = idEvent;
        this.titre = titre;
        this.nomLieu = nomLieu;
        this.tarification = tarification;
        this.imageUrl = imageUrl;
        this.dateDebut = dateDebut;
        this.espaceTemps = espaceTemps;
        this.departement = departement;
        Ville = ville;
        this.lienEvent = lienEvent;
        this.textLibre = textLibre;
        this.adresse = adresse;
        this.region = region;
        this.dateFin = dateFin;
        this.tags = tags;
        this.description = description;


    }

    public EventPublic() {

    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public String getTarification() {
        return tarification;
    }

    public void setTarification(String tarification) {
        this.tarification = tarification;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getEspaceTemps() {
        return espaceTemps;
    }

    public void setEspaceTemps(String espaceTemps) {
        this.espaceTemps = espaceTemps;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getLienEvent() {
        return lienEvent;
    }

    public void setLienEvent(String lienEvent) {
        this.lienEvent = lienEvent;
    }

    public String getTextLibre() {
        return textLibre;
    }

    public void setTextLibre(String textLibre) {
        this.textLibre = textLibre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
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

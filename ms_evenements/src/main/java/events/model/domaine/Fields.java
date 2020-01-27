package events.model.domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;

public class Fields implements Serializable {

    @JsonIgnore
    private ArrayList<Object> latlon = new ArrayList<Object>();
    private String lang;
    private String title;
    private String uid;
    private String placename;
    private String pricing_info;
    private String image;
    private String date_start;
    private String updated_at;
    private String space_time_info;
    private String department;
    private String city;
    private String link;
    private String free_text;
    private String address;
    private String timetable;
    private String image_thumb;
    private String region;
    private String date_end;
    private String tags;
    private String description;


    // Getter Methods
    public ArrayList<Object> getLatlon() {
        return latlon;
    }

    public void setLatlon(ArrayList<Object> latlon) {
        this.latlon = latlon;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getPricing_info() {
        return pricing_info;
    }

    public void setPricing_info(String pricing_info) {
        this.pricing_info = pricing_info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getSpace_time_info() {
        return space_time_info;
    }

    public void setSpace_time_info(String space_time_info) {
        this.space_time_info = space_time_info;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Setter Methods

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFree_text() {
        return free_text;
    }

    public void setFree_text(String free_text) {
        this.free_text = free_text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
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
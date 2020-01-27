package events.model.domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Codebeautify implements Serializable {

    @JsonIgnore
    Geometry GeometryObject;


    private String datasetid;

    private String recordid;

    private Fields fields;

    @JsonIgnore
    private String record_timestamp;

    // Getter Methods
    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public Fields getFields() {
        return fields;
    }

    // Setter Methods

    public void setFields(Fields fieldsObject) {
        this.fields = fieldsObject;
    }

    public Geometry getGeometry() {
        return GeometryObject;
    }

    public void setGeometry(Geometry geometryObject) {
        this.GeometryObject = geometryObject;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(String record_timestamp) {
        this.record_timestamp = record_timestamp;
    }
}



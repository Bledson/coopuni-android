package br.edu.ufrn.imd.coopuni.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by andreza on 29/11/15.
 */



public class Geolocation implements Serializable {

    private long id;

    private Float latitude;

    private Float longitude;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }


}
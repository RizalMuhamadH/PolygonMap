package com.inarts.inarts_user.mymap;

/**
 * Created by inarts-user on 9/28/16.
 */

public class LongLat {
    private String longitude;
    private String latitude;

    public LongLat(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public LongLat() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}

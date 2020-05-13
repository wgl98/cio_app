package com.cio_app.model;

import com.amap.api.services.core.LatLonPoint;

public class SiteBean {

    private String name;
    private String location;
    private LatLonPoint latLonPoint;

    public SiteBean(String name, String location, LatLonPoint latLonPoint){
        this.name = name;
        this.location = location;
        this.latLonPoint = latLonPoint;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LatLonPoint getLatLonPoint() {
        return latLonPoint;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.latLonPoint = latLonPoint;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

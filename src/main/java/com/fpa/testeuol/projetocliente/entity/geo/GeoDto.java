package com.fpa.testeuol.projetocliente.entity.geo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoDto {

    private String ipv4;

    @JsonProperty("continent_name")
    private String continentName;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("subdivision_1_name")
    private String subdivisionName1;
    @JsonProperty("subdivision_2_name")
    private String subdivisionName2;
    @JsonProperty("city_name")
    private String cityName;
    private String latitude;
    private String longitude;

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSubdivisionName1() {
        return subdivisionName1;
    }

    public void setSubdivisionName1(String subdivisionName1) {
        this.subdivisionName1 = subdivisionName1;
    }

    public String getSubdivisionName2() {
        return subdivisionName2;
    }

    public void setSubdivisionName2(String subdivisionName2) {
        this.subdivisionName2 = subdivisionName2;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

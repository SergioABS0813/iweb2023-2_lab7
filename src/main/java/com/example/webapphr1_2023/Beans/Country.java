package com.example.webapphr1_2023.Beans;

public class Country {
    // Creamos nuestro Bean de country para poder relacionarlo con nuestra tabla de Location y extraer los nombres
    private String countryId;
    private String countryName;
    private int regionId;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}

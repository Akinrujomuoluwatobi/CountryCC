package com.example.progtobi.countrycc;

/**
 * Created by PROG. TOBI on 14-Jun-16.
 */
public class ModelClass {
    private String CountryName, CallCode;
    private int Id;

    public ModelClass() {
    }

    public ModelClass(String countryName, String callCode) {
        CountryName = countryName;
        CallCode = callCode;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCountryName() {

        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCallCode() {
        return CallCode;
    }

    public void setCallCode(String callCode) {
        CallCode = callCode;
    }

    @Override
    public String toString() {
        return "ModelClass{" +
                "CountryName='" + CountryName + '\'' +
                ", CallCode='" + CallCode + '\'' +
                ", Id=" + Id +
                '}';
    }
}

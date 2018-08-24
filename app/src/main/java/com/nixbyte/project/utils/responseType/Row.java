package com.nixbyte.project.utils.responseType;

/**
 * Created by scapegoat on 05/04/2018.
 */

public class Row {
    private String coord_shirota;
    private String coord_dolgota;
    private String www;
    private String description;
    private String address_manual;
    private String name;
    private String phone;
    private String district;
    private String metro;

    public String getPhone() {
        return phone;
    }

    public String getDistrict() {
        return district;
    }

    public String getMetro() {
        return metro;
    }

    public String getEmail() {
        return email;
    }

    private String email;

    public String getCoord_shirota() {
        return coord_shirota;
    }

    public String getCoord_dolgota() {
        return coord_dolgota;
    }

    public String getWww() {
        return www;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress_manual() {
        return address_manual;
    }

    public String getName() {
        return name;
    }
}
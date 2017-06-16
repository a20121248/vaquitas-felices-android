package com.github.alvarosct.happycows.data.db.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class Client {

    @SerializedName("name")
    private String name;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("address")
    private String address;
    @SerializedName("dni")
    private String dni;
    @SerializedName("gender")
    private String gender = "M";
    @SerializedName("bioferia_id")
    private int bioferiaId = 1;
    @SerializedName("district")
    private int district = 24;
    @SerializedName("phone")
    private String phone;
    @SerializedName("reference")
    private String reference;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBioferiaId() {
        return bioferiaId;
    }

    public void setBioferiaId(int bioferiaId) {
        this.bioferiaId = bioferiaId;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
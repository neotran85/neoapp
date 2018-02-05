package io.neo.mvvm.data.model.api.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AppointmentDeleteRequest {
    @Expose
    @SerializedName("id_number")
    private String idNumber;

    public AppointmentDeleteRequest(String idNumber) {
        idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}

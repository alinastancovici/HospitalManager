package com.siit.hospital_manager.model.dto;

import com.siit.hospital_manager.model.AppointmentStatus;
import com.siit.hospital_manager.model.Doctor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAppointmentDto {

    Integer id;

    private String note;

    private String diagnostic;

    private String treatment;


    public Integer getId() {
        return id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNote() {
        return note;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public String getTreatment() {
        return treatment;
    }

}

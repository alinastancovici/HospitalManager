package com.siit.hospital_manager.model;

public class AppointmentDetails {

    private String note;
    private String diagnostic;
    private String treatment;

    public AppointmentDetails(String note, String diagnostic, String treatment) {
        this.note = note;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}

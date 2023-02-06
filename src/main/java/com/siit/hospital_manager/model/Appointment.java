package com.siit.hospital_manager.model;

import com.siit.hospital_manager.model.dto.AppointmentDto;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "appointments")
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private AppointmentStatus appointmentStatus;
    public AppointmentDto toDto(){
//        String dateFormat = "MMM dd HH:mm";
//        String formattedDate = date.format(DateTimeFormatter.ofPattern(dateFormat));

        return AppointmentDto
                .builder()
                .id(id)
                .date(date)
                .patient(patient)
                .doctor(doctor)
                .appointmentStatus(appointmentStatus)
                .build();
    }
    public Appointment() {
    }

    public Appointment(Integer id, Date date, Patient patient, Doctor doctor, AppointmentStatus appointmentStatus) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentStatus = appointmentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}

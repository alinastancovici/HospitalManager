package com.siit.hospital_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="doctors")
@Data
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Doctor extends User{
    private String name;
    private String specialisation;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<Appointment> appointments;


}

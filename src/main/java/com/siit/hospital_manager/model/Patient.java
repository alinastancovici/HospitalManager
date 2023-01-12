package com.siit.hospital_manager.model;

import com.siit.hospital_manager.model.dto.CreatePatientDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String phoneNumber;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<Appointment> appointments;

    public Patient() {
    }

    public Patient(CreatePatientDto createPatientDto) {
        this.age = createPatientDto.getAge();
        this.name = createPatientDto.getName();
        this.phoneNumber = createPatientDto.getPhoneNumber();
    }

    public Patient(Integer id, String name, Integer age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

package com.siit.hospital_manager.model.dto;

import com.siit.hospital_manager.model.AppointmentStatus;
import com.siit.hospital_manager.model.Doctor;
import com.siit.hospital_manager.model.Patient;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class CreateAppointmentDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String doctorName;
    private String patientName;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

}

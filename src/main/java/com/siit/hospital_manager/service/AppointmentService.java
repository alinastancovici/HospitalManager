package com.siit.hospital_manager.service;

import com.siit.hospital_manager.exception.BusinessException;
import com.siit.hospital_manager.model.Appointment;
import com.siit.hospital_manager.model.Patient;
import com.siit.hospital_manager.model.dto.*;
import com.siit.hospital_manager.repository.AppointmentsRepository;
import com.siit.hospital_manager.repository.PatientJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientJpaRepository patientJpaRepository;
    private final AppointmentsRepository appointmentsRepository;

    public List<AppointmentDto> findAllByPatientId(Integer id) {
        List<Appointment> appointments = appointmentsRepository.findAllByPatientId(id);

       return appointments
                .stream()
                .map(Appointment::toDto)
                .toList();
    }

    public List<AppointmentDto> findAll() {
        return appointmentsRepository.findAll()
                .stream()
                .map(Appointment::toDto)
                .toList();
    }

    public void createAppointment(CreateAppointmentDto createAppointmentDto) {
        Optional<Patient> patient = patientJpaRepository.findById(createAppointmentDto.getPatientId());
        if(patient.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Patient does not exist");
        }
        appointmentsRepository.save(new Appointment(createAppointmentDto,patient.get()));
    }

    public void updateAppointment(UpdateAppointmentDto updateAppointmentDto) {
        Appointment appointment = appointmentsRepository
                .findById(updateAppointmentDto.getId())
                .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST, "Appointment with id " + updateAppointmentDto.getId() + " not found"));

        if (updateAppointmentDto.getDate() != null) {
            appointment.setDate(updateAppointmentDto.getDate());
        }
        appointmentsRepository.save(appointment);
    }
}

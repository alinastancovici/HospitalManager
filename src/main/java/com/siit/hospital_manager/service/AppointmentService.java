package com.siit.hospital_manager.service;

import com.siit.hospital_manager.exception.BusinessException;
import com.siit.hospital_manager.model.*;
import com.siit.hospital_manager.model.dto.AppointmentDto;
import com.siit.hospital_manager.model.dto.CreateAppointmentDto;
import com.siit.hospital_manager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentsRepository appointmentsRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

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

    public List<AppointmentDto> findAllByUserName(String userName) {
        User patient = userRepository.findByUserName(userName).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, "User not found")
        );

        List<Appointment> appointments = appointmentsRepository.findAllByPatientId(patient.getId());
        return appointments.stream()
                .map(Appointment::toDto)
                .toList();

    }
    public List<AppointmentDto> findAllByDoctor(String doctorName) {
        Doctor doctor = doctorRepository.findByName(doctorName).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, "Doctor not found")
        );

        List<Appointment> appointments = appointmentsRepository.findAllByDoctor(doctor);

        return appointments.stream()
                .map(Appointment::toDto)
                .toList();

    }

        @Transactional
    public void deleteAppointmentByIdAndPatient(Integer id, String userName) {
        Patient patient = patientRepository.findByUserName(userName).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, "Invalid patient id"));

        Appointment appointment = appointmentsRepository.findAppointmentByIdAndPatient(id, patient).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, "Appointment not found"));

        appointmentsRepository.deleteByIdNativeQuery(appointment.getId());
    }

    public List<AppointmentDto> create(String name) {

        return null;
    }

    public Appointment createAppointment(CreateAppointmentDto createAppointmentDto) {

        Patient patient = patientRepository.findByUserName(createAppointmentDto.getPatientName()).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, "Invalid patient id"));

        Appointment appointment = Appointment.builder()
                .date(createAppointmentDto.getDate())
                .doctor(doctorRepository.findByName(createAppointmentDto.getDoctorName()).get())
                .patient(patient)
                .appointmentStatus(AppointmentStatus.CONFIRMED)
                .build();
        return appointmentsRepository.save(appointment);
    }

    public Appointment findAppointmentById(Integer id) {
        Appointment appointment = appointmentsRepository.findAppointmentById(id);
        return appointment;
    }
}

package com.siit.hospital_manager.controller;

import com.siit.hospital_manager.model.dto.AppointmentDto;
import com.siit.hospital_manager.model.dto.CreateAppointmentDto;
import com.siit.hospital_manager.model.dto.UpdateAppointmentDto;
import com.siit.hospital_manager.model.dto.UpdatePatientDto;
import com.siit.hospital_manager.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentsController {

    private final AppointmentService appointmentService;


    @GetMapping("/patient/{id}")
    public List<AppointmentDto> getByPatientId(@PathVariable("id") Integer id) {
        return appointmentService.findAllByPatientId(id);
    }

    @GetMapping
    public List<AppointmentDto> findAll() {
        return appointmentService.findAll();
    }

    @PostMapping
    public void createAppointment(@RequestBody CreateAppointmentDto createAppointmentDto) {
        appointmentService.createAppointment(createAppointmentDto);
    }

    @PatchMapping
    public void updateAppointment(@RequestBody @Valid UpdateAppointmentDto updateAppointmentDto) {
        appointmentService.updateAppointment(updateAppointmentDto);

    }
}

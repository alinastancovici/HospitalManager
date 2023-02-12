package com.siit.hospital_manager.controller;

import com.siit.hospital_manager.model.Appointment;
import com.siit.hospital_manager.model.AppointmentStatus;
import com.siit.hospital_manager.model.dto.AppointmentDto;
import com.siit.hospital_manager.model.dto.CreateAppointmentDto;
import com.siit.hospital_manager.model.dto.UpdateAppointmentDto;
import com.siit.hospital_manager.service.AppointmentService;
import com.siit.hospital_manager.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/appointment")

@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    @GetMapping("/findAllByPatient")
    public String findAllByPatient(Model model, Principal principal, AppointmentStatus appointmentStatus) {
        List<AppointmentDto> appointments = appointmentService.findAllByUserName(principal.getName(), AppointmentStatus.CONFIRMED);
        model.addAttribute("appointments", appointments);
        return "appointment/viewAll";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAppointmentById(@PathVariable Integer id, Principal principal) {

        try {
            appointmentService.deleteAppointmentByIdAndPatient(id, principal.getName());

        } catch (Exception userNotFoundException) {
            appointmentService.deleteAppointmentByIdAndDoctor(id, principal.getName());
        }
    }


    @GetMapping("/findAllByDoctor")
    public String findAllByDoctor(Model model, Principal principal) {
        List<AppointmentDto> appointments = appointmentService.findAllByDoctorAndStatus(principal.getName(), AppointmentStatus.CONFIRMED);
        model.addAttribute("appointments", appointments);

        return "appointment/viewAllAppointmentsByDoctor";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("appointment", CreateAppointmentDto.builder().build());
        return "appointment/create";
    }

    @PostMapping("/submitCreateAppointmentForm")
    public String submitCreateAppointmentForm(@Valid CreateAppointmentDto createAppointmentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //return to error page if there are validation errors
            return "/validationError";
        }
        try {
            appointmentService.createAppointment(createAppointmentDto);
        } catch (ResponseStatusException exception) {
            return "/entityExistsError";
        }
        return "redirect:/dashboard";
    }

        @GetMapping("/details/{id}")
    public String viewAppointmentById(Model model, @PathVariable Integer id){
        Appointment appointment = appointmentService.findAppointmentById(id);
        model.addAttribute("appointment", appointment);

        return "appointment/details";
    }

    @PostMapping(value="/details/{id}")
    public String updateAppointment(@Valid UpdateAppointmentDto updateAppointmentDto) {
        appointmentService.updateAppointment(updateAppointmentDto);
        return "redirect:/dashboard";
    }

    @GetMapping("/doctorAppointmentHistory")
    public String findCompletedAppointmentsByDoctor(Model model, Principal principal) {
        List<AppointmentDto> appointments = appointmentService.findAllByDoctorAndStatus(principal.getName(), AppointmentStatus.COMPLETED);
        model.addAttribute("appointments", appointments);

        return "appointment/doctorAppointmentHistory";
    }

    @GetMapping("/history/{id}")
    public String viewAppointmentHistory(Model model, @PathVariable Integer id){
        Appointment appointment = appointmentService.findAppointmentById(id);
        model.addAttribute("appointment", appointment);

        return "appointment/history";
    }

    @GetMapping("/patientAppointmentHistory")
    public String findCompletedAppointmentsByPatient(Model model, Principal principal, AppointmentStatus appointmentStatus) {
        List<AppointmentDto> appointments = appointmentService.findAllByUserName(principal.getName(), AppointmentStatus.COMPLETED);
        model.addAttribute("appointments", appointments);
        return "appointment/patientAppointmentHistory";
    }



}
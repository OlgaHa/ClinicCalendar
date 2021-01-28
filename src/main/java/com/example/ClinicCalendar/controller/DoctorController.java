package com.example.ClinicCalendar.controller;

import com.example.ClinicCalendar.model.Appointment;
import com.example.ClinicCalendar.model.Doctor;
import com.example.ClinicCalendar.rest.AppointmentDTO;
import com.example.ClinicCalendar.rest.DoctorDTO;
import com.example.ClinicCalendar.rest.ResponseDTO;
import com.example.ClinicCalendar.service.AppointmentService;
import com.example.ClinicCalendar.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.findAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public ResponseDTO<Doctor> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.findDoctorById(id);
        if(doctor == null)
            return new ResponseDTO(
                    "Sorry, we don't have doctor with id " + id,
                    HttpStatus.NOT_FOUND
            );
        else {
            return new ResponseDTO<>(doctor, HttpStatus.OK);
        }
    }

    @GetMapping("/search")
    public ResponseDTO getDoctorByName(@RequestParam String name) {
        Doctor doctor = doctorService.findDoctorByName(name);
        if(doctor == null) {
            return new ResponseDTO(
                    "Sorry, we don't have doctor " + name,
                    HttpStatus.NOT_FOUND
            );
        } else {
            return new ResponseDTO<>(doctor, HttpStatus.OK);
        }
    }

    @PostMapping("/doctors")
    public Doctor saveDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorDTO.getId());
        doctor.setName(doctorDTO.getName());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctorService.saveDoctor(doctor);
        return doctor;
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        return appointmentService.findAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    public List<Appointment> getDoctorAppointments(@PathVariable int id) {
        return appointmentService.findAppoinmentByDoctorId(id);
    }

    @PostMapping("/appointments")
    public Appointment saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());
        appointment.setDateAndTime(appointmentDTO.getDateAndTime());
        appointment.setPatientName(appointmentDTO.getName());
        appointment.setDoctorId(appointmentDTO.getDoctorId());

        appointmentService.saveAppointment(appointment);
        return appointment;
    }
}

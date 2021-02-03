package com.example.cliniccalendar.controller;

import com.example.cliniccalendar.model.Appointment;
import com.example.cliniccalendar.model.Doctor;
import com.example.cliniccalendar.rest.AppointmentDTO;
import com.example.cliniccalendar.rest.DoctorDTO;
import com.example.cliniccalendar.rest.ResponseDTO;
import com.example.cliniccalendar.service.AppointmentServiceImpl;
import com.example.cliniccalendar.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class DoctorController {

    @Autowired
    private DoctorServiceImpl doctorService;
    @Autowired
    private AppointmentServiceImpl appointmentService;

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.findAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public ResponseDTO<Doctor> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.findDoctorById(id);
        if (doctor == null)
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
        if (doctor == null) {
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

    @GetMapping("/appointments/id/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable int id) {
        Appointment appointment = appointmentService.findById(id);

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setName(appointment.getPatientName());
        appointmentDTO.setDateAndTime(appointment.getDateAndTime());
        appointmentDTO.setDoctorId(appointment.getDoctorId());

        return appointmentDTO;
    }

    @PostMapping("/appointments")
    public Appointment saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());
        appointment.setPatientName(appointmentDTO.getName());
        appointment.setDateAndTime(appointmentDTO.getDateAndTime());
        appointment.setDoctorId(appointmentDTO.getDoctorId());

        appointmentService.saveAppointment(appointment);
        return appointment;
    }

    @DeleteMapping("/appointments/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        try {
            appointmentService.deleteAppointment(id);
        } catch (EmptyResultDataAccessException dae) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}

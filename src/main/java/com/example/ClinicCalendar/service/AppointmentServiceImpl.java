package com.example.ClinicCalendar.service;

import com.example.ClinicCalendar.dao.AppointmentRepository;
import com.example.ClinicCalendar.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findAll().forEach(appointments::add);
        return appointments;
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAppoinmentByDoctorId(int doctorId) {
        return appointmentRepository.findAppointmentByDoctorId(doctorId);
    }

    @Override
    public Appointment findById(int id) {
        return appointmentRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteAppointment(int id) {
         appointmentRepository.deleteById(id);
    }
}

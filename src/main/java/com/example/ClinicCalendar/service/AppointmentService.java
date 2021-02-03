package com.example.ClinicCalendar.service;

import com.example.ClinicCalendar.model.Appointment;

import java.util.List;


public interface AppointmentService {

     List<Appointment> findAllAppointments();
     Appointment saveAppointment(Appointment appointment);
     List<Appointment> findAppoinmentByDoctorId(int doctorId);
     Appointment findById(int id);
     void deleteAppointment(int id);
}

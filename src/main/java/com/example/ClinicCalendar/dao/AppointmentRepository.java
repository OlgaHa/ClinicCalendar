package com.example.ClinicCalendar.dao;

import com.example.ClinicCalendar.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

     List<Appointment> findAppointmentByDoctorId(int doctorId);

}

package com.example.ClinicCalendar.service;

import com.example.ClinicCalendar.model.Doctor;

import java.util.List;


public interface DoctorService {

     List<Doctor> findAllDoctors();
     Doctor findDoctorById(Integer id);
     Doctor findDoctorByName(String name);
     Doctor saveDoctor(Doctor doctor);

}

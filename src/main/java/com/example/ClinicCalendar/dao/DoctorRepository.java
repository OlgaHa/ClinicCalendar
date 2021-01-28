package com.example.ClinicCalendar.dao;

import com.example.ClinicCalendar.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
   Optional <Doctor> findDoctorByNameContainingIgnoreCase(String name);
}

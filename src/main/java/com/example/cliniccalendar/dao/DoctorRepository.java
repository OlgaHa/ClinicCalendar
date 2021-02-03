package com.example.cliniccalendar.dao;

import com.example.cliniccalendar.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
   Optional <Doctor> findDoctorByNameContainingIgnoreCase(String name);
}

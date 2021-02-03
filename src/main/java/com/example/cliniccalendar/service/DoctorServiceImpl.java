package com.example.cliniccalendar.service;

import com.example.cliniccalendar.dao.DoctorRepository;
import com.example.cliniccalendar.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    @Override
    public Doctor findDoctorById(Integer id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor findDoctorByName(String name) {
        return doctorRepository.findDoctorByNameContainingIgnoreCase(name).orElse(null);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}

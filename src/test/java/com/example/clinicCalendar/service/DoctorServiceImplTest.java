package com.example.cliniccalendar.service;

import com.example.cliniccalendar.dao.DoctorRepository;
import com.example.cliniccalendar.model.Doctor;
import com.example.cliniccalendar.service.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DoctorServiceImplTest {

    private Doctor doctor;
    private Iterable<Doctor> iterable;
    private int doctorId;

    @Mock
    DoctorRepository doctorRepository;
    @InjectMocks
    DoctorServiceImpl doctorService;

    @BeforeEach
    public void setUp() {
        doctorId = 1;
        doctor = new Doctor(doctorId, "Alan Carter", "surgeon", null);
        iterable = Arrays.asList(doctor);
    }

    @Test
    public void findAllDoctorsTest() {
        when(doctorRepository.findAll()).thenReturn(iterable);

        Iterable <Doctor> actual = doctorService.findAllDoctors();

        assertEquals(iterable, actual);
    }

    @Test
    public void findDoctorByIdTest(){
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        Doctor actual = doctorService.findDoctorById(doctorId);

        assertEquals(doctor.getName(), actual.getName());
    }

    @Test
    public void findDoctorByNameTest(){
        when(doctorRepository.findDoctorByNameContainingIgnoreCase(any())).thenReturn(Optional.of(doctor));

        Doctor actual = doctorService.findDoctorByName("alan");

        assertEquals(doctor.getName(), actual.getName());
    }

    @Test
    public void saveDoctorTest(){
        doctor.setDoctorId(2);

        when(doctorRepository.save(any())).thenReturn(doctor);

        Doctor actual = doctorService.saveDoctor(doctor);

        assertEquals(2, actual.getDoctorId());
    }
}

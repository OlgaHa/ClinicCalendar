package com.example.ClinicCalendar;

import com.example.ClinicCalendar.controller.DoctorController;
import com.example.ClinicCalendar.model.Appointment;
import com.example.ClinicCalendar.model.Doctor;
import com.example.ClinicCalendar.rest.AppointmentDTO;
import com.example.ClinicCalendar.rest.DoctorDTO;
import com.example.ClinicCalendar.rest.ResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:~/test_db",
        "spring.jpa.hibernate.ddl-auto=create-drop"})
public class CalenderIntegrationTest {

    @Autowired
    DoctorController controller;

    @Test
    public void doctorsListTest() {
        List<Doctor> doctors = controller.getDoctors();
        assert !doctors.isEmpty();
        assertEquals(6, doctors.size());
    }

    @Test
    public void doctorByIdTest() {
        ResponseDTO<Doctor> responseDTO = controller.getDoctorById(1);

        assertEquals("Derek Shepherd", Objects.requireNonNull(responseDTO.getBody()).getName());
        assertEquals("neurosurgeon",responseDTO.getBody().getSpecialization());
        assertEquals("200 OK", responseDTO.getStatusCode().toString());

        responseDTO = controller.getDoctorById(100);

        assertEquals( "Sorry, we don't have doctor with id 100", responseDTO.getBody());
        assertEquals("404 NOT_FOUND", responseDTO.getStatusCode().toString());
    }

    @Test
    public void searchTest() {
        ResponseDTO <Doctor> responseDTO = controller.getDoctorByName("hunt");

        assertEquals("Owen Hunt",  Objects.requireNonNull(responseDTO.getBody()).getName());
        assertEquals("trauma surgeon",responseDTO.getBody().getSpecialization());

        responseDTO = controller.getDoctorByName("aaa");

        assertEquals("Sorry, we don't have doctor aaa", responseDTO.getBody());
        assertEquals("404 NOT_FOUND", responseDTO.getStatusCode().toString());
    }

    @Test
    public void saveDoctorTest(){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setName("Test Test");
        doctorDTO.setSpecialization("test surgeon");
        doctorDTO.setId(8);

        Doctor savedDoctor = controller.saveDoctor(doctorDTO);

        assertEquals("Test Test", savedDoctor.getName());
        assertEquals(8, savedDoctor.getDoctorId());
    }

    @Test
    public void appointmentListTest() {
        List<Appointment> appointments = controller.getAppointments();
        assert !appointments.isEmpty();
        assertEquals(9, appointments.size());
    }

    @Test
    public void appointmentsByDoctorIdTest() {
        List<Appointment> appointments = controller.getDoctorAppointments(1);
        assertEquals(1, appointments.get(0).getDoctorId());
        assertEquals("Dave Grohl", appointments.get(0).getPatientName());
    }

    @Test
    public void saveAppointmentTest() {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDateAndTime("2021-03-21 10:02");
        appointmentDTO.setDoctorId(2);
        appointmentDTO.setName("Test Test");
        appointmentDTO.setId(9);

        Appointment savedAppointment = controller.saveAppointment(appointmentDTO);

        assertEquals("Test Test", savedAppointment.getPatientName());
        assertEquals(9, savedAppointment.getId());
    }
}

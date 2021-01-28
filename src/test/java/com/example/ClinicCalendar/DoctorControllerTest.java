package com.example.ClinicCalendar;

import com.example.ClinicCalendar.controller.DoctorController;
import com.example.ClinicCalendar.model.Appointment;
import com.example.ClinicCalendar.model.Doctor;
import com.example.ClinicCalendar.service.AppointmentService;
import com.example.ClinicCalendar.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DoctorController.class)
public class DoctorControllerTest {

    private Appointment appointment;
    private List<Appointment> appointments;
    private Doctor doctor;
    private int doctorId;

    @Autowired
    MockMvc mockMvc;
    @MockBean
    DoctorService doctorService;
    @MockBean
    AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        doctorId = 1;
        appointment = new Appointment(2, "Hanna D.", "2021-08-01 14:20", doctorId);
        appointments = new ArrayList<>();
        appointments.add(appointment);
        doctor = new Doctor(doctorId, "Alan Carter", "surgeon", appointments);
    }

    @Test
    public void getDoctorsTest() throws Exception {
        when(doctorService.findAllDoctors()).thenReturn(Collections.singletonList(doctor));
        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Carter")));
        verify(doctorService).findAllDoctors();
    }

    @Test
    public void getDoctorByIdTest() throws Exception {
        when(doctorService.findDoctorById(any())).thenReturn(doctor);

        mockMvc.perform(get("/doctors/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"doctorId\":1,\"name\":\"Alan Carter\",\"specialization\":\"surgeon\"," +
                        "\"appointments\":[{\"id\":2,\"patientName\":\"Hanna D.\",\"dateAndTime\":\"2021-08-01 " +
                        "14:20\",\"doctorId\":1}]}"));
    }

    @Test
    public void getDoctorById_Not_Found_Test() throws Exception {
        when(doctorService.findDoctorById(any())).thenReturn(null);

        mockMvc.perform(get("/doctors/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Sorry, we don't have doctor with id 1"));
    }

    @Test
    public void getDoctorByNameTest() throws Exception {
        when(doctorService.findDoctorByName(any())).thenReturn(doctor);

        mockMvc.perform(get("/search?name=Carter"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"doctorId\":1,\"name\":\"Alan Carter\",\"specialization\":\"surgeon\"," +
                        "\"appointments\":[{\"id\":2,\"patientName\":\"Hanna D.\",\"dateAndTime\":\"2021-08-01 " +
                        "14:20\",\"doctorId\":1}]}"));
    }

    @Test
    public void getDoctorByName_Not_Found_Test() throws Exception {
        when(doctorService.findDoctorByName(any())).thenReturn(null);

        mockMvc.perform(get("/search?name=aaa"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Sorry, we don't have doctor aaa"));
    }

    @Test
    public void saveDoctorTest() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(1);
        doctor.setName("Alan Carter");
        doctor.setSpecialization("surgeon");
        String testString = "{\"doctorId\":1,\"name\":\"Alan Carter\",\"specialization\":\"surgeon\"," +
                "\"appointments\":null}";

        when(doctorService.saveDoctor(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testString))
                .andExpect(status().isOk());

        verify(doctorService).saveDoctor(any());
    }

    @Test
    public void getAppointmentsTest() throws Exception {
        when(appointmentService.findAllAppointments()).thenReturn(appointments);
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hanna")));
        verify(appointmentService).findAllAppointments();
    }

    @Test
    public void getDoctorAppointmentsTest() throws Exception {
        when(appointmentService.findAppoinmentByDoctorId(doctorId)).thenReturn(appointments);
        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hanna")));
        verify(appointmentService).findAppoinmentByDoctorId(doctorId);
    }

    @Test
    public void saveAppointmentTest() throws Exception {
        String testString = "{\"id\":2,\"patientName\":\"Hanna D.\",\"dateAndTime\":\"2021-08-01 " +
                "14:20\",\"doctorId\":1}";

        when(appointmentService.saveAppointment(Mockito.any(Appointment.class))).thenReturn(appointment);

        mockMvc.perform(post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testString))
                .andExpect(status().isOk());

        verify(appointmentService).saveAppointment(any());
    }
}


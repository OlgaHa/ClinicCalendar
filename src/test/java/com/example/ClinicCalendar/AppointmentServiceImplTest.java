package com.example.ClinicCalendar;

import com.example.ClinicCalendar.dao.AppointmentRepository;
import com.example.ClinicCalendar.model.Appointment;
import com.example.ClinicCalendar.rest.AppointmentDTO;
import com.example.ClinicCalendar.service.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AppointmentServiceImplTest {

    private Appointment appointment1;
    private Appointment appointment2;
    private List<Appointment> appointments;
    private Iterable<Appointment> iterable;
    private int doctorId;
    private int id1;
    private int id2;

    @Mock
    AppointmentRepository appointmentRepository;
    @InjectMocks
    AppointmentServiceImpl appointmentService;

    @BeforeEach
    public void setUp() {
        doctorId = 0;
        id1 = 1;
        id2 = 2;
        appointment1 = new Appointment(id1, "Hanna D.", "2021-08-01 14:20", doctorId);
        appointment2 = new Appointment(id2, "Dana D.", "2021-08-01 15:20", doctorId);
        appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        iterable = appointments;
    }

    @Test
    public void findAllAppointmentsTest() {
        when(appointmentRepository.findAll()).thenReturn(iterable);

        Iterable<Appointment> actual = appointmentService.findAllAppointments();

        assertEquals(iterable, actual);
    }

    @Test
    public void saveAppointmentTest() {
        appointment1.setDoctorId(2);

        when(appointmentRepository.save(any())).thenReturn(appointment1);

        Appointment actual = appointmentService.saveAppointment(appointment1);

        assertEquals(2, actual.getDoctorId());
    }

    @Test
    public void findAppoinmentByDoctorIdTest() {
        when(appointmentRepository.findAppointmentByDoctorId(doctorId)).thenReturn(appointments);

        List<Appointment> actual = appointmentService.findAppoinmentByDoctorId(doctorId);

        assertEquals(appointments.get(0).getDateAndTime(), actual.get(0).getDateAndTime());
    }

    @Test
    public void getAppointmentByIdTest() {
        when(appointmentRepository.findById(id1)).thenReturn(Optional.of(appointment1));

        Appointment appointment = appointmentService.findById(id1);

        assertEquals("Hanna D.", appointment.getPatientName());

        when(appointmentRepository.findById(3)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, ()-> appointmentService.findById(3));
    }

    @Test
    public void getDeleteByIdTest() {
        appointmentService.deleteAppointment(id1);
        verify(appointmentRepository).deleteById(id1);
    }


}

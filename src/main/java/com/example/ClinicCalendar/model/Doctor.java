package com.example.ClinicCalendar.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "doctors_tbl")
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int doctorId;
    private String name;
    private String specialization;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private List<Appointment> appointments;

    public Doctor() {
    }

    public Doctor(int doctorId, String name, String specialization, List<Appointment> appointments) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

package com.example.ClinicCalendar.model;

import javax.persistence.*;

@Entity
@Table(name = "appointments_tbl")
public class Appointment {
    @Id
    private int id;
    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "appointment_time")
    private String dateAndTime;
    @Column(name = "doctor_id")
    private int doctorId;

    public Appointment() {
    }

    public Appointment(int id, String patientName, String dateAndTime, int doctorId) {
        this.id = id;
        this.patientName = patientName;
        this.dateAndTime = dateAndTime;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}


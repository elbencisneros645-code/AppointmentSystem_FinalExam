/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import cisneros_javaui.AppointmentManager;
import cisneros_javaui.Appointment;
import utils.FileDBHelper;

import java.util.List;

/**
 *
 * @author USER
 */
public class AppoinmentController {

    private final AppointmentManager manager;
    private final FileDBHelper fileDB;

    public AppoinmentController() {

        manager = new AppointmentManager();
        fileDB = new FileDBHelper();

        manager.load(fileDB.loadAppointments());
    }

    public String bookAppointment(
            String patientName,
            String phone,
            String doctor,
            String date,
            String time,
            String reason) {

        if (patientName.isEmpty()
                || phone.isEmpty()
                || doctor.isEmpty()
                || date.isEmpty()
                || time.isEmpty()) {

            return "Please fill in all required fields.";
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {

            return "Use the date format YYYY-MM-DD.";
        }

        if (manager.isDoctorBooked(doctor, date, time)) {

            return "That doctor already has an appointment "
                    + "at this date and time.";
        }

        if (reason.isEmpty()) {
            reason = "Not specified";
        }

        Appointment appointment = new Appointment(
                patientName,
                phone,
                doctor,
                date,
                time,
                reason
        );

        manager.add(appointment);

        fileDB.saveAppointments(manager.getAll());

        return "Appointment booked successfully!";
    }

    public List<Appointment> getAppointments() {

        return manager.getAll();
    }

    public String cancelAppointment(int index) {

        Appointment removed = manager.removeAt(index);

        if (removed == null) {
            return "Please select an appointment first.";
        }

        fileDB.saveAppointments(manager.getAll());

        return "Appointment cancelled successfully.";
    }
}



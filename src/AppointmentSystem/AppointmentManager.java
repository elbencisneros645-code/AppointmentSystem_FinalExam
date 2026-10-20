/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cisneros_javaui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class AppointmentManager {

    private final List<Appointment> appointments = new ArrayList<>();

    public void add(Appointment appointment) {
        appointments.add(appointment);
    }

    public Appointment removeAt(int index) {
        if (index < 0 || index >= appointments.size()) {
            return null;
        }
        return appointments.remove(index);
    }

    public List<Appointment> getAll() {
        return new ArrayList<>(appointments);
    }

    public boolean isDoctorBooked(
            String doctor,
            String date,
            String time) {

        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(doctor)
                && appointment.getDate().equals(date)&& 
                        appointment.getTime().equals(time)){

                return true;

            }
        }
        return false;

    }
    
    public void load(List<Appointment> savedAppointments){
        appointments.clear();
        appointments.addAll(savedAppointments);
    }

}

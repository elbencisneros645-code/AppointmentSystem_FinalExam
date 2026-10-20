/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cisneros_javaui;

/**
 *
 * @author USER
 */
public class Appointment {

    private String patientName;
    private String phone;
    private String doctor;
    private String date;
    private String time;
    private String reason;

    public Appointment(String patientName, String phone, String doctor, String date, String time, String reason) {
        this.patientName = patientName;
        this.phone = phone;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public String[] toTableROw() {

        return new String[]{
            patientName,
            phone,
            doctor,
            date,
            time,
            reason,};

    }

    public String toCsv() {

        return quote(patientName) + "" + quote(phone) + "" + quote(doctor) + " " + quote(date) + " " + quote(date) + "" + quote(reason);
        
        

    }
    
    private String quote(String value){
        return "\"" + value.replace("\"","\"\"")+"\"";
    }

}

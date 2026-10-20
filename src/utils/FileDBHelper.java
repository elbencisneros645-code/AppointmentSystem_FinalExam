/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
 import cisneros_javaui.Appointment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class FileDBHelper {

    private static final Path FILE =
            Paths.get("appointments.csv");

    public List<Appointment> loadAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        if (!Files.exists(FILE)) {
            return appointments;
        }

        try (BufferedReader reader =
                     Files.newBufferedReader(
                             FILE, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                List<String> values = parseCsvLine(line);

                if (values.size() >= 6) {

                    appointments.add(
                            new Appointment(
                                    values.get(0),
                                    values.get(1),
                                    values.get(2),
                                    values.get(3),
                                    values.get(4),
                                    values.get(5)
                            )
                    );
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error loading appointments: "
                            + e.getMessage(), e);
        }

        return appointments;
    }

    public void saveAppointments(
            List<Appointment> appointments) {

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             FILE,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.TRUNCATE_EXISTING)) {

            for (Appointment appointment : appointments) {

                writer.write(appointment.toCsv());
                writer.newLine();
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error saving appointments: "
                            + e.getMessage(), e);
        }
    }

    private List<String> parseCsvLine(String line) {

        List<String> values = new ArrayList<>();

        StringBuilder value = new StringBuilder();

        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '"') {

                if (inQuotes
                        && i + 1 < line.length()
                        && line.charAt(i + 1) == '"') {

                    value.append('"');
                    i++;

                } else {
                    inQuotes = !inQuotes;
                }

            } else if (c == ',' && !inQuotes) {

                values.add(value.toString());
                value.setLength(0);

            } else {

                value.append(c);
            }
        }

        values.add(value.toString());

        return values;
    }
}
    


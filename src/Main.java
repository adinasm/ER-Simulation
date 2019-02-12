/**
 * @author Adina Smeu
 */

import hospital.Hospital;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import observers.DoctorPrinter;
import observers.NursePrinter;
import observers.PatientPrinter;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {

    }

    /**
     * Simulates the emergency room for the given number of rounds.
     * @param hospital the class that describes the emergency room
     */
    private static void simulate(Hospital hospital) {
        for (int i = 0; i < hospital.getSimulationLength(); ++i) {
            hospital.round(i);
            hospital.update();
        }
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(args[0]);
        try {
            // Reads the data from the Json file.
            Hospital hospital = objectMapper.readValue(file, Hospital.class);

            // Creates the observers.
            PatientPrinter patientPrinter = PatientPrinter.getInstance();
            NursePrinter nursePrinter = NursePrinter.getInstance();
            DoctorPrinter doctorPrinter = DoctorPrinter.getInstance();

            // Adds the observers.
            hospital.addObserver(doctorPrinter);
            hospital.addObserver(nursePrinter);
            hospital.addObserver(patientPrinter);

            // Creates the employees (nurses, doctors, technicians).
            hospital.createEmployees();

            // Starts the simulation.
            simulate(hospital);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

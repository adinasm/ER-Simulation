/**
 * @author Adina Smeu
 */

package observers;

import hospital.BaseDoctor;
import hospital.Hospital;
import hospital.Patient;
import tools.PatientNameComparator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public final class DoctorPrinter implements Observer {
    private static DoctorPrinter instance = new DoctorPrinter();
    private PatientNameComparator patientComparator = new PatientNameComparator();

    private DoctorPrinter() {

    }

    /**
     * Gets the instance.
     * @return the instance
     */
    public static DoctorPrinter getInstance() {
        return instance;
    }

    /**
     * For every doctor from the hospital, prints the verdicts given to each of their patients,
     * either sent home, hospitalized or operated. The patients are sorted alphabetically.
     * @param o the class that describes the emergency room
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Hospital hospital = (Hospital) o;
        ArrayList<BaseDoctor> doctors = hospital.getDoctors();

        System.out.println("~~~~ Doctors check their hospitalized patients and give verdicts ~~~~");

        for (BaseDoctor doctor : doctors) {
            // Sorts the patients.
            ArrayList<Patient> sortedPatients = new ArrayList<Patient>(doctor.getPatients());
            sortedPatients.sort(patientComparator);

            // Prints the verdict for each patient.
            for (Patient patient : sortedPatients) {
                if (patient.getSimulationState().getValue().contains("home")
                    || patient.isGoHome()) {
                    System.out.println(doctor.getType().getValue() + " sent " + patient.getName()
                            + " home");
                } else if (patient.getSimulationState().getValue().contains("hospitalized")
                    || patient.getSimulationState().getValue().contains("operated")) {
                    System.out.println(doctor.getType().getValue() + " says that "
                            + patient.getName() + " should remain in hospital");
                }
            }
        }

        System.out.println();
    }
}

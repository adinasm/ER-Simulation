/**
 * @author Adina Smeu
 */

package observers;

import hospital.Hospital;
import hospital.Patient;
import tools.PatientNameComparator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public final class PatientPrinter implements Observer {
    private static PatientPrinter instance = new PatientPrinter();
    private static PatientNameComparator patientComparator = new PatientNameComparator();

    private PatientPrinter() {

    }

    /**
     * Gets the instance.
     * @return the instance
     */
    public static PatientPrinter getInstance() {
        return instance;
    }

    /**
     * Prints the state of each patients that has arrived in the hospital until
     * the current round. The patients are sorted alphabetically.
     * @param o the class that describes the emergency room
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Hospital hospital = (Hospital) o;

        ArrayList<Patient> patients = hospital.getIncomingPatients();

        // Sorts the patients.
        ArrayList<Patient> sortedPatients = new ArrayList<Patient>(patients);
        sortedPatients.sort(patientComparator);

        int patientCount = sortedPatients.size();
        int currentRound = hospital.getCurrentRound();

        System.out.println("~~~~ Patients in round " + (currentRound + 1) + " ~~~~");

        // Prints the states of the patients that have arrived before or in the current round.
        for (int i = 0; i < patientCount; ++i) {
            if (currentRound >= sortedPatients.get(i).getTime()) {
                System.out.println(sortedPatients.get(i).getName() + " is "
                        + sortedPatients.get(i).getSimulationState().getValue());
            }
        }

        System.out.println();
    }
}

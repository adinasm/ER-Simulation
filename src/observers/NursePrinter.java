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

public final class NursePrinter implements Observer {
    private static NursePrinter instance = new NursePrinter();
    private PatientNameComparator patientComparator = new PatientNameComparator();

    private NursePrinter() {

    }

    /**
     * Gets the instance.
     * @return the instance
     */
    public static NursePrinter getInstance() {
        return instance;
    }

    /**
     * Patients are sorted alphabetically. For every patient, prints the nurse that
     * treated him and the number of rounds he has to stay in the hospital.
     * @param o the class that describes the emergency room
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Hospital hospital = (Hospital) o;

        // Sorts the patients.
        ArrayList<Patient> patients = new ArrayList<Patient>(hospital.getHospitalizedPatients());
        patients.sort(patientComparator);

        int nurseIndex = 0;
        int nurseCount = hospital.getNurses();
        String round;

        System.out.println("~~~~ Nurses treat patients ~~~~");

        // Prints the nurse, the patient and the number of rounds left.
        for (int i = 0; i < patients.size(); ++i) {
            if (patients.get(i).getRounds() == 1) {
                round = "round";
            } else {
                round = "rounds";
            }
            System.out.println("Nurse " + nurseIndex + " treated " + patients.get(i).getName()
                    + " and patient has " + patients.get(i).getRounds() + " more " + round);
            nurseIndex++;

            if (nurseIndex == nurseCount) {
                nurseIndex = 0;
            }
        }

        System.out.println();
    }
}

/**
 * @author Adina Smeu
 */

package queues;

import hospital.Nurse;
import hospital.Patient;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Contains a list of the patients that have to be consulted by the nurses (triagePatients)
 * and a list of patients that have been consulted and have to go to the examination stage
 * (consultedPatients).
 */
public final class TriageQueue {
    private static TriageQueue instance = new TriageQueue();
    private ArrayList<Patient> triagePatients = new ArrayList<Patient>();
    private ArrayList<Patient> consultedPatients = new ArrayList<Patient>();
    private PatientSeverityComparator patientSeverityComparator = new PatientSeverityComparator();

    /**
     *
     */
    class PatientSeverityComparator implements Comparator<Patient> {

        @Override
        public int compare(Patient p1, Patient p2) {
            return p2.getState().getSeverity() - p1.getState().getSeverity();
        }
    }


    private TriageQueue() {

    }

    public static TriageQueue getInstance() {
        return instance;
    }

    public ArrayList<Patient> getConsultedPatients() {
        return consultedPatients;
    }

    /**
     * Consults a number of patients that is the maximum between the patients from
     * the triagePatients list and the number of nurses.
     * @param patients - patients that came in the current round and have to be consulted
     * @param nurses - list of nurses
     */
    public void nurseConsultations(ArrayList<Patient> patients, ArrayList<Nurse> nurses) {
        int nurseCount = nurses.size();
        consultedPatients.clear();
        triagePatients.addAll(patients);

        // Sorts patients based on severity.
        triagePatients.sort(patientSeverityComparator);

        // Consults a patients from the triagePatients list and moves him to the consultedPatients.
        for (int i = 0; i < nurseCount && triagePatients.size() > 0; ++i) {
            nurses.get(i).consult(triagePatients.get(0));
            consultedPatients.add(triagePatients.get(0));
            triagePatients.remove(0);
        }
    }
}

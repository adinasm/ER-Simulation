/**
 * @author Adina Smeu
 */

package queues;

import hospital.ERTechnician;
import hospital.Patient;
import tools.PatientUrgencyComparator;

import java.util.ArrayList;

/**
 * Contains a list of patients that have to be investigated (they come from the examination
 * queue) and a list of patients that have been investigated and have to back to the
 * examination queue.
 */
public final class InvestigationsQueue {
    private static InvestigationsQueue instance = new InvestigationsQueue();
    private ArrayList<Patient> waitingPatients = new ArrayList<Patient>();
    private ArrayList<Patient> investigatedPatients = new ArrayList<Patient>();
    private PatientUrgencyComparator patientUrgencyComparator = new PatientUrgencyComparator();

    private InvestigationsQueue() {

    }

    public static InvestigationsQueue getInstance() {
        return instance;
    }

    public ArrayList<Patient> getInvestigatedPatients() {
        return investigatedPatients;
    }

    /**
     * Investigates a number of patients from the waiting patients that is the maximum between
     * the size of the waitingPatients list and the number of ertechnicians and moves them to
     * the investigated patients list.
     * @param patients - list of patients that came from the examination queue and have to
     *                 be investigated
     * @param erTechnicians - list of ertechnicians
     */
    public void investigate(ArrayList<Patient> patients, ArrayList<ERTechnician> erTechnicians) {
        int erTechnicianCount = erTechnicians.size();
        waitingPatients.addAll(patients);
        investigatedPatients.clear();

        // Sorts the patient based on urgency, severity and name.
        waitingPatients.sort(patientUrgencyComparator);

        // Investigates a patient and moves him to the investigatedPatients list.
        for (int i = 0; i < erTechnicianCount && waitingPatients.size() > 0; ++i) {
            erTechnicians.get(i).investigate(waitingPatients.get(0));
            investigatedPatients.add(waitingPatients.get(0));
            waitingPatients.remove(0);
        }
    }
}

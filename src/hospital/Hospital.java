/**
 * @author Adina Smeu
 */

package hospital;

import enums.State;
import tools.DoctorFactory;
import tools.PatientNameComparator;
import queues.ExaminationQueue;
import queues.InvestigationsQueue;
import queues.TriageQueue;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Contains the doctors, the patients, the nurses and the investigators, as well
 * as the queues (triage, investigations, examination).
 */
public class Hospital extends Observable {
    private int simulationLength;
    private int nurses;
    private int investigators;
    private int doctorsCount;
    private int currentRound = 0;
    private int lastPatient = 0;

    private ArrayList<BaseDoctor> doctors;
    private ArrayList<Patient> incomingPatients;
    private ArrayList<Patient> hospitalizedPatients = new ArrayList<Patient>();

    private ArrayList<Nurse> nursesVector;
    private ArrayList<ERTechnician> erTechnicians;

    private TriageQueue triageQueue = TriageQueue.getInstance();
    private InvestigationsQueue investigationsQueue = InvestigationsQueue.getInstance();
    private ExaminationQueue examinationQueue = ExaminationQueue.getInstance();

    private DoctorFactory doctorFactory = DoctorFactory.getInstance();
    private PatientNameComparator patientNameComparator = new PatientNameComparator();

    public final int getSimulationLength() {
        return simulationLength;
    }

    public final void setSimulationLength(final int s) {
        simulationLength = s;
    }

    public final int getNurses() {
        return nurses;
    }

    public final void setNurses(int n) {
        nurses = n;
    }

    public final int getInvestigators() {
        return investigators;
    }

    public final void setInvestigators(int i) {
        investigators = i;
    }

    public final ArrayList<BaseDoctor> getDoctors() {
        return doctors;
    }

    public final void setDoctors(ArrayList<BaseDoctor> d) {
        doctors = d;
    }

    public final ArrayList<Patient> getIncomingPatients() {
        return incomingPatients;
    }

    public final void setIncomingPatients(ArrayList<Patient> p) {
        incomingPatients = p;
    }

    public final int getCurrentRound() {
        return currentRound;
    }

    public final ArrayList<Patient> getHospitalizedPatients() {
        return hospitalizedPatients;
    }

    /**
     * Notifies the observers (PatientPrinter, NursePrinter, DoctorPrinter).
     */
    public final void update() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Creates a list of ERTechnicians.
     */
    private void createInvestigators() {
        erTechnicians = new ArrayList<ERTechnician>();
        for (int i = 0; i < investigators; ++i) {
            erTechnicians.add(new ERTechnician());
        }
    }

    /**
     * Creates a list of Nurses.
     */
    private void createNurses() {
        nursesVector = new ArrayList<Nurse>();
        for (int i = 0; i < nurses; ++i) {
            nursesVector.add(new Nurse());
        }
    }

    /**
     * Creates the doctors and the doctor queue from the examination queue.
     */
    private void createDoctors() {
        doctorsCount = doctors.size();

        for (int i = 0; i < doctorsCount; ++i) {
            doctors.set(i,  doctorFactory.createDoctor(doctors.get(i)));
        }

        examinationQueue.createDoctorQueue(doctors);
    }

    /**
     * Creates the employees (doctors, investigators, nurses).
     */
    public final void createEmployees() {
        createDoctors();
        createInvestigators();
        createNurses();
    }

    /**
     * The hospitalized patients are sorted alphabetically and the nurses administrate treatments.
     */
    public final void administrateTreatment() {
        hospitalizedPatients.sort(patientNameComparator);
        for (int i = 0, j = 0; i < nurses && hospitalizedPatients.size() > j; ++j) {
            if (hospitalizedPatients.get(j).getSimulationState().getValue()
            .contains("hospitalize") || hospitalizedPatients.get(j).getSimulationState().getValue()
                    .contains("operate")) {
                nursesVector.get(i).treat(hospitalizedPatients.get(j));
                ++i;
            }

            if (i == nurses) {
                i = 0;
            }
        }
    }

    /**
     * Each doctor checks its hospitalized patients to see if they can be sent home.
     */
    public final void checkPatients() {
        for (BaseDoctor doctor : doctors) {
            doctor.checkPatients(hospitalizedPatients);
        }
    }

    /**
     * Gets the patients that came in the current round and adds them to the triage queue.
     * The patients that were previously in the queue and the new ones are consulted by the
     * nurses.
     */
    public final void triage() {
        ArrayList<Patient> currentPatients = new ArrayList<Patient>();
        int patientsCount = incomingPatients.size();

        for (int i = lastPatient; i < patientsCount && incomingPatients.get(i).getTime()
                == currentRound; ++i) {
            incomingPatients.get(i).setSimulationState(State.TRIAGEQUEUE);
            lastPatient = incomingPatients.get(i).getId() + 1;
            currentPatients.add(incomingPatients.get(i));
        }

        triageQueue.nurseConsultations(currentPatients, nursesVector);
    }

    /**
     * Examines the patients that came from the triage queue.
     */
    public final void examine() {
        examinationQueue.consultPatients(triageQueue.getConsultedPatients(),
                hospitalizedPatients);
    }

    /**
     * Investigates the patients that were previously in the queue, as well as the ones
     * that came from the examination queue.
     * The patients that are investigated in the current round are sent back to the
     * examination queue.
     */
    public final void investigate() {
        investigationsQueue.investigate(examinationQueue.getToInvestigatePatients(),
                erTechnicians);
        examinationQueue.addPatients(investigationsQueue.getInvestigatedPatients());
    }

    /**
     * Patients are sent to the triage queue, the examination queue, the investigation queue.
     * Then the nurses administrate the treatment to the hospitalized patients and the doctors
     * check who can be sent home.
     * @param i the current round
     */
    public final void round(int i) {
        currentRound = i;
        triage();
        examine();
        investigate();
        administrateTreatment();
        checkPatients();
    }
}

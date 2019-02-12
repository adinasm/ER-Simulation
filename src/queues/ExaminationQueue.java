/**
 * @author Adina Smeu
 */

package queues;

import hospital.BaseDoctor;
import hospital.Patient;
import enums.InvestigationResult;
import enums.State;
import tools.PatientUrgencyComparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public final class ExaminationQueue {
    private static ExaminationQueue instance = new ExaminationQueue();
    private ArrayList<Patient> examinePatients = new ArrayList<Patient>();
    private ArrayList<Patient> toInvestigatePatients = new ArrayList<Patient>();
    private ArrayList<BaseDoctor> doctorQueue;
    private int doctorCount;
    private PatientUrgencyComparator patientUrgencyComparator = new PatientUrgencyComparator();

    private ExaminationQueue() {

    }

    public static ExaminationQueue getInstance() {
        return instance;
    }

    public ArrayList<Patient> getToInvestigatePatients() {
        return toInvestigatePatients;
    }

    public void addPatients(ArrayList<Patient> patients) {
        examinePatients.addAll(patients);
    }

    /**
     * Creates a queue of doctors.
     * @param doctors the list of doctors
     */
    public void createDoctorQueue(ArrayList<BaseDoctor> doctors) {
        doctorCount = doctors.size();
        doctorQueue = new ArrayList<BaseDoctor>(doctors);
    }

    /**
     * Checks if the hospital contains a surgeon that can treat the patient's illness.
     * @param patient the patient that is examined
     * @return the surgeon or the default constructor if a surgeon can't be found
     */
    private BaseDoctor findSurgeon(Patient patient) {
        for (int j = 0; j < doctorCount; ++j) {
            if (doctorQueue.get(j).getTreatedIllnesses()
                    .contains(patient.getState().getIllnessName())
                    && doctorQueue.get(j).getIsSurgeon()) {
                return doctorQueue.get(j);
            }
        }

        return new BaseDoctor();
    }

    /**
     * Checks if the hospital contains a doctor that can treat the patient's illness.
     * @param patient the patient that is examined
     * @return the doctor or the default constructor if a doctor can't be found
     */
    private BaseDoctor findDoctor(Patient patient) {
        for (int j = 0; j < doctorCount; ++j) {
            if (doctorQueue.get(j).getTreatedIllnesses()
                    .contains(patient.getState().getIllnessName())) {
                return doctorQueue.get(j);
            }
        }

        return new BaseDoctor();
    }

    /**
     * Moves the doctor at the end of the queue.
     * @param doctor the doctor that has to be moved at the end of the queue
     */
    private void moveDoctor(BaseDoctor doctor) {
        doctorQueue.remove(doctor);
        doctorQueue.add(doctor);
    }

    /**
     * Checks if a patient has to be sent home, to another hospital, hospitalized or operated.
     * @param patient the patient that is examined
     * @param doctor the doctor that examines the patient
     * @param hospitalizedPatients the list of hospitalized patients
     */
    private void giveVerdict(Patient patient, BaseDoctor doctor,
                             ArrayList<Patient> hospitalizedPatients) {
        if (patient.getSimulationState().getValue().contains("home")) {
            patient.setSimulationState(doctor.getHomeMessage());
        } else if (patient.getInvestigationResult().equals(InvestigationResult.TREATMENT)) {
            patient.setSimulationState(doctor.getHomeMessage());
        } else if (patient.getInvestigationResult().equals(InvestigationResult.HOSPITALIZE)) {
            doctor.hospitalize(patient);
            hospitalizedPatients.add(patient);
        } else if (patient.getInvestigationResult().equals(InvestigationResult.OPERATE)) {
            doctor = findSurgeon(patient);

            if (!doctor.getIsSurgeon()) {
                patient.setSimulationState(State.OTHERHOSPITAL);
            } else {
                doctor.operate(patient);
                hospitalizedPatients.add(patient);
            }
        }

        // If the patient wasn't sent to another hospital, the doctor that examined him is
        // moved at the end of the doctor queue.
        if (!patient.getSimulationState().equals(State.OTHERHOSPITAL)) {
            moveDoctor(doctor);
        }
    }

    /**
     * Each patient is examined by a doctor. If the patient hasn't been investigated, the doctor
     * consults him and then sends him to the investigation queue. The doctor then gives his
     * verdict on what must happen to the patient.
     * @param patients the patients that have to be examined
     * @param hospitalizedPatients the list of hospitalized patients
     */
    public void consultPatients(ArrayList<Patient> patients,
                                ArrayList<Patient> hospitalizedPatients) {
        toInvestigatePatients.clear();
        addPatients(patients);
        Collections.sort(examinePatients, patientUrgencyComparator);

        for (int i = 0; i < examinePatients.size(); ++i) {
            Patient patient = examinePatients.get(i);

            BaseDoctor doctor = findDoctor(patient);
            patient.setDoctor(doctor);

            if (patient.getInvestigationResult()
                    .equals(InvestigationResult.NOT_DIAGNOSED)) {
                doctor.consult(patient);

                if (patient.getSimulationState().equals(State.INVESTIGATIONSQUEUE)) {
                    toInvestigatePatients.add(patient);
                }
            }

            giveVerdict(patient, doctor, hospitalizedPatients);
        }

        examinePatients.clear();
    }
}

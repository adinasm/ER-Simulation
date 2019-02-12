/**
 * @author Adina Smeu
 */

package hospital;

import java.util.ArrayList;
import java.util.EnumSet;

import enums.DoctorType;
import enums.IllnessType;
import enums.State;

public class BaseDoctor {
    private static final int FACTOR = 3;
    protected DoctorType type;
    protected boolean isSurgeon;
    protected int maxForTreatment;
    protected static final int T = 22;
    protected float c1;
    protected float c2;
    protected EnumSet<IllnessType> treatedIllnesses;
    protected ArrayList<Patient> patients = new ArrayList<Patient>();
    protected State homeMessage;
    protected State hospitalizeMessage;
    protected State operateMessage;

    public BaseDoctor() {

    }

    /**
     * BaseDoctor constructor.
     * @param t the type
     * @param illnesses the illnesses that the doctor can treat
     * @param surgeon true if it's a surgeon, false otherwise
     * @param maxTreatment maximum severity a patient can have in order to be sent home
     * @param c11 amelioration factor
     * @param c22 operation amelioration factor
     */
    BaseDoctor(DoctorType t, EnumSet<IllnessType> illnesses,
               boolean surgeon, int maxTreatment, float c11, float c22) {
        type = t;
        treatedIllnesses = illnesses;
        isSurgeon = surgeon;
        maxForTreatment = maxTreatment;
        c1 = c11;
        c2 = c22;
    }

    public final DoctorType getType() {
        return type;
    }

    public final void setType(DoctorType t) {
        type = t;
    }

    public final boolean getIsSurgeon() {
        return isSurgeon;
    }

    public final void setIsSurgeon(boolean s) {
        isSurgeon = s;
    }

    public final int getMaxForTreatment() {
        return maxForTreatment;
    }

    public final void setMaxForTreatment(int max) {
        maxForTreatment = max;
    }

    public final int getT() {
        return T;
    }

    public final ArrayList<Patient> getPatients() {
        return patients;
    }

    public final EnumSet<IllnessType> getTreatedIllnesses() {
        return treatedIllnesses;
    }

    public final State getHomeMessage() {
        return homeMessage;
    }

    /**
     * Computes the maximum between 2 integers.
     * @param a first integer
     * @param b second integer
     * @return the maximum
     */
    final int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * Checks if a patient can go home or if he needs to be investigated.
     * @param patient the patient that has to be consulted
     */
    public final void consult(Patient patient) {
        if (patient.getState().getSeverity() <= maxForTreatment) {
            patient.setSimulationState(homeMessage);
        } else {
            patient.setSimulationState(State.INVESTIGATIONSQUEUE);
        }
    }

    /**
     * Hospitalizes a patient, calculates the number of rounds that he has to spend
     * in the hospital and sets its state accordingly.
     * @param patient the patient that has to be hospitalized
     */
    public final void hospitalize(Patient patient) {
        int severity = patient.getState().getSeverity();

        patient.setRounds(Math.round(max(Math.round(severity * c1), FACTOR)));

        patient.setSimulationState(hospitalizeMessage);
        patients.add(patient);
    }

    /**
     * Operates a patient, hospitalizes him and sets its state accordingly.
     * @param patient the patient that has to be operated
     */
    public final void operate(Patient patient) {
        int severity = patient.getState().getSeverity();

        // Operates the patient.
        patient.getState().setSeverity(Math.round(severity - Math.round(severity * c2)));
        // Hospitalizes the patient.
        hospitalize(patient);
        patient.setSimulationState(operateMessage);
    }

    /**
     * Checks all the patients that are hospitalized by the doctor. If a patient is sent home,
     * his state is updated accordingly and he is removed from the hospitalized patients and
     * from the list kept by the doctor. If the severity of a patient's illness or the
     * number of rounds has reached 0, the patient has to be sent home the next round.
     * @param hospitalizedPatients the patients that are hospitalized
     */
    final void checkPatients(ArrayList<Patient> hospitalizedPatients) {
        for (int i = 0; i < patients.size(); ++i) {
            if (patients.get(i).isGoHome()) {
                patients.get(i).setSimulationState(State.HOME_DONE_TREATMENT);
                hospitalizedPatients.remove(patients.get(i));
                patients.remove(i);
                i--;
            } else if ((patients.get(i).getRounds() == 0)
                    || (patients.get(i).getState().getSeverity() <= 0)) {
                patients.get(i).setGoHome(true);
            }
        }
    }
}

/**
 * @author Adina Smeu
 */

package hospital;

import enums.State;
import enums.Urgency;
import tools.UrgencyEstimator;

public class Nurse {
    private static UrgencyEstimator urgencyEstimator = UrgencyEstimator.getInstance();
    /**
     * Calculates the urgency of a patient's illness using the urgency estimator and
     * sets its state to examination queue.
     * @param patient the patient that has to be consulted
     */
    public final void consult(Patient patient) {
        Urgency urgency = urgencyEstimator.estimateUrgency(patient.getState().getIllnessName(),
                patient.getState().getSeverity());
        patient.setUrgency(urgency);
        patient.setSimulationState(State.EXAMINATIONSQUEUE);
    }

    /**
     * Treats the patient by applying the amelioration factor and decrements the number of
     * rounds he has to stay hospitalized.
     * @param patient the patient that has to be trated
     */
    public final void treat(Patient patient) {
        int severity = patient.getState().getSeverity();

        patient.getState().setSeverity(severity - patient.getDoctor().getT());
        patient.setRounds(patient.getRounds() - 1);
    }
}

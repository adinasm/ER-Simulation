/**
 * @author Adina Smeu
 */

package hospital;

import enums.InvestigationResult;
import enums.State;

public class ERTechnician {
    private static final int C1 = 75;
    private static final int C2 = 40;

    /**
     * Investigates a patient based on the constants and the illness severity.
     * Sets the patient's state to examination queue.
     * @param patient the patient that has to be investigated
     */
    public final void investigate(Patient patient) {
        if (C1 < patient.getState().getSeverity()) {
            patient.setInvestigationResult(InvestigationResult.OPERATE);
        } else if (patient.getState().getSeverity() <= C2) {
            patient.setInvestigationResult(InvestigationResult.TREATMENT);
        } else {
            patient.setInvestigationResult(InvestigationResult.HOSPITALIZE);
        }

        patient.setSimulationState(State.EXAMINATIONSQUEUE);
    }
}

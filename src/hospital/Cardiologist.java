/**
 * @author Adina Smeu
 */

package hospital;

import enums.DoctorType;
import enums.IllnessType;
import enums.State;

import java.util.EnumSet;

/**
 * Inherits the BaseDoctor class.
 * Contains fields associated with the cardiologist type (amelioration factors,
 * treated illnesses, messages, whether it's a surgeon or not).
 */
public class Cardiologist extends BaseDoctor {
    private static final float C1_CARDIOLOGIST = (float) 0.4;
    private static final float C2_CARDIOLOGIST = (float) 0.1;

    public Cardiologist(boolean surgeon, int maxTreatment) {
        super(DoctorType.CARDIOLOGIST, EnumSet.of(IllnessType.HEART_ATTACK,
                IllnessType.HEART_DISEASE), surgeon, maxTreatment, C1_CARDIOLOGIST,
                C2_CARDIOLOGIST);

        homeMessage = State.HOME_CARDIO;
        hospitalizeMessage = State.HOSPITALIZED_CARDIO;
        operateMessage = State.OPERATED_CARDIO;
    }
}

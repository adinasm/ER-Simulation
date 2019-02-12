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
 * Contains fields associated with the general surgeon type (amelioration factors,
 * treated illnesses, messages, whether it's a surgeon or not).
 */
public class GeneralSurgeon extends BaseDoctor {
    private static final float C1_GENERALSURGEON = (float) 0.2;
    private static final float C2_GENERALSURGEON = (float) 0.2;

    public GeneralSurgeon(boolean surgeon, int maxTreatment) {
        super(DoctorType.GENERAL_SURGEON, EnumSet.of(IllnessType.ABDOMINAL_PAIN,
                IllnessType.BURNS, IllnessType.CAR_ACCIDENT, IllnessType.CUTS,
                IllnessType.SPORT_INJURIES), true, maxTreatment, C1_GENERALSURGEON,
                C2_GENERALSURGEON);

        homeMessage = State.HOME_SURGEON;
        hospitalizeMessage = State.HOSPITALIZED_SURGEON;
        operateMessage = State.OPERATED_SURGEON;
    }
}

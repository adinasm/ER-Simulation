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
 * Contains fields associated with the erphysician type (amelioration factors,
 * treated illnesses, messages, whether it's a surgeon or not).
 */
public class ERPhysician extends BaseDoctor {
    private static final float C1_ERPHYSICIAN = (float) 0.1;
    private static final float C2_ERPHYSICIAN = (float) 0.3;

    public ERPhysician(boolean surgeon, int maxTreatment) {
        super(DoctorType.ER_PHYSICIAN, EnumSet.of(IllnessType.ALLERGIC_REACTION,
                IllnessType.BROKEN_BONES, IllnessType.BURNS, IllnessType.CAR_ACCIDENT,
                IllnessType.CUTS, IllnessType.HIGH_FEVER, IllnessType.SPORT_INJURIES),
                surgeon, maxTreatment, C1_ERPHYSICIAN, C2_ERPHYSICIAN);

        homeMessage = State.HOME_ERPHYSICIAN;
        hospitalizeMessage = State.HOSPITALIZED_ERPHYSICIAN;
        operateMessage = State.OPERATED_ERPHYSICIAN;
    }
}

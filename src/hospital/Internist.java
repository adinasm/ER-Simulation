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
 * Contains fields associated with the internist type (amelioration factors,
 * treated illnesses, messages, whether it's a surgeon or not).
 */
public class Internist extends BaseDoctor {
    private static final float C1_INTERNIST = (float) 0.01;
    private static final float C2_INTERNIST = (float) 0;

    public Internist(boolean surgeon, int maxTreatment) {
        super(DoctorType.INTERNIST, EnumSet.of(IllnessType.ABDOMINAL_PAIN,
                IllnessType.ALLERGIC_REACTION, IllnessType.FOOD_POISONING,
                IllnessType.HEART_DISEASE, IllnessType.HIGH_FEVER,
                IllnessType.PNEUMONIA), surgeon, maxTreatment, C1_INTERNIST, C2_INTERNIST);

        homeMessage = State.HOME_INTERNIST;
        hospitalizeMessage = State.HOSPITALIZED_INTERNIST;
        operateMessage = State.NONE;
    }
}

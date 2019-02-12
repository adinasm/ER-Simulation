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
 * Contains fields associated with the neurologist type (amelioration factors,
 * treated illnesses, messages, whether it's a surgeon or not).
 */
public class Neurologist extends BaseDoctor {
    private static final float C1_NEUROLOGIST = (float) 0.5;
    private static final float C2_NEUROLOGIST = (float) 0.1;

    public Neurologist(boolean surgeon, int maxTreatment) {
        super(DoctorType.NEUROLOGIST, EnumSet.of(IllnessType.STROKE), surgeon,
                maxTreatment, C1_NEUROLOGIST, C2_NEUROLOGIST);

        homeMessage = State.HOME_NEURO;
        hospitalizeMessage = State.HOSPITALIZED_NEURO;
        operateMessage = State.OPERATED_NEURO;
    }
}

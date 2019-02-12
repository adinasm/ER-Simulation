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
 * Contains fields associated with the gastroenterologist type (amelioration factors,
 * treated illnesses, messages, whether it's a surgeon or not).
 */
public class Gastroenterologist extends BaseDoctor {
    private static final float C1_GASTROENTEROLOGIST = (float) 0.5;
    private static final float C2_GASTROENTEROLOGIST = (float) 0;

    public Gastroenterologist(boolean surgeon, int maxTreatment) {
        super(DoctorType.GASTROENTEROLOGIST, EnumSet.of(IllnessType.ABDOMINAL_PAIN,
                IllnessType.ALLERGIC_REACTION, IllnessType.FOOD_POISONING),
                surgeon, maxTreatment, C1_GASTROENTEROLOGIST, C2_GASTROENTEROLOGIST);

        homeMessage = State.HOME_GASTRO;
        hospitalizeMessage = State.HOSPITALIZED_GASTRO;
        operateMessage = State.NONE;
    }
}

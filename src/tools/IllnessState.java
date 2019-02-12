/**
 * @author Adina Smeu
 */

package tools;

import enums.IllnessType;

/**
 * Class that contains the name of the illness and its severity.
 */
public class IllnessState {
    private IllnessType illnessName;
    private int severity;

    public final IllnessType getIllnessName() {
        return illnessName;
    }

    public final void setIllnessName(IllnessType illnessType) {
        this.illnessName = illnessType;
    }

    public final int getSeverity() {
        return severity;
    }

    public final void setSeverity(int s) {
        severity = s;
    }
}

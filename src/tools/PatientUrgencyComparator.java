/**
 * @author Adina Smeu
 */

package tools;

import hospital.Patient;
import enums.Urgency;

import java.util.Comparator;

/**
 * Compares patients based on their urgency, severity and name.
 * Used for sorting them from the most to the least urgent illness, then from the one with
 * the biggest to the smallest severity and then in reverse alphabetical order.
 */
public class PatientUrgencyComparator implements Comparator<Patient> {
    @Override
    public final int compare(Patient p1, Patient p2) {
        Urgency u1 = p1.getUrgency();
        Urgency u2 = p2.getUrgency();
        int s1 = p1.getState().getSeverity();
        int s2 = p2.getState().getSeverity();
        String n1 = p1.getName();
        String n2 = p2.getName();

        if (u1.equals(u2)) {
            if (s1 == s2) {
                return n2.compareTo(n1);
            } else {
                return s2 - s1;
            }
        }

        if (u1.equals(Urgency.IMMEDIATE)) {
            return -1;
        }

        if (u1.equals(Urgency.URGENT)) {
            if (u2.equals(Urgency.IMMEDIATE)) {
                return 1;
            } else {
                return -1;
            }
        }

        if (u1.equals(Urgency.LESS_URGENT)) {
            if (u2.equals(Urgency.URGENT) || u2.equals(Urgency.IMMEDIATE)) {
                return 1;
            } else {
                return -1;
            }
        }

        return 1;
    }
}


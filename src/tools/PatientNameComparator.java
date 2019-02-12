/**
 * @author Adina Smeu
 */

package tools;

import hospital.Patient;

import java.util.Comparator;

/**
 * Compares 2 patients based on their names. Used for sorting them alphabetically.
 */
public class PatientNameComparator implements Comparator<Patient> {
    @Override
    public final int compare(Patient p1, Patient p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

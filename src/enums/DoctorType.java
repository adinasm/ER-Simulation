/**
 * @author Adina Smeu
 */

package enums;

/**
 * All the types of doctors that can consult the patients.
 */
public enum DoctorType {
    CARDIOLOGIST("Cardiologist"),
    ER_PHYSICIAN("ERPhysician"),
    GASTROENTEROLOGIST("Gastroenterologist"),
    INTERNIST("Internist"),
    NEUROLOGIST("Neurologist"),
    GENERAL_SURGEON("General Surgeon");

    private String value;

    DoctorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

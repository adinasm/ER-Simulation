/**
 * @author Adina Smeu
 */

package tools;

import hospital.BaseDoctor;
import hospital.Cardiologist;
import hospital.ERPhysician;
import hospital.Gastroenterologist;
import hospital.Internist;
import hospital.Neurologist;
import hospital.GeneralSurgeon;

public final class DoctorFactory {
    private static final DoctorFactory INSTANCE = new DoctorFactory();

    private DoctorFactory() {

    }

    /**
     *  Gets the instance.
     * @return the instance
     */
    public static DoctorFactory getInstance() {
        return  INSTANCE;
    }

    /**
     * Creates a new doctor based on the type of the doctor received as a parameter.
     * @param doctor a BaseDoctor whose type has to be used
     * @return the doctor that is an instance of the wanted type
     */
    public BaseDoctor createDoctor(BaseDoctor doctor) {
        switch (doctor.getType()) {
            case CARDIOLOGIST:
                return new Cardiologist(doctor.getIsSurgeon(), doctor.getMaxForTreatment());
            case ER_PHYSICIAN:
                return new ERPhysician(doctor.getIsSurgeon(), doctor.getMaxForTreatment());
            case GASTROENTEROLOGIST:
                return new Gastroenterologist(doctor.getIsSurgeon(), doctor.getMaxForTreatment());
            case INTERNIST:
                return new Internist(doctor.getIsSurgeon(), doctor.getMaxForTreatment());
            case NEUROLOGIST:
                return new Neurologist(doctor.getIsSurgeon(), doctor.getMaxForTreatment());
            case GENERAL_SURGEON:
                return new GeneralSurgeon(doctor.getIsSurgeon(), doctor.getMaxForTreatment());
            default:
                    return doctor;
        }
    }
}

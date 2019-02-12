package hospital;

/**
 * @author Adina Smeu
 */

import enums.InvestigationResult;
import enums.State;
import enums.Urgency;
import tools.IllnessState;

/**
 * Class that contains data about a patient, either read from the input file
 * (id, name, age, time, illness state) or generated during the simulation.
 */
public class Patient {
    private int id;
    private String name;
    private int age;
    private int time;
    private IllnessState state;
    private State simulationState = State.NONE;
    private Urgency urgency = Urgency.NOT_DETERMINED;
    private int rounds;
    private boolean goHome = false;
    private BaseDoctor doctor;
    private InvestigationResult investigationResult = InvestigationResult.NOT_DIAGNOSED;

    public final int getId() {
        return id;
    }

    public final void setId(final int i) {
        id = i;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String n) {
        name = n;
    }

    public final int getAge() {
        return age;
    }

    public final void setAge(final int a) {
        age = a;
    }

    public final int getTime() {
        return time;
    }

    public final void setTime(final int t) {
        time = t;
    }

    public final IllnessState getState() {
        return state;
    }

    public final void setState(IllnessState s) {
        state = s;
    }

    public final State getSimulationState() {
        return simulationState;
    }

    public final void setSimulationState(final State simulationState) {
        this.simulationState = simulationState;
    }

    public final Urgency getUrgency() {
        return urgency;
    }

    public final void setUrgency(final Urgency urgency) {
        this.urgency = urgency;
    }

    public final int getRounds() {
        return rounds;
    }

    public final void setRounds(int r) {
        rounds = r;
    }

    public final boolean isGoHome() {
        return goHome;
    }

    public final void setGoHome(boolean goHome) {
        this.goHome = goHome;
    }

    public final BaseDoctor getDoctor() {
        return doctor;
    }

    public final void setDoctor(BaseDoctor doctor) {
        this.doctor = doctor;
    }

    public final InvestigationResult getInvestigationResult() {
        return investigationResult;
    }

    public final void setInvestigationResult(InvestigationResult investigationResult) {
        this.investigationResult = investigationResult;
    }
}

# ER Simulation



    The project simulates an Emergency Room. The entrypoint is the Main class,
where the input is read from a file and an instance of the Hospital class is
created, as well as its observers, and it is organised in 5 packages:


## enums
- contains enums regarding the type of the doctor, the type of the illness a
  patient can have and a doctor can treat, the result of the investigation, the
  state of the patient during the simulation and the urgency of the illness;


## hospital
- contains the hospital and its employees:

- BaseDoctor
  - contains data about its type, amelioration factors, whether it's a 
    surgeon, treated illnesses and his patients;
  - inherited by the Cardiologist, ERPhysician, Internist,
    Gastroenterologist, GeneralSurgeon and Neurologist classes, that contain
    constants associated with each type;
  - can operate and hospitalize a patient;
  - checks each patient he hospitalized and decides if he can be sent home in
    the next round of the simulation;

- Nurse
  - estimates the urgency for a patient and treats a hospitalized one;

- Patient
  - contains data about a patient, either read from the input file(id, name,
    age, time, illness state) or generated during the simulation;

- ERTechnician
  - investigates a patient and gives a result based on the severity of the
    illness;

- Hospital
  - contains the doctors, the patients, the nurses and the investigators, as
    well as the queues (triage, investigations, examination);
  - all the data from the input file is read into this class;
  - is observed by the observes from the package with the same name;
  - the whole logic of the simulation is stored in this class: the creation
    of the hospital employees, the methods called in every round,
    corresponding to every queue (triage, examine, inspect) and regarding
    actions done by the nurses and doctors (the administration of the
    treatments and the checking of the hospitalized patients);


## observers
- contains classes that represent the observers of the Hospital class, each one
  printing information regarding the patients and their states, the nurses and
  the patients they treated, the doctors and their verdicts for every round of
  the simulation;


## queues
- contains classes that represent the different types of queues a patient can
  be a part of (they all use the SingletonPattern):

- TriageQueue
  - patients are either waiting to be consulted by nurses and to receive an
    urgency or they have already been consulted and have to go to the
    examination queue;

- ExaminationQueue
  - the patients that have to be examined come from the triage and
    investigations queues;
  - contains a queue of doctors ordered by availability;
  - each patient is assigned to a doctor that can treat his illness; if the
    patient came from the triage queue, the doctor consults the patient and
    sends him home or to the investigation queue, depending on the severity
    of the illness;
  - if the patient came from the investigations queue, the doctor acts upon
    the result given by the technician: the patient is either sent home,
    hospitalized, operated or sent to another hospital;
  - once a doctor has examined a patient, he's moved to the end of the doctor
    queue;

- InvestigationsQueue
  - the patients come from the examination queue and are sent back there
    after the ertechnicians investigate them.


## tools
- contains tools used in the simulation:

- DoctorFactory
  - creates doctors at the beginning of the simulation;
  - uses the Singleton Pattern;

- IllnessState
  - contains the name of an illness and its severity;

- PatientNameComparator
  - used for sorting patients alphabetically when the nursed administrate
    treatments and when the PatientPrinter prints them;

- PatientUrgencyComparator
  - used for sorting patients from the most to the least urgent illness, then
    from the one with the biggest to the smallest severity and then in
    reverse alphabetical order;
  - used in the examination and investigation queues;

- UrgencyEstimator
  - estimates the urgency based on the patient's illness and how severe the
    illness is manifested;
  - uses the Singleton Pattern;
  - used by the nurses in the triage queue.

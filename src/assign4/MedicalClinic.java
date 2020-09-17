/*File Name: MedicalClinic.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;

import java.util.ArrayList;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/* Represents a MedicalClinic to keep track of appointments between doctors and
 * patients
 */
public class MedicalClinic implements Serializable {
	private ArrayList<Appointment> appointments;
	private ArrayList<Patient> patients;
	private ArrayList<Doctor> doctors;

	private static int numberAppointments;
	private static int numberPatients;
	private static int numberDoctors;

	private final static int MAX_APPOINTMENTS = 5;
	private final static int MAX_PATIENTS = 5;
	private final static int MAX_DOCTORS = 5;

	// default constructor
	public MedicalClinic() {
		appointments = new ArrayList<Appointment>(); // initialize appointments arrayList
		patients = new ArrayList<Patient>(); // initialize patients arrayList
		doctors = new ArrayList<Doctor>(); // initialize doctors arrayList

		numberAppointments = 0; // appointments counter
		numberPatients = 3; // patients counter
		numberDoctors = 5; // doctors counter

		patients.add(new Patient("glo","glo",new HealthCardNumber("111111111"),new OurDate(2,11,2017)));
		patients.add(new MaternityPatient("john","john",new HealthCardNumber("221111111"),new OurDate(2,11,2017),new OurDate(2,03,2019),true));
		patients.add(new OutPatient("steph", "steph", new HealthCardNumber("331111111"), new OurDate(2,11,2018), 10, true));
		
		// add doctors to doctors ArrayList
		doctors.add(new Doctor("Vikash", "Singh", "Cardiologist"));
		doctors.add(new Doctor("Susan", "Miller", "Dentist"));
		doctors.add(new Doctor("Thanh", "Do", "Neurologist"));
		doctors.add(new Doctor("Francois", "DaSilva", "Surgeon"));
		doctors.add(new Doctor("Judy", "Chin", "Dermatologist"));
	}

	// method to write patients data to an output file
	public void filePatientsOut() {

		try {
			// Save object of patient data to a file
			FileOutputStream fileOutput = new FileOutputStream("PatientData.ser");
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

			// Write the patients output to a file.
			for (Patient p : patients)
				objectOutput.writeObject(p);

			fileOutput.close();
			objectOutput.close();

			System.out.println("Serialization was successful");

		}

		catch (IOException e) {
			System.out.println("Serialization process failed or has been interupted");
		}

	}

	// method to read patients data from a file
	public void filePatientsIn() {

		
		try {
			
			//ArrayList<Patient> patients = new ArrayList<Patient>();

			// Reading the object from a file
			
			ObjectInputStream objectInput = new ObjectInputStream(Files.newInputStream(Paths.get("PatientData.ser")));
			
			
			int index = 0;
			
			while(true) {
				
				patients.add((Patient) objectInput.readObject());
				index ++;
				
			}
	
		}

		catch (IOException e) {
			//System.out.println("IOException Caught");
			//e.printStackTrace();
		}

		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException caught");
		}

		//for (Patient p : patients)
			//System.out.println(p);
	}

	// adds a regular patient to the patients ArrayList
	public void addPatient(String lastName, String firstName, HealthCardNumber healthCardNumber, OurDate date) {
		patients.add(new Patient(lastName, firstName, healthCardNumber, date));
		numberPatients++;
	}

	// adds a Maternity patient to ArrayList
	public void addPatient(String lastName, String firstName, HealthCardNumber healthCardNumber, OurDate date,
			OurDate dueDate, boolean nutritionTesting) {
		patients.add(new MaternityPatient(lastName, firstName, healthCardNumber, date, dueDate, nutritionTesting));
		numberPatients++;
	}

	// adds an outPatient to the ArrayList
	public void addPatient(String lastName, String firstName, HealthCardNumber healthCardNumber, OurDate date,
			double distanceFromClinic, boolean mobility) {
		patients.add(new OutPatient(lastName, firstName, healthCardNumber, date, distanceFromClinic, mobility));
		numberPatients++;
	}

	// adds an appointment to the appointments ArrayList
	public void addAppointment(Patient patient, Doctor doctor, OurDate date) {
		appointments.add(new Appointment(patient, doctor, date));
		numberAppointments++;
	}

	// cancels appointment at the inputed index
	public void cancelAppointment(int index) {
		appointments.remove(index);
		numberAppointments--;
	}

	// returns number of appointments existing in the ArrayList
	public int getNumberAppointments() {
		return numberAppointments;
	}

	// returns number of patients existing in the ArrayList
	public int getNumberPatients() {
		return numberPatients;
	}

	// returns number of doctors existing in the ArrayList
	public int getNumberDoctors() {
		return numberDoctors;
	}

	// returns maximum number of doctors allowed in the ArrayList
	public int getMaxAppointments() {
		return MAX_APPOINTMENTS;
	}

	// returns maximum number of patients allowed in the ArrayList
	public int getMaxPatients() {
		return MAX_PATIENTS;
	}

	// returns maximum number of doctors allowed in the ArrayList
	public int getMaxDoctors() {
		return MAX_DOCTORS;
	}

	// returns doctors in the ArrayList
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	// returns patients in the ArrayList
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	// returns appointments in the ArrayList
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

}

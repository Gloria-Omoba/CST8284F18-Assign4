/*File Name: Appointment.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

// this class keeps record of appointments made in the clinic
public class Appointment implements Serializable {

	private Doctor doctor;
	private Patient patient;
	private OurDate appointmentDate;

	// default constructor
	public Appointment() {

		this(new Patient(), new Doctor(), new OurDate());
	}

	// overloaded constructor
	public Appointment(Patient patient, Doctor doctor, OurDate appointmentDate) {

		setPatient(patient);
		setDoctor(doctor);
		setAppointmentDate(appointmentDate);
	}

	// returns a doctors details
	public Doctor getDoctor() {
		return doctor;
	}

	// sets doctor
	private void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	// returns a patients details
	public Patient getPatient() {
		return patient;
	}

	// sets patient
	private void setPatient(Patient patient) {
		this.patient = patient;
	}

	// returns the date for an appointment
	public OurDate getAppointmentDate() {
		return appointmentDate;
	}

	// sets the date for an appointment
	private void setAppointmentDate(OurDate aDate) {

		// error checkingS
		GregorianCalendar appDate = new GregorianCalendar(aDate.getYear(), aDate.getMonth(), aDate.getDay());
		GregorianCalendar a = (GregorianCalendar) Calendar.getInstance();
		GregorianCalendar today = new GregorianCalendar(a.get(GregorianCalendar.YEAR),
				a.get(GregorianCalendar.MONTH) + 1, a.get(GregorianCalendar.DATE));

		if (appDate.equals(today))
			throw new MedicalClinicException("Appointment cannot be today\n");
		if (appDate.before(today))
			throw new MedicalClinicException("Appointment cannot be in the past\n");

		// sets appointment date if user input is appropriate
		this.appointmentDate = aDate;
	}

	// prints out the doctor, patient and date of an appointment
	public String toString() {
		return String.format("Appointment date: %s, %s, %s", this.appointmentDate, doctor, patient);
	}

	// returns hashcode value for class object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointmentDate == null) ? 0 : appointmentDate.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	// compares if two Appointment objects are equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Appointment))
			return false;

		Appointment app = (Appointment) obj; // casting

		return (this.doctor.equals(app.doctor) && this.patient.equals(app.patient)
				&& this.appointmentDate.equals(app.appointmentDate));
	}

}

/*File Name: Patient.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

//this class keeps record of patients that come into the Medical clinic
public class Patient implements Serializable{

	private String firstName;
	private String lastName;
	private HealthCardNumber healthCardNumber;
	private OurDate birthDate;

	// default constructor
	public Patient() {

		this("unknown", "unknown", new HealthCardNumber(), new OurDate());
	}

	// overloaded constructor
	public Patient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate birthDate) {
		setFirstName(firstName);
		setLastName(lastName);
		setHealthCardNumber(healthCardNumber);
		setBirthDate(birthDate);
	}

	// gets patients first name
	public String getFirstName() {
		return firstName;
	}

	// sets patients first name
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// gets patients last name
	public String getLastName() {
		return lastName;
	}

	// sets patients last name
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// gets patients card number
	public HealthCardNumber getHealthCardNumber() {
		return healthCardNumber;
	}

	// sets patients health card number
	private void setHealthCardNumber(HealthCardNumber healthCardNumber) {

		this.healthCardNumber = healthCardNumber;

	}

	// gets patients date of birth
	public OurDate getBirthDate() {
		return birthDate;
	}

	// sets patients date of birth
	private void setBirthDate(OurDate birthDate) {

		// error checking
		GregorianCalendar bDay = new GregorianCalendar(birthDate.getYear(), birthDate.getMonth(), birthDate.getDay());
		GregorianCalendar t = (GregorianCalendar) Calendar.getInstance();
		GregorianCalendar today = new GregorianCalendar(t.get(GregorianCalendar.YEAR),
				t.get(GregorianCalendar.MONTH) + 1, t.get(GregorianCalendar.DATE));

		if (bDay.equals(today))
			throw new MedicalClinicException("Birthday cannot be today\n");
		if (bDay.after(today))
			throw new MedicalClinicException("Birthday cannot be in the future\n");
		if (birthDate.getYear() < 1900)
			throw new MedicalClinicException("Congratulations Centenarian! Please sign up with Geriatrics\n");

		// sets date if input is appropriate
		this.birthDate = birthDate;
	}

	// prints the patients details
	public String toString() {
		return String.format("%s, %s, Health Card Number: %s, DOB: %s", this.firstName, this.lastName,
				this.healthCardNumber, this.birthDate);
	}

	// returns hashcode value for class object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((healthCardNumber == null) ? 0 : healthCardNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	// compares if two Patient objects are equal
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Patient))
			return false;

		Patient p = (Patient) obj;

		return (this.firstName.equals(p.firstName) && this.lastName.equals(p.lastName)
				&& this.healthCardNumber.equals(p.healthCardNumber) && this.birthDate.equals(p.birthDate));
	}
}

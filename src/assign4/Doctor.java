/*File Name: Doctor.Java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;
import java.io.Serializable;

/*this class keeps record of names and specialty of doctors 
that work in the clinic
*/
public class Doctor implements Serializable{

	private String firstName;
	private String lastName;
	private String specialty;

	// default constructor
	public Doctor() {
		this("unknown", "unknown", "unknown");
	}

	// overloaded constructor with three input parameters
	public Doctor(String firstName, String lastName, String specialty) {

		setFirstName(firstName);
		setLastName(lastName);
		setSpecialty(specialty);
	}

	// gets doctors first name
	public String getFirstName() {
		return firstName;
	}

	// sets doctors first name
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// gets doctors last name
	public String getLastName() {
		return lastName;
	}

	// sets doctors last name
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// gets doctors specialty
	public String getSpecialty() {
		return specialty;
	}

	// sets doctors specialty
	private void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	// prints Doctors details
	public String toString() {
		return String.format("Dr. %s %s, %s", this.firstName, this.lastName, this.specialty);
	}

	// compares if two Doctor objects are equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Doctor))
			return false;

		Doctor doc = (Doctor) obj; // casting

		return (this.firstName.equals(doc.firstName) && this.lastName.equals(doc.lastName)
				&& this.specialty.equals(doc.specialty));
	}

	// returns hashcode value for class object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
		return result;
	}
}

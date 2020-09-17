/*File Name: Patient.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;
import java.io.Serializable;

/*
 * this class is used to return a patient's health card number
*/
public class HealthCardNumber implements Serializable{

	String healthCardNumber;

	// default constructor
	public HealthCardNumber() {
		this("999999999");
	}

	// initial constructor
	public HealthCardNumber(String healthCardNumber) {
		setHealthCardNumber(healthCardNumber);
	}

	// returns health card number
	public String getHealthCardNumber() {
		return this.healthCardNumber;
	}

	// sets the health card number
	private void setHealthCardNumber(String healthCardNumber) {

		if (healthCardNumber.length() != 9)
			throw new MedicalClinicException("Health Card Number must be 9 digits in length");
		if (healthCardNumber.matches("[0-9]+")) {
			this.healthCardNumber = healthCardNumber;
		} else
			throw new MedicalClinicException("Health Card Number must contain only digits");

	}

	// returns hash code value for class object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((healthCardNumber == null) ? 0 : healthCardNumber.hashCode());
		return result;
	}

	// compares if two health card numbers are equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HealthCardNumber))
			return false;

		HealthCardNumber h = (HealthCardNumber) obj;

		return (this.healthCardNumber.equals(h.healthCardNumber));
	}

	@Override
	public String toString() {
		return String.format("%s", this.healthCardNumber);
	}
}

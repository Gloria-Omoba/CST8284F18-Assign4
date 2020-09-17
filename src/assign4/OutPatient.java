/*File Name: OutPatient.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;
import java.io.Serializable;

// this class keeps record of patients of the clinic that are not admitted in the clinic
public class OutPatient extends Patient implements Serializable{

	private double distanceFromClinic;
	private boolean mobility;

	// default constructor
	public OutPatient() {
		this("unknown", "unknown", new HealthCardNumber(), new OurDate(), -10.00, false);
	}

	// overloaded constructor
	public OutPatient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate birthDate,
			double distanceFromClinic, boolean mobility) {
		super(firstName, lastName, healthCardNumber, birthDate);
		setDistanceFromClinic(distanceFromClinic);
		setMobility(mobility);
	}

	// gets the patients distance from the clinic
	public double getDistanceFromClinic() {
		return distanceFromClinic;
	}

	// sets patients distance from clinic
	private void setDistanceFromClinic(double distanceFromClinic) {
		this.distanceFromClinic = distanceFromClinic;
	}

	// gets patients mobility
	public boolean getMobility() {
		return mobility;
	}

	// sets patients mobility
	private void setMobility(boolean mobility) {
		this.mobility = mobility;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, Health Card Number: %s, DOB: %s, Distance From Clinic:%.2f, Mobility:%b\n",
				getFirstName(), getLastName(), getHealthCardNumber(), getBirthDate(), this.distanceFromClinic,
				this.mobility);
	}

	// returns hashcode value for class object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(distanceFromClinic);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (mobility ? 1231 : 1237);
		return result;
	}

	// compares if two OutPatient objects are equal
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OutPatient))
			return false;

		OutPatient outP = (OutPatient) obj; // casting

		return (super.equals(outP) && this.distanceFromClinic == outP.distanceFromClinic
				&& this.mobility == outP.mobility);
	}
}

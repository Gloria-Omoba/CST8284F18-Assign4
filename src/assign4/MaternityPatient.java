/*File Name: MaternityPatient.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

//this class keeps record of Maternity patients in the clinic 
public class MaternityPatient extends Patient implements Serializable {

	private OurDate dueDate;
	private boolean nutritionTesting;

	// default constructor
	public MaternityPatient() {

		this("unknown", "unknown", new HealthCardNumber(), new OurDate(), new OurDate(), false);
		
	}

	// overloaded constructor
	public MaternityPatient(String firstName, String lastName, HealthCardNumber healthCardNumber, OurDate birthDate,
			OurDate dueDate, boolean nutritionTesting) {
		super(firstName, lastName, healthCardNumber, birthDate);
		setDueDate(dueDate);
		setNutritionTesting(nutritionTesting);
	}

	public OurDate getDueDate() {
		return dueDate;
	}

	private void setDueDate(OurDate dueDate) {

		// error checking
		GregorianCalendar dDay = new GregorianCalendar(dueDate.getYear(), dueDate.getMonth(), dueDate.getDay());
		GregorianCalendar t = (GregorianCalendar) Calendar.getInstance();
		GregorianCalendar today = new GregorianCalendar(t.get(GregorianCalendar.YEAR),
				t.get(GregorianCalendar.MONTH) + 1, t.get(GregorianCalendar.DATE));

		if (dDay.before(today))
			throw new MedicalClinicException("Due date cannot be in the past\n");

		this.dueDate = dueDate;
	}

	public boolean getNutritionTesting() {
		return nutritionTesting;
	}

	private void setNutritionTesting(boolean nutritionTesting) {
		this.nutritionTesting = nutritionTesting;
	}

	// prints details of a Maternity patient
	@Override
	public String toString() {

		return String.format("%s, %s, Health Card Number: %s, DOB: %s, Due date:%s, Nutrition Testing:%b",
				getFirstName(), getLastName(), getHealthCardNumber(), getBirthDate(), this.dueDate,
				this.nutritionTesting);
	}

	// returns hashcode value for class object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + (nutritionTesting ? 1231 : 1237);
		return result;
	}

	// compares if two MaternityPatient objects are equal
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MaternityPatient))
			return false;

		MaternityPatient matP = (MaternityPatient) obj;
		return (super.equals(matP) && this.dueDate.equals(matP.dueDate)
				&& this.nutritionTesting == matP.nutritionTesting);
	}

}

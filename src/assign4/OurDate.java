/*File Name: OurDate.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;

//import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

/*this class is used to get patients birthdate 
 * and appointment date
 */
public class OurDate implements Serializable {

	private int day;
	private int month;
	private int year;

	// private static final Calendar CALENDER = Calendar.getInstance();
	private static final GregorianCalendar CALENDER = (GregorianCalendar) GregorianCalendar.getInstance();

	// default constructor
	public OurDate() {
		this(CALENDER.get(GregorianCalendar.DATE), CALENDER.get(GregorianCalendar.MONTH) + 1,
				CALENDER.get(GregorianCalendar.YEAR));
	}

	// initial constructor
	public OurDate(int day, int month, int year) {
		setYear(year);
		setMonth(month);
		setDay(day);

	}

	// gets the value of day
	public int getDay() {
		return day;
	}

	// sets the value of day
	private void setDay(int day) {

		// exception handling
		if ((getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11) && (day < 1 || day > 30))
			throw new MedicalClinicException("Day must be between 1 and 30\n");
		if (day < 1 || day > 31)
			throw new MedicalClinicException("Day must be between 1 and 31\n");
		if (getMonth() == 2 && CALENDER.isLeapYear(getYear()) == true && (day < 1 || day > 29))
			throw new MedicalClinicException("Day must be between 1 and 29\n");
		if (getMonth() == 2 && CALENDER.isLeapYear(getYear()) == false && (day < 1 || day > 28))
			throw new MedicalClinicException("Day must be between 1 and 28\n");

		// sets day after exception handling
		this.day = day;
	}

	// gets the value of month
	public int getMonth() {
		return month;
	}

	// sets the value of month
	private void setMonth(int month) {

		if (month < 1 || month > 12)
			throw new MedicalClinicException("Month should be between 1 and 12");

		this.month = month;
	}

	// gets the value of year
	public int getYear() {
		return year;
	}

	// sets the value of year
	private void setYear(int year) {
		this.year = year;
	}

	// prints the date
	public String toString() {

		return String.format("%d/%d/%d", this.day, this.month, this.year);
	}

	// returns hashcode value for class object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	// compares if two OurDate objects are equal
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OurDate))
			return false;

		OurDate d = (OurDate) obj; // casting

		return (this.getDay() == d.getDay() && this.getMonth() == d.getMonth() && this.getYear() == d.getYear());
	}

}

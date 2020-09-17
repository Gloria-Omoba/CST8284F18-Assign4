/*File Name: MedicalClinicUserInterface.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;

//exception handling class which accepts and prints out unique input message
public class MedicalClinicException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// default constructor
	public MedicalClinicException() {
		super();
	}

	// initial constructor
	public MedicalClinicException(String message) {
		super(message);
	}

}

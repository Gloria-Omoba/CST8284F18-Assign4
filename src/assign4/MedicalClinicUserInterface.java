/*File Name: MedicalClinicUserInterface.java
Course Name: CST8284
Lab Section: 313
Student Name: Gloria Omoba
Date: 26 November, 2018
 */

package assign4;

import java.util.ArrayList;
import java.util.Scanner;

/*this class prints the menu and takes in all user interaction for a medical clinic
 */
public class MedicalClinicUserInterface {

	private static final int ADD_PATIENT = 1;
	private static final int ADD_APPOINTMENT = 2;
	private static final int CANCEL_APPOINTMENT = 3;
	private static final int LIST_APPOINTMENTS = 4;
	private static final int WRITE_PATIENT_DATA = 5;
	private static final int LOAD_PATIENT_DATA = 6;
	private static final int QUIT = 7;

	private MedicalClinic clinic;
	private Scanner in;

	// default constructor for class
	public MedicalClinicUserInterface() {

		clinic = new MedicalClinic();
		in = new Scanner(System.in);
	}

	// main method
	public static void main(String[] args) {

		MedicalClinicUserInterface user = new MedicalClinicUserInterface();
		user.menu();
	}

	// prints out menu options
	public void menu() {
		int option;

		do {
			System.out.println(
					"\nPlease make a choice:\n1. Enter a new patient\n2. Make an appointment for a patient\n3. Cancel an appointment for a patient\n4. List all appointments\n5. Write Patient data to file\n6. Load Patient data\n7. Quit");
			option = in.nextInt();

			if (option == ADD_PATIENT) {
				addPatient();
			} else if (option == ADD_APPOINTMENT) {
				addAppointment();
			} else if (option == CANCEL_APPOINTMENT) {
				cancelAppointment();
			} else if (option == LIST_APPOINTMENTS) {
				listAppointments();
			} else if (option == WRITE_PATIENT_DATA) {
				writePatientsOut();
			} else if (option == LOAD_PATIENT_DATA) {
				readPatientsIn();
			} else if (option == QUIT) {
				System.out.print("Goodbye!");
				return;
			} else if (option == 9) {
				for (Patient pat : clinic.getPatients()) {
					System.out.println(pat.toString());
				}
			} else if (option != ADD_PATIENT || option != ADD_APPOINTMENT || option != CANCEL_APPOINTMENT
					|| option != LIST_APPOINTMENTS || option != WRITE_PATIENT_DATA || option != LOAD_PATIENT_DATA
					|| option != QUIT || option != 9) {
				System.out.print(option + " is invalid. Please input a valid option.\n");
			}

		} while (option != QUIT);
	}

	// adds patients to patients ArrayList
	public void addPatient() {

		String firstName;
		String lastName;
		String hcn;
		HealthCardNumber healthCard = new HealthCardNumber();
		String bDate;
		OurDate birthDate;
		String dDate;
		OurDate dueDate;
		boolean nutriTest;
		double distanceFromClinic;
		boolean isMobile;

		if (clinic.getNumberPatients() == clinic.getMaxPatients()) {
			System.out.println("Cannot add anymore patients\nGoodbye");
			return;
		} else {

			System.out.print("Enter first name: ");
			firstName = in.next();

			System.out.print("Enter last name: ");
			lastName = in.next();

			// prompts user for valid health card number
			boolean testHcn = false;
			do {

				System.out.print("\nEnter health card number:");
				hcn = in.next();

				for (int i = 0; i < clinic.getNumberPatients(); i++) {
					if ((clinic.getNumberPatients() != 0)
							&& (clinic.getPatients().get(i).getHealthCardNumber().healthCardNumber.equals(hcn))) {
						System.out.println("\nHealth number already in the System\n");
						// testHcn = true;
						return;
					}
				}

				// exception handling
				try {
					healthCard = new HealthCardNumber(hcn);
					testHcn = true;
				} catch (MedicalClinicException e) {
					System.out.println(e.getMessage());

				}

			} while (testHcn == false);

			boolean isBday = false;
			int day;
			int month;
			int year;

			// prompts user for valid birth date if user input does not match criteria
			do {
				System.out.print("\nEnter birth date:");
				bDate = in.next();

				day = Integer.parseInt(bDate.substring(0, 2));
				month = Integer.parseInt(bDate.substring(2, 4));
				year = Integer.parseInt(bDate.substring(4, 8));

				// asks for type of patient type and prompts for valid input if
				// selected option is not on the list
				boolean validPatient = true;
				do {
					System.out.println(
							"Enter the Type of Patient: \n1. Maternity Patient \n2. OutPatient \n3. Regular Patient");
					int patientType = in.nextInt();

					switch (patientType) {

					case 1:

						boolean isDueDate = false;
						int d;
						int m;
						int y;

						do {
							System.out.print("Enter due date:");
							dDate = in.next();

							d = Integer.parseInt(dDate.substring(0, 2));
							m = Integer.parseInt(dDate.substring(2, 4));
							y = Integer.parseInt(dDate.substring(4, 8));

							// error checking
							try {
								dueDate = new OurDate(d, m, y);
								MaternityPatient x = new MaternityPatient("unknown", "unknown", new HealthCardNumber(),
										new OurDate(day, month, year), new OurDate(d, m, y), false);
								isDueDate = true;
							} catch (MedicalClinicException e) {
								System.out.println(e.getMessage());

							}
						} while (isDueDate == false);

						System.out.print("Enter nutrition testing(T/F):");
						String nutriT = in.next();

						int nut = 0;
						nutriTest = false;

						do {
							if (nutriT.equalsIgnoreCase("T")) {
								nutriTest = true;
								nut = 1;
							} else if (nutriT.equalsIgnoreCase("F")) {
								nutriTest = false;
								nut = 1;
							} else
								nut = 0;
						} while (nut == 0);

						try {
							birthDate = new OurDate(day, month, year);
							dueDate = new OurDate(d, m, y);
							clinic.addPatient(lastName, firstName, healthCard, birthDate, dueDate, nutriTest);
							isBday = true;
						} catch (MedicalClinicException e) {
							System.out.println(e.getMessage());
						}
						validPatient = true;
						break;

					case 2:
						System.out.print("Enter distance from Clinic:");
						distanceFromClinic = in.nextDouble();

						int isMob = 0;
						isMobile = false;

						do {
							System.out.print("Enter patient's mobility(Y/N):");

							String mobile = in.next();

							if (mobile.equalsIgnoreCase("Y")) {
								isMobile = true;
								isMob = 1;
							} else if (mobile.equalsIgnoreCase("N")) {
								isMobile = false;
								isMob = 1;
							} else
								isMob = 0;
						} while (isMob == 0);

						try {
							birthDate = new OurDate(day, month, year);
							clinic.addPatient(lastName, firstName, healthCard, birthDate, distanceFromClinic, isMobile);
							isBday = true;
						} catch (MedicalClinicException e) {
							System.out.println(e.getMessage());
						}

						validPatient = true;
						break;

					case 3:

						try {
							birthDate = new OurDate(day, month, year);
							clinic.addPatient(lastName, firstName, healthCard, birthDate);
							isBday = true;
						} catch (MedicalClinicException e) {
							System.out.println(e.getMessage());
						}

						validPatient = true;
						break;
					default:
						System.out.println(patientType + "is an invalid selection. Please Enter a valid option.");
						validPatient = false;
					}
				} while (validPatient == false);
			} while (isBday == false);
		}
	}

	// adds appointments to appointments arrayList
	public void addAppointment() {
		int appointmentDay;
		int appointmentMonth;
		int appointmentYear;
		int selectDoctor;
		String hCardNumber;
		Patient patient;
		Doctor doctor;
		OurDate appointmentDate;

		if (clinic.getNumberAppointments() == clinic.getMaxAppointments()) {
			System.out.println("Cannot make any more appointments.");
			return;
		} else {

			boolean testH = false;
			do {
				System.out.print("Enter health card number: ");
				hCardNumber = in.next();

				try {
					HealthCardNumber healthCard = new HealthCardNumber(hCardNumber);
					testH = true;
				}

				catch (MedicalClinicException e) {
					System.out.println(e.getMessage());
				}

			} while (testH == false);

			for (Patient pExist : clinic.getPatients()) {

				if (hCardNumber.equals(pExist.getHealthCardNumber().healthCardNumber)) {
					System.out.print(pExist.toString());
					patient = pExist;

					boolean appExist = false;
					do {
						System.out.print("\n\n1. Vikash Singh\n2. Susan Miller\n3. Thanh Do\n4. Francois DaSilva"
								+ "\n5. Judy Chin\nEnter number for doctor selection: ");
						selectDoctor = in.nextInt();

						boolean appDayTest = false;
						do {

							System.out.print("Enter desired appointment date DDMMYYYY:");
							String appointDate = in.next();

							appointmentDay = Integer.parseInt(appointDate.substring(0, 2));
							appointmentMonth = Integer.parseInt(appointDate.substring(2, 4));
							appointmentYear = Integer.parseInt(appointDate.substring(4, 8));

							// checks for appointment conflict
							if (clinic.getNumberAppointments() == 0) {
								appExist = false;
							} else {
								for (int x = 0; x < clinic.getNumberAppointments(); x++) {
									if ((clinic.getAppointments().get(x).getAppointmentDate()
											.getDay() == appointmentDay)
											&& (clinic.getAppointments().get(x).getAppointmentDate()
													.getMonth() == appointmentMonth)
											&& (clinic.getAppointments().get(x).getAppointmentDate()
													.getYear() == appointmentYear)
											&& (clinic.getAppointments().get(x).getDoctor() == clinic.getDoctors()
													.get(selectDoctor - 1))) {
										appExist = true;
										System.out.println("Doctor not available");
									}
								}
							}

							// exception handling to check acceptable date values and if appointment is the
							// same day or in the past
							try {

								// gets selected doctor
								doctor = clinic.getDoctors().get(selectDoctor - 1);
								appointmentDate = new OurDate(appointmentDay, appointmentMonth, appointmentYear);

								// add appointment into the ArrayList
								clinic.addAppointment(patient, doctor, appointmentDate);
								appDayTest = true;
							} catch (MedicalClinicException e) {
								System.out.println(e.getMessage());
							}

						} while (appDayTest == false);

					} while (appExist == true);
				}
			}
		}
	}

	// deletes an appointment in the appointments Array using patients Health Card
	// Number
	public void cancelAppointment() {

		String hCardNo;
		boolean hcn = false;

		do {
			System.out.print("Enter Health Card Number:");
			hCardNo = in.next();

			try {
				HealthCardNumber healthC = new HealthCardNumber(hCardNo);
				hcn = true;
			} catch (MedicalClinicException e) {
				System.out.println(e.getMessage());
			}
		} while (hcn == false);

		// comparing patients, doctors and appointment date
		for (Appointment x : clinic.getAppointments()) {

			if (hCardNo.equals(x.getPatient().getHealthCardNumber().healthCardNumber)) {
				System.out.print(x.getPatient().toString());

				Patient p = x.getPatient(); // patient to compare

				System.out.println("\n1. Vikash Singh\n2. Susan Miller\n3. Thanh Do\n4. Francois DaSilva"
						+ "\n5. Judy Chin\nEnter number for doctor selection: ");
				int selectDoctor2 = in.nextInt();
				Doctor d = clinic.getDoctors().get(selectDoctor2 - 1); // doctor to compare

				boolean invalidDate = false;
				do {
					System.out.print("Enter appointment date DDMMYYYY:");
					String appointDate2 = in.next();

					int appointDay = Integer.parseInt(appointDate2.substring(0, 2));
					int appointMonth = Integer.parseInt(appointDate2.substring(2, 4));
					int appointYear = Integer.parseInt(appointDate2.substring(4, 8));

					try {
						OurDate newD = new OurDate(appointDay, appointMonth, appointYear); // date to compare

						// cancel appointment if provided details are similar
						if (x.getPatient().equals(p) && x.getDoctor().equals(d)
								&& x.getAppointmentDate().equals(newD)) {
							int y = clinic.getAppointments().indexOf(x);
							clinic.cancelAppointment(y);
							System.out.println("Appointment Cancelled\n");
							break;
						}

						invalidDate = true;
					} catch (MedicalClinicException e) {
						System.out.print(e.getMessage());
					}
				} while (invalidDate == false);
			}
		}
	}

	// prints appointments in the appointment array
	public void listAppointments() {
		if (clinic.getNumberAppointments() == 0)
			System.out.println("No appointments Available");

		else {

			for (int i = 0; i < clinic.getNumberAppointments(); i++) {
				System.out.println(clinic.getAppointments().get(i).toString());
			}
		}
	}

	// method to invoke filePatientsOut() in Medical clinic
	public void writePatientsOut() {
		clinic.filePatientsOut();
	}

	// method to invoke filePatientsIn() in Medical clinic
	public void readPatientsIn() {
		clinic.filePatientsIn();
	}
}

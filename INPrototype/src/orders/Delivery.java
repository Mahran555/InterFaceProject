package orders;

/**
 * This class is a delivery information class, to save the delivery information of the customer
 * @author Mahran
 *
 */
public class Delivery {
	/**
	 * to save the first name
	 */
	private String firstName;
	/**
	 * to save the last name
	 */
	private String lastName;
	/**
	 * to save the phone number
	 */
	private String phoneNumber;
	/**
	 * to save the house number
	 */
	private String houseNumber;
	/**
	 * to save the street address
	 */
	private String street;
	/**
	 * constructor to initialize the data
	 * @param firstName first name of the customer 
	 * @param lastName last name of the customer 
	 * @param phoneNumber phone number of the customer 
	 * @param houseNumber house number of the customer 
	 * @param street street address of the customer 
	 */
	public Delivery(String firstName, String lastName, String phoneNumber, String houseNumber, String street) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.houseNumber = houseNumber;
		this.street = street;
	}
	
	/**
	 * Method to get the first name
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to get the last name
	 * @return LastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to get the phone number
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Method to get the house number
	 * @return houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * Method to get the street
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Method to clear all fields in that class
	 */
	public void clear() {
		firstName=null;
		lastName=null;
		phoneNumber=null;
		houseNumber=null;
		street=null;
	}
	
}

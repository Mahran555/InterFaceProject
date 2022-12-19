package orders;

public class Delivery {
	private String firstName;
	private String LastName;
	private String phoneNumber;
	private String houseNumber;
	private String street;
	public Delivery(String firstName, String lastName, String phoneNumber, String houseNumber, String street) {
		super();
		this.firstName = firstName;
		this.LastName = lastName;
		this.phoneNumber = phoneNumber;
		this.houseNumber = houseNumber;
		this.street = street;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void clear() {
		firstName=null;
		LastName=null;
		phoneNumber=null;
		houseNumber=null;
		street=null;
	}
	
}

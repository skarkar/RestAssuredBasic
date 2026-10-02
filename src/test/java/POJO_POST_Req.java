import java.util.Map;

public class POJO_POST_Req {

	String firstname;
	String lastname;
	int totalprice;
	boolean depositepaid;
	Map<?, ?> bookingdates;
	String additionalneeds[];

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public boolean isDepositepaid() {
		return depositepaid;
	}
	public void setDepositepaid(boolean depositepaid) {
		this.depositepaid = depositepaid;
	}
	public Map<?,?> getBookingdates() {
		return bookingdates;
	}
	public void setBookingdates(Map<?, ?> bookingdates) {
		this.bookingdates = bookingdates;
	}
	public String[] getAdditionalneeds() {
		return additionalneeds;
	}
	public void setAdditionalneeds(String[] additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
}

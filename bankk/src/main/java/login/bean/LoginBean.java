package login.bean;

public class LoginBean {
	private String username;
	private String password;
	private String contactno;
	private String rusername;
	private Float rbalance;
	private Float ubalance;
	private Float amount;
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getContactno() {
		return contactno;
	}
	public Float getRbalance() {
		return rbalance;
	}
	public void setRbalance(Float rbalance) {
		this.rbalance = rbalance;
	}
	public Float getUbalance() {
		return ubalance;
	}
	public void setUbalance(Float ubalance) {
		this.ubalance = ubalance;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getRusername() {
		return rusername;
	}
	public void setRusername(String rusername) {
		this.rusername = rusername;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

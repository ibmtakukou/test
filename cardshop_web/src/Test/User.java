package Test;

import java.io.Serializable;

public class User implements Serializable{
	private String id, pw;

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw() {
		this.pw = pw;
	}

	public  User() {
		this.id="";
		this.pw="";
	}

	public  User(String id, String pw) {
		this.id=id;
		this.pw=pw;
	}
}

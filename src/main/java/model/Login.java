package model;

import java.util.ArrayList;

public class Login {
	
	private String name;
	private String logCategory;
	
	
	private String logDateRangeFrom;
	private String logDateRangeTo;
	
	
	public String getLogDateRangeFrom() {
		return logDateRangeFrom;
	}
	public void setLogDateRangeFrom(String logDateRangeFrom) {
		this.logDateRangeFrom = logDateRangeFrom;
	}
	public String getLogDateRangeTo() {
		return logDateRangeTo;
	}
	public void setLogDateRangeTo(String logDateRangeTo) {
		this.logDateRangeTo = logDateRangeTo;
	}
	public String getLogCategory() {
		return logCategory;
	}
	public void setLogCategory(String logCategory) {
		this.logCategory = logCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String loginID;
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Login [name=" + name + ", logCategory=" + logCategory + ", logDateRangeFrom=" + logDateRangeFrom
				+ ", logDateRangeTo=" + logDateRangeTo + ", loginID=" + loginID + ", password=" + password + ", role="
				+ role + ", currentPassword=" + currentPassword + ", newPassword=" + newPassword + ", email=" + email
				+ ", accessInvList=" + accessInvList + "]";
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	private String password;
	private String role;
	private String currentPassword;
	private String newPassword;
	private String email;
	private ArrayList<String> accessInvList;
	public ArrayList<String> getAccessInvList() {
		return accessInvList;
	}
	public void setAccessInvList(ArrayList<String> accessInvList) {
		this.accessInvList = accessInvList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	

}

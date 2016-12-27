package model;

import java.util.ArrayList;

public class Alert {

	private ArrayList<String> userName;
	private ArrayList<String> invName;
	private ArrayList<String> dayname;
	private String alertName;
	private String scheduleTIme;
	private String alertMinutes;
	private String alertHours;
	private String alertDay;
	
	public ArrayList<String> getDayname() {
		return dayname;
	}
	public void setDayname(ArrayList<String> dayname) {
		this.dayname = dayname;
	}
	public String getScheduleTIme() {
		return scheduleTIme;
	}
	public void setScheduleTIme(String scheduleTIme) {
		this.scheduleTIme = scheduleTIme;
	}
	public String getAlertMinutes() {
		return alertMinutes;
	}
	public void setAlertMinutes(String alertMinutes) {
		this.alertMinutes = alertMinutes;
	}
	public String getAlertHours() {
		return alertHours;
	}
	public void setAlertHours(String alertHours) {
		this.alertHours = alertHours;
	}
	public String getAlertDay() {
		return alertDay;
	}
	public void setAlertDay(String alertDay) {
		this.alertDay = alertDay;
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}
	public String getMonthNumber() {
		return monthNumber;
	}
	public void setMonthNumber(String monthNumber) {
		this.monthNumber = monthNumber;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	private String monthDate;
	private String monthNumber;
	private String monthName;
	
	public String getAlertTime() {
		return scheduleTIme;
	}
	public void setAlertTime(String alertTime) {
		this.scheduleTIme = alertTime;
	}
	public ArrayList<String> getUserName() {
		return userName;
	}
	public void setUserName(ArrayList<String> userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Alert [userName=" + userName + ", invName=" + invName + ", dayname=" + dayname + ", alertName="
				+ alertName + ", scheduleTIme=" + scheduleTIme + ", alertMinutes=" + alertMinutes + ", alertHours="
				+ alertHours + ", alertDay=" + alertDay + ", monthDate=" + monthDate + ", monthNumber=" + monthNumber
				+ ", monthName=" + monthName + "]";
	}
	public ArrayList<String> getInvName() {
		return invName;
	}
	public void setInvName(ArrayList<String> invName) {
		this.invName = invName;
	}
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	
	
}

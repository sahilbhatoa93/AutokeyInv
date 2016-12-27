package model;

public class LogObject {
	
	
	@Override
	public String toString() {
		return "LogObject [sku=" + sku + ", brand=" + brand + ", category=" + category + ", subCategory=" + subCategory
				+ ", invFrom=" + invFrom + ", invTo=" + invTo + ", reason=" + reason + ", activity=" + activity
				+ ", date=" + date + ", user=" + user + ", number=" + number + ", model=" + model + ", shipperName="
				+ shipperName + ", transferID=" + transferID + ", Part1=" + Part1 + ", technician=" + technician
				+ ", itemCode=" + itemCode + ", logDateRangeFrom=" + logDateRangeFrom + ", logDateRangeTo="
				+ logDateRangeTo + ", keyGenerationID=" + keyGenerationID + "]";
	}
	private String sku;
	private String brand;
	private String category;
	private String subCategory;
	private String invFrom;
	private String invTo;
	private String reason;
	private String activity;
	private String date;
	private String user;
	private String number;
	private String model;
	private String shipperName;
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	private String transferID;
	private String Part1;
	private String technician;
	private String itemCode;

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
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	private String keyGenerationID;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getInvFrom() {
		return invFrom;
	}
	public void setInvFrom(String invFrom) {
		this.invFrom = invFrom;
	}
	public String getInvTo() {
		return invTo;
	}
	public void setInvTo(String invTo) {
		this.invTo = invTo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTransferID() {
		return transferID;
	}
	public void setTransferID(String transferID) {
		this.transferID = transferID;
	}
	public String getPart1() {
		return Part1;
	}
	public void setPart1(String part1) {
		Part1 = part1;
	}
	
	public String getKeyGenerationID() {
		return keyGenerationID;
	}
	public void setKeyGenerationID(String keyGenerationID) {
		this.keyGenerationID = keyGenerationID;
	}

}

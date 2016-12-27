package model;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class Item {
	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	private String itemCode;
	private String reason;
	private String invCode;
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private String invName;
	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}

	private String noOfItems;
	public String getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(String noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String getInvCode() {
		return invCode;
	}

	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	

	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", reason=" + reason + ", invCode=" + invCode + ", invName=" + invName
				+ ", noOfItems=" + noOfItems + ", restockLimit=" + restockLimit + ", rack=" + rack + ", brandCode="
				+ brandCode + ", shelf=" + shelf + ", model=" + model + ", column=" + column + ", compartment="
				+ compartment + ", description=" + description + ", brand=" + brand + ", sku=" + sku + ", productNotes="
				+ productNotes + ", ffcId=" + ffcId + ", buttonConfiguration=" + buttonConfiguration + ", emergencyKey="
				+ emergencyKey + ", batteryPartNumber=" + batteryPartNumber + ", noOfButton=" + noOfButton + ", iC="
				+ iC + ", imageURLByteArray=" + Arrays.toString(imageURLByteArray) + ", subCategoryName="
				+ subCategoryName + ", categoryName=" + categoryName + ", scheduleTIme=" + scheduleTIme + "]";
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getCompartment() {
		return compartment;
	}

	public void setCompartment(String compartment) {
		this.compartment = compartment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductNotes() {
		return productNotes;
	}

	public void setProductNotes(String productNotes) {
		this.productNotes = productNotes;
	}

	public String getFfcId() {
		return ffcId;
	}

	public void setFfcId(String ffcId) {
		this.ffcId = ffcId;
	}

	public String getButtonConfiguration() {
		return buttonConfiguration;
	}

	public void setButtonConfiguration(String buttonConfiguration) {
		this.buttonConfiguration = buttonConfiguration;
	}

	public String getEmergencyKey() {
		return emergencyKey;
	}

	public void setEmergencyKey(String emergencyKey) {
		this.emergencyKey = emergencyKey;
	}
private String restockLimit;
	public String getRestockLimit() {
	return restockLimit;
}

public void setRestockLimit(String restockLimit) {
	this.restockLimit = restockLimit;
}

	private String rack;
	private String brandCode;
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	private String shelf;
	private String model;
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	private String column;
	private String compartment;
	private String description;
	private String brand;
	private String sku;
	private String productNotes;
	private String ffcId;
	private String buttonConfiguration;
	private String emergencyKey;
	private String batteryPartNumber;
	private String noOfButton;
	private String iC;
	private String fromYear;
	private String toYear;
	private String trim;
	private MultipartFile  imageURL;
	public MultipartFile getImageURL() {
		return imageURL;
	}

	public void setImageURL(MultipartFile imageURL) {
		this.imageURL = imageURL;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public String getNoOfButton() {
		return noOfButton;
	}

	public void setNoOfButton(String noOfButton) {
		this.noOfButton = noOfButton;
	}

	public String getiC() {
		return iC;
	}

	public void setiC(String iC) {
		this.iC = iC;
	}

	public String getBatteryPartNumber() {
		return batteryPartNumber;
	}

	public void setBatteryPartNumber(String batteryPartNumber) {
		this.batteryPartNumber = batteryPartNumber;
	}
	private  byte[] imageURLByteArray;
	public byte[] getImageURLByteArray() {
		return imageURLByteArray;
	}
	public void setImageURLByteArray(byte[] imageURLByteArray) {
		this.imageURLByteArray = imageURLByteArray;
	}
	private String subCategoryName;
	private String categoryName;
	private String scheduleTIme;
	public String getScheduleTIme() {
		return scheduleTIme;
	}

	public void setScheduleTIme(String scheduleTIme) {
		this.scheduleTIme = scheduleTIme;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}

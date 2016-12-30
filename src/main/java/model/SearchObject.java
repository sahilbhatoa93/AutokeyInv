package model;

import org.springframework.web.multipart.MultipartFile;

public class SearchObject {
	@Override
	public String toString() {
		return "SearchObject [itemCode=" + itemCode + ", invName=" + invName + ", rack=" + rack + ", shelf=" + shelf
				+ ", column=" + column + ", compartment=" + compartment + ", noOfItems=" + noOfItems + ", categoryName="
				+ categoryName + ", subCategoryName=" + subCategoryName + "]";
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
	public String getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(String noOfItems) {
		this.noOfItems = noOfItems;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	private String itemCode;
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	private String shipperName;
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	private MultipartFile  imageURL;

	public MultipartFile getImageURL() {
		return imageURL;
	}
	public void setImageURL(MultipartFile imageURL) {
		this.imageURL = imageURL;
	}
	private String invName;
	private String model;
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	private String rack;
	private String shelf;
	private String column;
	private String compartment;
	private String noOfItems;
	private String categoryName;
	private String brand;
	private String sku;
	private String description;
	private String fromYear;
	private String toYear;
	private String trim;
	private String transponder;
	public String getTransponder() {
		return transponder;
	}

	public void setTransponder(String transponder) {
		this.transponder = transponder;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
	private String subCategoryName;
	private  byte[] imageURLByteArray;
	public byte[] getImageURLByteArray() {
		return imageURLByteArray;
	}
	public void setImageURLByteArray(byte[] imageURLByteArray) {
		this.imageURLByteArray = imageURLByteArray;
	}
	
	private String productNotes;
	private String ffcId;
	private String buttonConfiguration;
	private String emergencyKey;
	private String batteryPartNumber;
	private String iC;
	private String noOfButton;
	public String getNoOfButton() {
		return noOfButton;
	}

	public void setNoOfButton(String noOfButton) {
		this.noOfButton = noOfButton;
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

	public String getBatteryPartNumber() {
		return batteryPartNumber;
	}

	public void setBatteryPartNumber(String batteryPartNumber) {
		this.batteryPartNumber = batteryPartNumber;
	}

	public String getiC() {
		return iC;
	}

	public void setiC(String iC) {
		this.iC = iC;
	}
	private String restockLimit;
	public String getRestockLimit() {
	return restockLimit;
}

public void setRestockLimit(String restockLimit) {
	this.restockLimit = restockLimit;
}

}

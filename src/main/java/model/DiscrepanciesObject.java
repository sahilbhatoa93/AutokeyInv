package model;

public class DiscrepanciesObject {
	
	
	
	private String sku;
	private String invName;
	private String userNoOfItems;
	private String invNoOfItems;
	private String itemCode;
	private String brand;
	private String category;
	private String subCategory;
	private String user;
	private String date;
	private String model;
	private String status;
	
	@Override
	public String toString() {
		return "DiscrepanciesObject [sku=" + sku + ", invName=" + invName + ", userNoOfItems=" + userNoOfItems
				+ ", invNoOfItems=" + invNoOfItems + ", itemCode=" + itemCode + ", brand=" + brand + ", category="
				+ category + ", subCategory=" + subCategory + ", user=" + user + ", date=" + date + ", model=" + model
				+ ", status=" + status + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public String getUserNoOfItems() {
		return userNoOfItems;
	}
	public void setUserNoOfItems(String userNoOfItems) {
		this.userNoOfItems = userNoOfItems;
	}
	public String getInvNoOfItems() {
		return invNoOfItems;
	}
	public void setInvNoOfItems(String invNoOfItems) {
		this.invNoOfItems = invNoOfItems;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

}

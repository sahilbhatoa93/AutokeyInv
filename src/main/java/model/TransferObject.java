package model;

public class TransferObject {
	
	@Override
	public String toString() {
		return "TransferObject [itemCodeFrom=" + itemCodeFrom + ", itemCodeTo=" + itemCodeTo + ", invCodeFrom="
				+ invCodeFrom + ", invCodeTo=" + invCodeTo + ", rackFrom=" + rackFrom + ", rackTo=" + rackTo
				+ ", shelfFrom=" + shelfFrom + ", shelfTo=" + shelfTo + ", columnFrom=" + columnFrom + ", columnTo="
				+ columnTo + ", compartmentFrom=" + compartmentFrom + ", compartmentTo=" + compartmentTo
				+ ", transferAmount=" + transferAmount + ", reason=" + reason + ", transferId=" + transferId
				+ ", brand=" + brand + ", TransferObject=" + TransferObject + ", subCategoryName=" + subCategoryName
				+ ", categoryName=" + categoryName + ", sku=" + sku + ", shipperName=" + shipperName
				+ ", shipperAddress=" + shipperAddress + ", shipperEmail=" + shipperEmail + ", shipperPhoneNumber="
				+ shipperPhoneNumber + ", date=" + date + "]";
	}
	public String getItemCodeFrom() {
		return itemCodeFrom;
	}
	public void setItemCodeFrom(String itemCodeFrom) {
		this.itemCodeFrom = itemCodeFrom;
	}
	public String getItemCodeTo() {
		return itemCodeTo;
	}
	public void setItemCodeTo(String itemCodeTo) {
		this.itemCodeTo = itemCodeTo;
	}
	public String getInvCodeFrom() {
		return invCodeFrom;
	}
	public void setInvCodeFrom(String invCodeFrom) {
		this.invCodeFrom = invCodeFrom;
	}
	public String getInvCodeTo() {
		return invCodeTo;
	}
	public void setInvCodeTo(String invCodeTo) {
		this.invCodeTo = invCodeTo;
	}
	public String getRackFrom() {
		return rackFrom;
	}
	public void setRackFrom(String rackFrom) {
		this.rackFrom = rackFrom;
	}
	public String getRackTo() {
		return rackTo;
	}
	public void setRackTo(String rackTo) {
		this.rackTo = rackTo;
	}
	public String getColumnFrom() {
		return columnFrom;
	}
	public void setColumnFrom(String columnFrom) {
		this.columnFrom = columnFrom;
	}
	public String getColumnTo() {
		return columnTo;
	}
	public void setColumnTo(String columnTo) {
		this.columnTo = columnTo;
	}
	public String getCompartmentFrom() {
		return compartmentFrom;
	}
	public void setCompartmentFrom(String compartmentFrom) {
		this.compartmentFrom = compartmentFrom;
	}
	public String getCompartmentTo() {
		return compartmentTo;
	}
	public void setCompartmentTo(String compartmentTo) {
		this.compartmentTo = compartmentTo;
	}
	public String getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}
	private String itemCodeFrom;
	private String itemCodeTo;
	private String invCodeFrom;
	private String model;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	private String invCodeTo;
	private String rackFrom;
	private String rackTo;
	private String validationReason;
	public String getValidationReason() {
		return validationReason;
	}
	public void setValidationReason(String validationReason) {
		this.validationReason = validationReason;
	}
	private String shelfFrom;
	private String shelfTo;
	public String getShelfFrom() {
		return shelfFrom;
	}
	public void setShelfFrom(String shelfFrom) {
		this.shelfFrom = shelfFrom;
	}
	public String getShelfTo() {
		return shelfTo;
	}
	public void setShelfTo(String shelfTo) {
		this.shelfTo = shelfTo;
	}
	private String columnFrom;
	private String columnTo;
	private String compartmentFrom;
	private String compartmentTo;
	private String transferAmount;
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	private String transferId;
	private String brand;
	public String getTransferId() {
		return transferId;
	}
	public void setTransferId(String transferId) {
		this.transferId = transferId;
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
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	private TransferObject TransferObject;
	private String subCategoryName;
	private String categoryName;
	public String getShipperAddress() {
		return shipperAddress;
	}
	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}
	public String getShipperEmail() {
		return shipperEmail;
	}
	public void setShipperEmail(String shipperEmail) {
		this.shipperEmail = shipperEmail;
	}
	public String getShipperPhoneNumber() {
		return shipperPhoneNumber;
	}
	public void setShipperPhoneNumber(String shipperPhoneNumber) {
		this.shipperPhoneNumber = shipperPhoneNumber;
	}
	private String sku;
	private String shipperName;
	private String shipperAddress;
	private String shipperEmail;
	private String shipperPhoneNumber;
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	private String date;
	public TransferObject getTransferObject() {
		return TransferObject;
	}
	public void setTransferObject(TransferObject transferObject) {
		TransferObject = transferObject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}

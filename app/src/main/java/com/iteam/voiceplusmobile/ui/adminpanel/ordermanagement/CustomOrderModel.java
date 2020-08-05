package com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement;

public class CustomOrderModel {

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    private int image_id;

    private Integer id;
    private String userPhoneNumber;
    private String mobileBrand;
    private String mobileModel;
    private String mobileFault;
    private String dateOrderPlaced;
    private String dateItemReceived;
    private String dateItemDelivered;
    private String orderStatus;
    private Boolean hasRepaired;
    private Integer charges;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getMobileFault() {
        return mobileFault;
    }

    public void setMobileFault(String mobileFault) {
        this.mobileFault = mobileFault;
    }


    public String getDateOrderPlaced() {
        return dateOrderPlaced;
    }

    public void setDateOrderPlaced(String dateOrderPlaced) {
        this.dateOrderPlaced = dateOrderPlaced;
    }

    public String getDateItemReceived() {
        return dateItemReceived;
    }

    public void setDateItemReceived(String dateItemReceived) {
        this.dateItemReceived = dateItemReceived;
    }

    public String getDateItemDelivered() {
        return dateItemDelivered;
    }

    public void setDateItemDelivered(String dateItemDelivered) {
        this.dateItemDelivered = dateItemDelivered;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getHasRepaired() {
        return hasRepaired;
    }

    public void setHasRepaired(Boolean hasRepaired) {
        this.hasRepaired = hasRepaired;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }


}

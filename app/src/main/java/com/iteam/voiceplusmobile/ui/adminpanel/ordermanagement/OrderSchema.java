
package com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderSchema {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_phone_number")
    @Expose
    private String userPhoneNumber;
    @SerializedName("mobile_brand")
    @Expose
    private String mobileBrand;
    @SerializedName("mobile_model")
    @Expose
    private String mobileModel;
    @SerializedName("mobile_fault")
    @Expose
    private String mobileFault;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("date_order_placed")
    @Expose
    private String dateOrderPlaced;
    @SerializedName("date_item_received")
    @Expose
    private String dateItemReceived;
    @SerializedName("date_item_delivered")
    @Expose
    private String dateItemDelivered;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("has_repaired")
    @Expose
    private Boolean hasRepaired;
    @SerializedName("charges")
    @Expose
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
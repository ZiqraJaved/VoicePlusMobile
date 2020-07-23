
package com.iteam.voiceplusmobile.ui.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OrderSchema {

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
    private Date dateOrderPlaced;
    @SerializedName("date_item_received")
    @Expose
    private Date dateItemReceived;
    @SerializedName("date_item_delivered")
    @Expose
    private Date dateItemDelivered;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("has_repaired")
    @Expose
    private Boolean hasRepaired;
    @SerializedName("charges")
    @Expose
    private Integer charges;

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

    public Date getDateOrderPlaced() {
        return dateOrderPlaced;
    }

    public void setDateOrderPlaced(Date dateOrderPlaced) {
        this.dateOrderPlaced = dateOrderPlaced;
    }

    public Date getDateItemReceived() {
        return dateItemReceived;
    }

    public void setDateItemReceived(Date dateItemReceived) {
        this.dateItemReceived = dateItemReceived;
    }

    public Date getDateItemDelivered() {
        return dateItemDelivered;
    }

    public void setDateItemDelivered(Date dateItemDelivered) {
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
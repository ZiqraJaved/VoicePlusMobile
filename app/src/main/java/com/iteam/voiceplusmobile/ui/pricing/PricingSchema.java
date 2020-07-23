package com.iteam.voiceplusmobile.ui.pricing;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
 
public class PricingSchema {

    @SerializedName("mobile_company")
    @Expose
    private String mobileCompany;
    @SerializedName("mobile_model")
    @Expose
    private String mobileModel;
    @SerializedName("repairing_part")
    @Expose
    private String repairingPart;
    @SerializedName("repairing_price")
    @Expose
    private Integer repairingPrice;
    @SerializedName("repairing_description")
    @Expose
    private String repairingDescription;

    public String getMobileCompany() {
        return mobileCompany;
    }

    public void setMobileCompany(String mobileCompany) {
        this.mobileCompany = mobileCompany;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getRepairingPart() {
        return repairingPart;
    }

    public void setRepairingPart(String repairingPart) {
        this.repairingPart = repairingPart;
    }

    public Integer getRepairingPrice() {
        return repairingPrice;
    }

    public void setRepairingPrice(Integer repairingPrice) {
        this.repairingPrice = repairingPrice;
    }

    public String getRepairingDescription() {
        return repairingDescription;
    }

    public void setRepairingDescription(String repairingDescription) {
        this.repairingDescription = repairingDescription;
    }

}
package com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement;

public class AddItem {
    public String getMobile_company() {
        return mobile_company;
    }

    public void setMobile_company(String mobile_company) {
        this.mobile_company = mobile_company;
    }

    public String getMobile_model() {
        return mobile_model;
    }

    public void setMobile_model(String mobile_model) {
        this.mobile_model = mobile_model;
    }

    public String getRepairing_part() {
        return repairing_part;
    }

    public void setRepairing_part(String repairing_part) {
        this.repairing_part = repairing_part;
    }

    public Integer getRepairing_price() {
        return repairing_price;
    }

    public void setRepairing_price(Integer repairing_price) {
        this.repairing_price = repairing_price;
    }

    public String getRepairing_description() {
        return repairing_description;
    }

    public void setRepairing_description(String repairing_description) {
        this.repairing_description = repairing_description;
    }

    String mobile_company;
//
//    title: Mobile company
//    maxLength: 50
//    minLength: 1
   String mobile_model;
//    title: Mobile model
//    maxLength: 50
//    minLength: 1
    String repairing_part;
//    title: Repairing part
//    maxLength: 70
//    minLength
     Integer repairing_price;
//    title: Repairing price
//    maximum: 2147483647
//    minimum: -2147483648
//    x-nullable: true
      String repairing_description;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    //    title: Repairing description
//    maxLength: 1000
//    minLength: 1
    String detail;
}

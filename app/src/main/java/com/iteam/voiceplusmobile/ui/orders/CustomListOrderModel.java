package com.iteam.voiceplusmobile.ui.orders;

import java.util.Date;

public class CustomListOrderModel {
//    private int image_id;
//    private String phone_model_name;
//    private String repair_part_name;
//    private String mobile_company;
//    private int repairing_price;
//    private String repairing_description;
//  "user_phone_number": "9231313131",


    private String user_phone_number;
    private String mobile_brand;
    private String mobile_model;

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getMobile_brand() {
        return mobile_brand;
    }

    public void setMobile_brand(String mobile_brand) {
        this.mobile_brand = mobile_brand;
    }

    public String getMobile_model() {
        return mobile_model;
    }

    public void setMobile_model(String mobile_model) {
        this.mobile_model = mobile_model;
    }

    public String getMobile_fault() {
        return mobile_fault;
    }

    public void setMobile_fault(String mobile_fault) {
        this.mobile_fault = mobile_fault;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Date getDate_order_placed() {
        return date_order_placed;
    }

    public void setDate_order_placed(Date date_order_placed) {
        this.date_order_placed = date_order_placed;
    }

    public Date getDate_item_received() {
        return date_item_received;
    }

    public void setDate_item_received(Date date_item_received) {
        this.date_item_received = date_item_received;
    }

    public Date getDate_item_delivered() {
        return date_item_delivered;
    }

    public void setDate_item_delivered(Date date_item_delivered) {
        this.date_item_delivered = date_item_delivered;
    }

    public boolean isHas_repaired() {
        return has_repaired;
    }

    public void setHas_repaired(boolean has_repaired) {
        this.has_repaired = has_repaired;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    private String mobile_fault;
    private String image;
    private String order_status;
    private Date date_order_placed;
    private Date date_item_received;
    private Date date_item_delivered;
    private boolean has_repaired;
    private int charges;

}

package com.iteam.voiceplusmobile.ui.pricing;

public class CustomListDataModel {
    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImageDiscription() {
        return ImageDiscription;
    }

    public void setImageDiscription(String imageDiscription) {
        ImageDiscription = imageDiscription;
    }

    public int getImage_id() {
        return Image_id;
    }

    public void setImage_id(int image_id) {
        Image_id = image_id;
    }

    private String ImageName="";
    private String ImageDiscription;
    private int Image_id;
}

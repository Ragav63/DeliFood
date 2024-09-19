package com.example.delifood;

public class RecItemItemAct {


    private int imageResource;
    private String itemTitle;
    private String itemprice;
    private String itemcalories;

    public RecItemItemAct(int imageResource, String itemTitle, String itemprice, String itemcalories) {
        this.imageResource = imageResource;
        this.itemTitle = itemTitle;
        this.itemprice = itemprice;
        this.itemcalories = itemcalories;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemcalories() {
        return itemcalories;
    }

    public void setItemcalories(String itemcalories) {
        this.itemcalories = itemcalories;
    }
}

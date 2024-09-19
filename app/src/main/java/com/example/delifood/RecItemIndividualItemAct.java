package com.example.delifood;

public class RecItemIndividualItemAct {



    private String itemTitle;
    private String itemprice;

    public RecItemIndividualItemAct(String itemTitle, String itemprice) {
        this.itemTitle = itemTitle;
        this.itemprice = itemprice;
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
}

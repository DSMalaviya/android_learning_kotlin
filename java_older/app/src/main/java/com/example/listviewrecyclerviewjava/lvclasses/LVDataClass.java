package com.example.listviewrecyclerviewjava.lvclasses;

public class LVDataClass {
    private int ivNumbersImageId;

    private String mNumberInDigit;

    private String mNumbersInText;

    public LVDataClass(int NumbersImageId, String NumbersInDigit, String NumbersInText) {
        this.ivNumbersImageId = NumbersImageId;
        this.mNumberInDigit = NumbersInDigit;
        this.mNumbersInText = NumbersInText;
    }

    public int getNumbersImageId() {
        return ivNumbersImageId;
    }

    // getter method for returning the ID of the TextView 1
    public String getNumberInDigit() {
        return mNumberInDigit;
    }

    // getter method for returning the ID of the TextView 2
    public String getNumbersInText() {
        return mNumbersInText;
    }
}

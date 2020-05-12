
package com.zalocoders.cornerstonekangemi.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PayItem {

    @SerializedName("amount")
    private String mAmount;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("item")
    private String mItem;
    @SerializedName("name")
    private String mName;

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}

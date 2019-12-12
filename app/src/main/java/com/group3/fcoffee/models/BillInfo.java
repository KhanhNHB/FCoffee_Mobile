package com.group3.fcoffee.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BillInfo implements Serializable, Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private float price;
    @SerializedName("total_price")
    private float total_price;

    public BillInfo() {
    }

    public BillInfo(String name, int quantity, float price, float total_price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total_price = total_price;
    }

    protected BillInfo(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
        price = in.readFloat();
        total_price = in.readFloat();
    }

    public static final Creator<BillInfo> CREATOR = new Creator<BillInfo>() {
        @Override
        public BillInfo createFromParcel(Parcel in) {
            return new BillInfo(in);
        }

        @Override
        public BillInfo[] newArray(int size) {
            return new BillInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(quantity);
        dest.writeFloat(price);
        dest.writeFloat(total_price);
    }
}

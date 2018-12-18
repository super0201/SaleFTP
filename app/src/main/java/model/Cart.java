package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    public String idproduct;
    public String name;
    public String image;
    public int amount;
    public Integer price;

    public Cart() {
    }

    public Cart(String idproduct, String name, String image, int amount, Integer price) {
        this.idproduct = idproduct;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.price = price;
    }

    protected Cart(Parcel in) {
        idproduct = in.readString();
        name = in.readString();
        image = in.readString();
        amount = in.readInt();
        price = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };


    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static Creator<Cart> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idproduct);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeInt(amount);
        dest.writeDouble(price);
    }
}

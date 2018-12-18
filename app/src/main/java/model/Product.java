package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name, summary, image, id, detail;
    private Integer price;

    public Product(String name, String summary, String image, String id, String detail, Integer price) {
        this.name = name;
        this.summary = summary;
        this.image = image;
        this.id = id;
        this.detail = detail;
        this.price = price;
    }

    private Product(Parcel in) {
        name = in.readString();
        summary = in.readString();
        image = in.readString();
        id = in.readString();
        detail = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(summary);
        dest.writeString(image);
        dest.writeString(id);
        dest.writeString(detail);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(price);
        }
    }
}

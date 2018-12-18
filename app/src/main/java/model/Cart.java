package model;

public class Cart {
    public String idproduct;
    public String name;
    public String image;
    public int amount;
    public double price;

    public Cart() {
    }

    public Cart(String idproduct, String name, String image, double price) {
        this.idproduct = idproduct;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.price = price;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

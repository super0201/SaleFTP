package model;

public class Invoice {
    private String code, name, date, stt;
    private int amount;
    private double total;

    public Invoice() {
    }

    public Invoice(String code, String name, String date, String stt, int amount, double total) {
        this.code = code;
        this.name = name;
        this.date = date;
        this.stt = stt;
        this.amount = amount;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

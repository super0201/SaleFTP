package model;

public class ProductDetail {
    private String scr, scrRes, frCam, reCam, cpu, ram, rom, sim, mCard, battCap, os;

    public ProductDetail(String scr, String scrRes, String frCam, String reCam, String cpu, String ram, String rom, String sim, String mCard, String battCap, String os) {
        this.scr = scr;
        this.scrRes = scrRes;
        this.frCam = frCam;
        this.reCam = reCam;
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.sim = sim;
        this.mCard = mCard;
        this.battCap = battCap;
        this.os = os;
    }

    public String getScr() {
        return scr;
    }

    public void setScr(String scr) {
        this.scr = scr;
    }

    public String getScrRes() {
        return scrRes;
    }

    public void setScrRes(String scrRes) {
        this.scrRes = scrRes;
    }

    public String getFrCam() {
        return frCam;
    }

    public void setFrCam(String frCam) {
        this.frCam = frCam;
    }

    public String getReCam() {
        return reCam;
    }

    public void setReCam(String reCam) {
        this.reCam = reCam;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getmCard() {
        return mCard;
    }

    public void setmCard(String mCard) {
        this.mCard = mCard;
    }

    public String getBattCap() {
        return battCap;
    }

    public void setBattCap(String battCap) {
        this.battCap = battCap;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}

package com.creditsapp.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 31.10.14
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
public class InfoForBanks {

    private String id;
    private String nameForBanks;
    private String logoForBanks;
    private String payForBanks;

    public InfoForBanks(String id, String nameForBanks, String logoForBanks, String payForBanks ){
         this.id = id;
        this.nameForBanks = nameForBanks;
        this.logoForBanks = logoForBanks;
        this.payForBanks = payForBanks;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameForBanks() {
        return nameForBanks;
    }

    public void setNameForBanks(String nameForBanks) {
        this.nameForBanks = nameForBanks;
    }

    public String getLogoForBanks() {
        return logoForBanks;
    }

    public void setLogoForBanks(String logoForBanks) {
        this.logoForBanks = logoForBanks;
    }

    public String getPayForBanks() {
        return payForBanks;
    }

    public void setPayForBanks(String payForBanks) {
        this.payForBanks = payForBanks;
    }

}

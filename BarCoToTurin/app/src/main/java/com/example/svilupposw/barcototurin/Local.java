package com.example.svilupposw.barcototurin;

/**
 * Created by svilupposw on 02/05/16.
 */
public class Local {

    protected int id;
    protected String name;
    protected String address;
    protected String type;
    protected String money;
    protected String contact;
    protected String hours;

    public Local(int id) {
        this.id = id;
    }

    public Local(int id, String name, String address, String type, String money, String contact, String hours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.money = money;
        this.contact = contact;
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}

package demo.spring.customer.integration.po;

import org.springframework.data.geo.Point;

import java.io.Serializable;

public class Store implements Serializable {

    private String id;

    private String name;

    private Address address;

    private String msg;

    public Store(String msg){
        this.msg = msg;
    }

    public Store() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Address implements Serializable {

        private String street, city, zip;
        private Point location;
        public Address(){}
        public Address(String street_address, String city, String zip, Point location) {
            this.street = street_address;
            this.city = city;
            this.zip = zip;
            this.location = location;
        }

        public Point getLocation() {
            return location;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }

}

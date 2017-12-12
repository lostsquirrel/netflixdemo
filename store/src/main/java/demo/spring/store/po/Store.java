package demo.spring.store.po;

import org.springframework.data.geo.Point;

public class Store {
    String name;
    Address address;

    public static class Address {

        String street, city, zip;
        Point location;
    }
}

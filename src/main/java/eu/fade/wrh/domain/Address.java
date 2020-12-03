package eu.fade.wrh.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "empl_street", columnDefinition = "VARCHAR(80)")
    private String street;
    @Column(name = "empl_house_number", columnDefinition = "VARCHAR(10)")
    private String number;
    @Column(name = "empl_postal_code", columnDefinition = "VARCHAR(10)")
    private String zip;
    @Column(name = "empl_city", columnDefinition = "VARCHAR(50)")
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(final String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

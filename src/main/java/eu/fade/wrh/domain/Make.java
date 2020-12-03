package eu.fade.wrh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Make {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Version
    private int version;
    private String name;
    private Double discount;
    private Integer OrderAmount;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(final Double discount) {
        this.discount = discount;
    }

    public Integer getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(final Integer orderAmount) {
        OrderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "Make{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                ", OrderAmount=" + OrderAmount +
                '}';
    }
}

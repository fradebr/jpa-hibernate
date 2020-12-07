package eu.fade.wrh.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer currentStock;
    private String type;
    private String make;

    @OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "detail_id")
    private Detail detail;


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(final Integer currentStock) {
        this.currentStock = currentStock;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(final String make) {
        this.make = make;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(final Detail detail) {
        this.detail = detail;
    }

    public Item addDetail(Detail detail) {
        this.detail = detail;
        detail.setItem(this);

        return this;
    }

    public Item removeDetail() {
        if(this.detail != null) {
            this.detail.setItem(null);
            this.detail = null;
        }

        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentStock=" + currentStock +
                ", type='" + type + '\'' +
                ", make='" + make + '\'' +
                ", detail=" + detail +
                '}';
    }
}

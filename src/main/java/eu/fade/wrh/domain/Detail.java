package eu.fade.wrh.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer minimumStock;
    private Integer maximumStock;
    private Double price;
    @OneToOne(mappedBy = "detail")
    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(final Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public Integer getMaximumStock() {
        return maximumStock;
    }

    public void setMaximumStock(final Integer maximumStock) {
        this.maximumStock = maximumStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", minimumStock=" + minimumStock +
                ", maximumStock=" + maximumStock +
                ", price=" + price +
                '}';
    }
}

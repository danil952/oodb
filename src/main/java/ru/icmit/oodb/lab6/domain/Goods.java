package ru.icmit.oodb.lab6.domain;


import ru.icmit.oodb.lab6.annotation.Column;
import ru.icmit.oodb.lab6.annotation.Entity;

@Entity
public class Goods {

    public Goods(){}

    @Column
    private Integer cost;

    @Column
    private int quantity;

    @Column
    private GoodsType type;

    public Goods(int cost, int quantity, GoodsType goodstype)
    {
        this.type=goodstype;
        this.cost=cost;
        this.quantity=quantity;
    }

    public Integer getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public GoodsType getType() {
        return type;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

package ru.icmit.oodb.lab2.domain;


public class Goods {

    private Integer cost;
    private int quantity;
    GoodsType type;

    public Goods(int cost, int quantity, GoodsType goodstype)
    {
        type=goodstype;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

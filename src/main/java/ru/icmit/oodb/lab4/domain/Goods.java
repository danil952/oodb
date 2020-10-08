package ru.icmit.oodb.lab4.domain;



public class Goods {

    public Goods(){}

    private Integer cost;
    private int quantity;
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

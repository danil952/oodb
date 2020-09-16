package ru.icmit.oodb.lab1;

public class Goods {

    private int cost;
    private int quantity;
    GoodsType type;

    Goods(int cost,int quantity,GoodsType goodstype)
    {
        type=goodstype;
        this.cost=cost;
        this.quantity=quantity;
    }

    public int getCost() {
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

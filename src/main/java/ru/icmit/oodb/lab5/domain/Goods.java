package ru.icmit.oodb.lab5.domain;


import org.postgresql.util.PGobject;
import org.postgresql.util.PGtokenizer;

import java.sql.SQLException;

public class Goods extends PGobject {

    public Goods(){
        setType("goods");
    }

    private Integer cost;
    private int quantity;
    private GoodsType type;

    public Goods(int cost, int quantity, GoodsType goodstype)
    {
        this.type=goodstype;
        this.cost=cost;
        this.quantity=quantity;
    }

    public void setValue(String value) throws SQLException {
        String s = value.substring(1, value.length()-1);
        PGtokenizer t = new PGtokenizer(s, ',');
        type = GoodsType.valueOf(t.getToken(0).substring(1, t.getToken(0).length()-1));
        cost = Integer.parseInt(t.getToken(1).substring(1, t.getToken(1).length()-1));
        quantity = Integer.parseInt(t.getToken(2).substring(1, t.getToken(2).length()-1));
    }

    public String getValue() {
        return "('" + type.toString() + "'," + cost + "," +quantity+ ")";
    }

    public Integer getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
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

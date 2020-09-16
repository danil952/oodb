package ru.icmit.oodb.lab1;

import java.util.List;

public class Transaction {

    private Shop shop;

    private Cashier cashier;

    private List<Goods> goodsList;

    public Transaction(Shop shop,Cashier cashier,List<Goods> goodsList)
    {
        this.shop=shop;
        this.cashier=cashier;
        this.goodsList=goodsList;
    }

    public static void sell(Cashier cashier, List<Goods> goodsList,int visitormoney)
    {
        System.out.println(cashier.toString());
        cashier.toSell(goodsList,visitormoney);
    }

    public static void Change(Cashier cashier,int visitormoney)
    {
        System.out.println(cashier.toString());
        money.Exchange(visitormoney);
    }

    public Cashier getCashier() {
        return cashier;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public Shop getShop() {
        return shop;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}

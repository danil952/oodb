package ru.icmit.oodb.lab2.domain;

import java.util.List;

public class Transaction {


    private Cashier cashier;

    private List<Goods> goodsList;

    public Transaction(Shop shop, Cashier cashier, List<Goods> goodsList)
    {
        this.cashier=cashier;
        this.goodsList=goodsList;
    }

    public static void sell(Cashier cashier, List<Goods> goodsList, int visitormoney)
    {
        System.out.println(cashier.toString());
        cashier.toSell(goodsList,visitormoney);
    }

    public static void Change(Cashier cashier, int visitormoney)
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


    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

}

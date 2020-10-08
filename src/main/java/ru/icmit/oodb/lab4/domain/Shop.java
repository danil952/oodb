package ru.icmit.oodb.lab4.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "shop")
public class Shop {

    public Shop(){}
    private String name;

    private List<Cashier> cashiers;

    private List<Goods> goods;

    private List<Transaction> transactions;

    public Shop(String name)
    {
        this.name=name;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction tr)
    {
        this.transactions.add(tr);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

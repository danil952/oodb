package ru.icmit.oodb.lab7.domain;

import ru.icmit.oodb.lab7.annotation.Column;
import ru.icmit.oodb.lab7.annotation.Entity;
import ru.icmit.oodb.lab7.annotation.Id;
import ru.icmit.oodb.lab7.annotation.OneToMany;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@Entity
public class Shop {

    @Column
    @Id
    public long id;

    public Shop(){}
    @Column
    private String name;

    @Column
    @OneToMany
    private List<Cashier> cashiers;

    @Column
    @OneToMany
    private List<Goods> goods;

    @Column
    @OneToMany
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

package domain;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Shop {

    @Id
    public int id;

    private String name;

    @OneToMany
    private Collection<Cashier> cashiers;

    @OneToMany
    private Collection<Goods> goods;

    @OneToMany
    private Collection<Transaction> transactions;

    public Shop(String name)
    {
        this.name=name;
    }

    public int getId(){return id;}

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Cashier> getCashiers() {
        return cashiers;
    }

    public Collection<Goods> getGoods() {
        return goods;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setCashiers(Collection<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public void setGoods(Collection<Goods> goods) {
        this.goods = goods;
    }

    public void setTransactions(Collection<Transaction> transactions) {
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

package ru.icmit.oodb.lab8.domain;

import ru.icmit.oodb.lab8.annotation.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@Entity
public class Transaction {

    public Transaction(){}

    @Column
    @Id
    public int id;

    @Column
    @ManyToOne
    private Cashier cashier;

    @Column
    @OneToMany
    private List<Goods> goodsList;

    public Transaction(Shop shop, Cashier cashier, List<Goods> goodsList)
    {
        this.cashier=cashier;
        this.goodsList=goodsList;
    }


    public Cashier getCashier() {
        return cashier;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

}

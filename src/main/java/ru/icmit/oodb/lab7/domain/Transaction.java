package ru.icmit.oodb.lab7.domain;

import ru.icmit.oodb.lab7.annotation.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@Entity
public class Transaction {

    public Transaction(){}

    @Column
    @Id
    public long id;

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


    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

}

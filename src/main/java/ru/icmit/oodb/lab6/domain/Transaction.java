package ru.icmit.oodb.lab6.domain;

import ru.icmit.oodb.lab6.annotation.Column;
import ru.icmit.oodb.lab6.annotation.Entity;
import ru.icmit.oodb.lab6.annotation.OneToMany;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@Entity
public class Transaction {

    public Transaction(){}

    @Column
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

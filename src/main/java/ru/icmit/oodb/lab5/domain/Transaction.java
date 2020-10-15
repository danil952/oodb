package ru.icmit.oodb.lab5.domain;

import org.postgresql.util.PGobject;
import org.postgresql.util.PGtokenizer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction extends PGobject {

    public Transaction(){
        setType("transaction");
    }

    private Cashier cashier;

    private List<Goods> goodsList;

    public Transaction(Cashier cashier, List<Goods> goodsList)
    {
        this.cashier=cashier;
        this.goodsList=goodsList;
    }

    public void setValue(String value) throws SQLException {
        String s = value.substring(1, value.length()-1);
        PGtokenizer t = new PGtokenizer(s, ',');
        cashier.setValue(t.getToken(0));
        String[] arr = t.getToken(1).substring(1, t.getToken(1).length()-1).split(",");
        List<Goods> list = new ArrayList<>();
        Goods item = new Goods();
        for (String i: arr)
        {
            item.setValue(i);
            list.add(item);
        }
        goodsList=list;
    }

    public String getValue() {
        String s="{";
        for(Goods item: goodsList)
        {
            s=s+item.getValue()+"\",\"";
        }
        s=s.substring(0, s.length()-2);
        s=s+"}";
        return "('" + cashier.getValue() + "\",\"" + s + "')";
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

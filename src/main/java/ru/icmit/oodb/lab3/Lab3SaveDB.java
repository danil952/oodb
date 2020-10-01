package ru.icmit.oodb.lab3;

import com.google.gson.Gson;
import ru.icmit.oodb.lab3.domain.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lab3SaveDB {

    public static void main(String[] args) throws IOException, JAXBException {
        ru.icmit.oodb.lab3.domain.Shop shop=new Shop("Shop KZN");
        List<ru.icmit.oodb.lab3.domain.Cashier> list = new ArrayList();
        List<Goods> goods = new ArrayList();
        List<Transaction> transactions = new ArrayList();

        ru.icmit.oodb.lab3.domain.Cashier cashier1=new ru.icmit.oodb.lab3.domain.Cashier("Danil", "Garbazhii", ru.icmit.oodb.lab3.domain.Cashier.Shift.NIGHT);
        ru.icmit.oodb.lab3.domain.Cashier cashier2=new ru.icmit.oodb.lab3.domain.Cashier("Artem", "Pupkin", Cashier.Shift.DAY);

        Goods item1 = new Goods(1200, 2, GoodsType.CHAIR);
        Goods item2 = new Goods(130, 1, GoodsType.TABLE);
        Goods item3 = new Goods(700, 0, GoodsType.TV);
        goods.add(item1);
        goods.add(item2);
        goods.add(item3);


        Transaction transaction1 = new Transaction(shop, cashier1, goods);
        Transaction transaction2 = new Transaction(shop, cashier2, goods);
        transactions.add(transaction1);
        transactions.add(transaction2);



        list.add(cashier1);
        list.add(cashier2);
        shop.setCashiers(list);
        shop.setGoods(goods);
        shop.setTransactions(transactions);


        Transactions tr = new Transactions();
        Cashiers cash= new Cashiers();
        cash.setCashiers(list);
        tr.setTransactions(transactions);
        Lab3XMLService.saveShopData(shop);
        Lab3XMLService.saveTransactionsList(tr, "transactions.xml");
        Lab3XMLService.saveCashiersList(cash, "cashiers.xml");
    }


}

package ru.icmit.oodb.lab2;

import ru.icmit.oodb.lab2.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lab2Main {

    public static void main(String[] args) {

        try {

            Shop shop=new Shop("Shop KZN");
            List<Cashier> list = new ArrayList();
            List<Goods> goods = new ArrayList();
            List<Transaction> transactions = new ArrayList();

            Cashier cashier1=new Cashier("Danil", "Garbazhii", Cashier.Shift.NIGHT);
            Cashier cashier2=new Cashier("Artem", "Pupkin", Cashier.Shift.DAY);

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


            System.out.println("Shop:");
            Lab2SaveDB.saveShop(shop);
            System.out.println("Cashiers:");
            Lab2SaveDB.saveStructureList(list, "cashier.json");
            System.out.println("Goods:");
            Lab2SaveDB.saveStructureList(goods, "goods.json");


            List<Cashier> cashiers = Lab2LoadDB.loadCashierList();


            Cashier prs = StructureService.findCashierByName(cashiers, "Danil");

            if (prs != null) {
                prs.setName("Bulat");
            }

            System.out.println("Changed cashiers:");
            Lab2SaveDB.saveStructureList(cashiers, "cashier.json");


            List<Goods> sortedGoods = StructureService.sortGoodsByPrice(goods);
            System.out.println("Sorted goods:");
            Lab2SaveDB.saveStructureList(sortedGoods, "goods.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

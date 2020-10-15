package ru.icmit.oodb.lab5;

import ru.icmit.oodb.lab5.domain.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Lab5Main {

    public static void main(String[] args) throws IOException, JAXBException {
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


            Transaction transaction1 = new Transaction(cashier1, goods);
            Transaction transaction2 = new Transaction(cashier2, goods);
            transactions.add(transaction1);
            transactions.add(transaction2);



            list.add(cashier1);
            list.add(cashier2);
            shop.setCashiers(list);
            shop.setGoods(goods);
            shop.setTransactions(transactions);

            Class.forName("org.postgresql.Driver");
            String dbURL = "jdbc:postgresql://localhost:1345/oodb";
            Connection connection =
                    DriverManager.getConnection( dbURL, "postgres", "3005952");


            Lab5SaveDB.saveShop(shop.getName(),shop.getCashiers(),shop.getGoods(),shop.getTransactions(),connection);

            String s=Lab5LoadFromDB.loadCashier(connection);
            System.out.println(s);
            s=s.substring(2, s.length()-2);
            cashier1.setValue(s.split("\"")[0]);

            System.out.println(cashier1.getName());
            connection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

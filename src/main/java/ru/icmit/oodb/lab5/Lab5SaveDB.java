package ru.icmit.oodb.lab5;

import com.google.gson.Gson;
import ru.icmit.oodb.lab5.domain.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Lab5SaveDB {
    public static void saveCashier(Cashier person, Connection connection) throws SQLException {

            PreparedStatement statement = connection.
                    prepareStatement("insert into record (cashier) values ( cast( ? as cashier) )");
        System.out.println(person.getValue());
            statement.setString(1, person.getValue());
            int count = statement.executeUpdate();

            System.out.println(count + " records added!");

            statement.close();

    }

    public static void saveShop(String name, List<Cashier> cashiers,List<Goods> goods,List<Transaction> transactions,Connection connection) throws SQLException {

        PreparedStatement statement = connection.
                prepareStatement("insert into shop (name,cashier,goods) values ((?),cast( ? as cashier[]),cast( ? as goods[]) )");
        String s= "{\"";
        for (Cashier person: cashiers) {
            s=s+person.getValue()+"\",\"";
        }
        s=s.substring(0, s.length()-2);
        s=s+"}";

        statement.setString(1, name);
        statement.setString(2, s);
        s="{\"";
        for (Goods item: goods) {
            s=s+item.getValue()+"\",\"";
        }
        s=s.substring(0, s.length()-2);
        s=s+"}";
        statement.setString(3, s);
        s="{\"";
        for (Transaction item: transactions) {
            s=s+item.getValue()+"\",\"";
        }
        s=s.substring(0, s.length()-2);
        s=s+"}";
        int count = statement.executeUpdate();

        System.out.println(count + " records added!");

        statement.close();

    }
}

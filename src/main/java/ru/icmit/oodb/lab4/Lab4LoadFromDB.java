package ru.icmit.oodb.lab4;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.icmit.oodb.lab4.domain.Cashier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Lab4LoadFromDB {
    public static List<Cashier> loadPersonList(Connection connection) throws JsonSyntaxException, SQLException {
        String pStr = "";

        PreparedStatement statement =
                connection.prepareStatement("select content from jtest2 ");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("content");
            System.out.println(pStr);
        }

        statement.close();

        Gson gson = new Gson();

        Cashier[] plst = gson.fromJson(pStr, Cashier[].class);


        return Arrays.asList(plst);
    }


}

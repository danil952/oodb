package ru.icmit.oodb.lab5;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.icmit.oodb.lab5.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Lab5LoadFromDB {
    public static String loadCashier(Connection connection) throws JsonSyntaxException, SQLException {
        String pStr = "";

        PreparedStatement statement =
                connection.prepareStatement("select cashier from shop ");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("cashier");
        }

        statement.close();


        return pStr;
    }

    public static String loadShop(Connection connection) throws JsonSyntaxException, SQLException {
        String pStr = "";

        PreparedStatement statement =
                connection.prepareStatement("select cashier from record ");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("cashier");
        }

        statement.close();


        return pStr;
    }


}

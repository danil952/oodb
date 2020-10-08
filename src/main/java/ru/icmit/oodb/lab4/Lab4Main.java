package ru.icmit.oodb.lab4;

import ru.icmit.oodb.lab4.*;
import ru.icmit.oodb.lab4.domain.Cashier;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Lab4Main {

    public static void main(String[] args) throws IOException, JAXBException {
        try {
            Cashier cashier1=new Cashier("Danil", "Garbazhii", Cashier.Shift.NIGHT);
            Cashier cashier2=new Cashier("Artem", "Pupkin", Cashier.Shift.DAY);
            List<Cashier> list = new ArrayList<>();
            list.add(cashier1);
            list.add(cashier2);

            Class.forName("org.postgresql.Driver");
            String dbURL = "jdbc:postgresql://localhost:1345/oodb";
            Connection connection =
                    DriverManager.getConnection( dbURL, "postgres", "3005952");

            Lab4SaveDB.saveCashierList(list,connection);
            
            list =Lab4LoadFromDB.loadPersonList(connection);
            for (Cashier c: list) {
                System.out.println(c.getName());
                
            }

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

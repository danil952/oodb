package ru.icmit.oodb.lab8;

import com.google.gson.Gson;
import ru.icmit.oodb.lab8.domain.*;
import ru.icmit.oodb.lab8.annotation.Column;
import ru.icmit.oodb.lab8.annotation.Entity;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EntityManagerImpl implements EntityManager {


    public EntityManagerImpl(Connection connection)
    {
        this.connection=connection;
    }


    public Connection connection;


    public static void main(String[] args) throws Exception {

    }



    public static void test(String[] args) throws Exception {

        Shop shop = new Shop();
        shop.id=1;
        shop.setName("Shop1");

        List<Goods> list = new ArrayList<>();

        Goods goods = new Goods();
        goods.setCost(100);
        goods.setQuantity(2);
        goods.setType("Стул");
        goods.id=1;

        list.add(goods);


        Cashier cashier = new Cashier();
        cashier.setId(1);
        cashier.setLastname("Ivanov");
        cashier.setName("Ivan");
        cashier.setShift("Night");

        List<Cashier> list2= new ArrayList<>();
        list2.add(cashier);

        Transaction transaction = new Transaction();
        transaction.id=1;
        transaction.setCashier(cashier);
        transaction.setGoodsList(list);

        List<Transaction> list3 = new ArrayList<>();
        list3.add(transaction);

        shop.setGoods(list);
        shop.setCashiers(list2);
        shop.setTransactions(list3);

        //e.persist(shop);
       // connection.close();

    }

    /**
     * Метод сохраняет экземпляр класса в базу данных
     * @param var1
     */
    @Override
    public void persist(Object var1) throws Exception {


        Method id_method = var1.getClass().getMethod(
                "getId",null);
        String id1 = id_method.invoke(var1).toString();
        if(this.find(var1.getClass(), id1)==null) {
            // Задача этого метода сформировать и выполнить запрос
            // INSERT INTO ...
            // Для этого мы должны получить имя таблицы, имена полей и их значения

            // 1. Проверяем наличие аннотации @Entity
            Annotation entity = var1.getClass().getAnnotation(Entity.class);
            // Если аннотации @Entity нет, то прекращаем работу, генерируя исключение
            if (entity == null)
                throw new Exception("Class " + var1.getClass().getCanonicalName() + " is not Entity!");

            // 1. Получаем имя таблицы
            String tableName = var1.getClass().getSimpleName().toLowerCase();
            HashMap<String, Object> objFields = new HashMap<>();


            // Получаем список членов класса
            Field[] fields = var1.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation[] fannotations = field.getAnnotations();
                HashSet<String> annList = new HashSet<>();
                for (Annotation a : fannotations) {
                    annList.add(a.annotationType().getSimpleName());
                }
                if (annList.contains("Column") && !annList.contains("OneToMany") && !annList.contains("ManyToOne")) {
                    try {
                        Method method = var1.getClass().getMethod(
                                "get" + field.getName().substring(0, 1).toUpperCase() +
                                        field.getName().substring(1), null);

                        Object value = method.invoke(var1);
                        objFields.put(field.getName(), value);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                } else if (annList.contains("ManyToOne")) {
                    Method method = var1.getClass().getMethod(
                            "get" + field.getName().substring(0, 1).toUpperCase() +
                                    field.getName().substring(1), null);

                    Object value = method.invoke(var1);

                    method = value.getClass().getMethod(
                            "getId", null);

                    int id = (int) method.invoke(value);

                    if (this.find(value.getClass(), id) == null) {
                        this.persist(value);
                    }
                    objFields.put(field.getName() + "_id", id);

                }
            }
            String s = "insert into " + tableName + " (";
            for (String key : objFields.keySet()) {
                s = s + key + ",";
            }
            s = s.substring(0, s.length() - 1);
            s = s + ") values (";
            for (String key : objFields.keySet()) {
                String cast = "";
                if (objFields.get(key).getClass().getSimpleName().equals("String")) {
                    cast = "text";
                } else if (objFields.get(key).getClass().getSimpleName().equals("Integer")) {
                    cast = "INTEGER";
                } else {
                    cast = "INTEGER";
                }
                s = s + "cast(? as " + cast + "),";
            }
            s = s.substring(0, s.length() - 1);
            s = s + ")";
            PreparedStatement statement = connection.
                    prepareStatement(s);
            int i = 1;
            for (Object value : objFields.values()) {
                statement.setString(i, value.toString());
                i = i + 1;
            }
            System.out.println(s);
            statement.executeUpdate();
            statement.close();


            //получаем список OneToMany
            Field[] fieldsMany = var1.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation[] fannotations = field.getAnnotations();
                HashSet<String> annList = new HashSet<>();
                for (Annotation a : fannotations) {
                    annList.add(a.annotationType().getSimpleName());
                }
                if (annList.contains("Column") && annList.contains("OneToMany")) {
                    try {
                        Method method = var1.getClass().getMethod(
                                "get" + field.getName().substring(0, 1).toUpperCase() +
                                        field.getName().substring(1), null);

                        List<Object> value = (List) method.invoke(var1);
                        System.out.println("get" + field.getName().substring(0, 1).toUpperCase() +
                                field.getName().substring(1));
                        for (Object item : value) {

                            method = item.getClass().getMethod(
                                    "getId", null);

                            String id = method.invoke(item).toString();
                            if (this.find(item.getClass(), id) == null) {
                                this.persist(item);
                            }
                            s = "insert into " + var1.getClass().getSimpleName().toLowerCase() + "_" + item.getClass().getSimpleName().toLowerCase() + " (";
                            s = s + var1.getClass().getSimpleName().toLowerCase() + "_id," + item.getClass().getSimpleName().toLowerCase() + "_id) values (cast(? as INTEGER),cast(? as INTEGER))";
                            statement = connection.
                                    prepareStatement(s);
                            method = var1.getClass().getMethod(
                                    "getId", null);

                            id = method.invoke(var1).toString();
                            statement.setString(1, id);
                            method = item.getClass().getMethod(
                                    "getId", null);

                            id = method.invoke(item).toString();
                            statement.setString(2, id);
                            statement.executeUpdate();
                            statement.close();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    @Override
    public <T> T merge(T var1)  {
        try {
            Method id_method = var1.getClass().getMethod(
                        "getId",null);
            String id = id_method.invoke(var1).toString();
            Object del=this.find(var1.getClass(), id);
            this.remove(del);
            this.persist(var1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(Object var1) {

        try {
        Method method = var1.getClass().getMethod(
                "getId",null);
        String id =  method.invoke(var1).toString();
        PreparedStatement statement =
                connection.prepareStatement("delete from "+var1.getClass().getSimpleName().toLowerCase()+" where id="+id);
        statement.execute();
        statement.close();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public <T> T find(Class<T> var1, Object var2) throws SQLException {

        try {

            T object = var1.newInstance();

            Gson gson = new Gson();

            String id =  var2.toString();
            PreparedStatement statement =
                    connection.prepareStatement("select * from "+object.getClass().getSimpleName().toLowerCase()+" where id="+id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                statement.close();
                return null;

            }


            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields)
            {
                    Annotation[] fannotations = field.getAnnotations();
                    HashSet<String> annList = new HashSet<>();
                    for (Annotation a : fannotations) {
                        annList.add(a.annotationType().getSimpleName());
                    }
                    if (annList.contains("Column") && !annList.contains("OneToMany") && !annList.contains("ManyToOne")) {


                            Object s;
                            if (field.getType().getSimpleName() == "int") {
                                s = Integer.parseInt(resultSet.getString(field.getName()));
                            } else {

                                s = resultSet.getString(field.getName());
                            }
                            Method method = object.getClass().getMethod(
                                    "set" + field.getName().substring(0, 1).toUpperCase() +
                                            field.getName().substring(1), field.getType());
                            method.invoke(object, s);


                    }
                }
                statement.close();
                return object;
            } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
        return null;


    }

    @Override
    public void refresh(Object var1) {

    }
}

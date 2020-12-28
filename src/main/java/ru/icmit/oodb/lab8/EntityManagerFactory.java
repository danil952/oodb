package ru.icmit.oodb.lab8;

import ru.icmit.oodb.lab7.annotation.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EntityManagerFactory {


    public static String PATH_FOR_SCAN = "ru.icmit.oodb.lab7.domain";

    /**
     * Подключение к БД. Это подключение передается в класс реализацию EntityManager
     * при его создании.
     */
    private Connection connection;

    /**
     * Параметры подключения к базе данных:
     * <p> url - путь к БД для JDBC драйвера (например "jdbc:postgresql://localhost:5432/lab8")
     * <p> username - имя пользователя СУБД
     * <p> password - пароль пользователя СУБД
     */
    private Properties dbProperties;

    /**
     * Структура для хранения сведений о базе данных (таблицы, их поля)
     */
    private HashMap<String, HashSet<String>> tables = new HashMap<>();

    /**
     * Конструктор класса
     * @param dbProperties - параметры подключения к базе данных
     */
    public EntityManagerFactory(Properties dbProperties) {
        this.dbProperties = dbProperties;
        this.connection=getConnection();
    }

    /**
     * Метод создает, инициализирует экземпляр класса, реализующего EntityManager
     */
    public EntityManagerImpl createEntityManager() {
        if(scanModel())
        {
            return new EntityManagerImpl(connection);
        }
        else
        {
            return null;
        }
    }

    /**
     * Метод создает (если еще не создано) и возвращает подключение к БД
     */
    private Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(dbProperties.getProperty("url"),
                        dbProperties.getProperty("username"), dbProperties.getProperty("password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void close() {
        closeConnection();
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод сканирует структуру классов модели, структуру базы данных,
     * сравнивает их и возвращает булевский результат сравнения
     */
    private boolean scanModel() {

        Boolean result =true;

        /* Сканируем пакет PATH_FOR_SCAN для поиска классов-сущностей  */
        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        List<Class<?>> clList=classList.stream().filter(c -> classIsEntity(c)).collect(Collectors.toList());
        tables = ScanDB.returnTables();

        System.out.println("Проверяем корректность БД");
        for (Class<?> cl : clList) {
            System.out.println("------------------------------------------");
            if(tables.containsKey(cl.getSimpleName().toLowerCase()))
            {
                HashSet<String> tableFields=tables.get(cl.getSimpleName().toLowerCase());
                System.out.println(cl.getSimpleName().toLowerCase()+" содержится в БД");
                System.out.println("Проверям поля "+cl.getSimpleName().toLowerCase());
                Field[] fields = cl.getDeclaredFields();
                for (Field field : fields) {
                    Annotation[] fannotations = field.getAnnotations();
                    HashSet<String> annList = new HashSet<>();
                    for(Annotation a : fannotations) {
                        annList.add(a.annotationType().getSimpleName());
                    }

                    if (annList.contains("OneToMany")) {
                        Type type = field.getGenericType();
                        String nodeTargetName="";
                        String[] bits;
                        if (type instanceof ParameterizedType) {
                            ParameterizedType pType = (ParameterizedType) type;
                            bits=pType.getActualTypeArguments()[0].getTypeName().split("\\.");
                            nodeTargetName=bits[bits.length-1];
                        }
                        if(tables.containsKey(cl.getSimpleName().toLowerCase()+"_"+nodeTargetName.toLowerCase()))
                        {
                            System.out.println("Связь OneToMany содержится: "+cl.getSimpleName().toLowerCase()+"_"+nodeTargetName.toLowerCase());
                        }
                        else
                        {
                            System.out.println("Связь OneToMany отсутствует");
                            result =false;
                        }
                    }
                    else if(annList.contains("ManyToOne"))
                    {
                        if(tableFields.contains(field.getName().toLowerCase()+"_id"))
                        {
                            System.out.println("Связь ManyToOne содержится: "+field.getName().toLowerCase()+"_id");
                        }
                        else
                        {
                            System.out.println("Связь ManyToOne отсутствует");
                            result =false;
                        }
                    }
                    else
                    {
                        if(tableFields.contains(field.getName().toLowerCase()))
                        {
                            System.out.println("Поле "+field.getName().toLowerCase()+" содержится в таблице БД "+cl.getSimpleName().toLowerCase());
                        }
                        else
                        {
                            System.out.println("Отсутсвует поле "+field.getName().toLowerCase());
                            result =false;
                        }
                    }
                }
            }
            else
            {
                System.out.println("Отсутсвует таблица "+cl.getSimpleName().toLowerCase());
                result =false;
            }

        }

        return result;
    }

    private static boolean classIsEntity(Class<?> c) {
        Annotation[] annotations = c.getAnnotations();
        for (Annotation a : annotations) {
            if (a.annotationType().equals(Entity.class)) {
                return true;
            }
        }
        return false;
    }
}

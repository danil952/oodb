package ru.icmit.oodb.lab7;

import ru.icmit.oodb.lab7.annotation.Entity;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class Lab7Main2 {

    // Пакет в котором расположены классы-сущности
    public static String PATH_FOR_SCAN = "ru.icmit.oodb.lab7.domain";

    public static void main(String[] args) {

        /* Сканируем пакет PATH_FOR_SCAN для поиска классов-сущностей  */
        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        List<Class<?>> clList=classList.stream().filter(c -> classIsEntity(c)).collect(Collectors.toList());
        HashMap<String, HashSet<String>> tables = ScanDB.returnTables();

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
                        }
                    }
                }
            }
            else
            {
                System.out.println("Отсутсвует таблица "+cl.getSimpleName().toLowerCase());
            }

        }


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

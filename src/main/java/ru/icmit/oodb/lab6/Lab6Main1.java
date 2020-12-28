package ru.icmit.oodb.lab6;

import ru.icmit.oodb.lab6.annotation.Column;
import ru.icmit.oodb.lab6.annotation.Entity;
import ru.icmit.oodb.lab7.PathScan;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Програма демонстрирует "разбор" классов в выбранном пакете
 */
public class Lab6Main1 {

    public static String PATH_FOR_SCAN = "ru.icmit.oodb.lab6.domain";

    public static void main(String[] args) {

        /* Просканируем пакет PATH_FOR_SCAN для поиска классов (включая вложенные пакеты)  */
        System.out.println("STEP 1: scan package " + PATH_FOR_SCAN+":");

        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        if (classList != null)
            classList.forEach(c->System.out.println("\t" + c.getSimpleName().toLowerCase()));

        System.out.println("STEP 2: scan class fields:");
        for (Class<?> cl : classList) {
            /* Сканируем поля классов */
            System.out.println("\tFields of class " + cl.getName());
             ;
            for (Field field : cl.getDeclaredFields()) {
                System.out.println("\t\t" + field.getName());
            }
        }

        System.out.println("STEP 3: scan class methods:");
        for (Class<?> cl : classList) {
            /* Сканируем методы классов */
            System.out.println("\tFields of class " + cl.getName());
            Method[] methods = cl.getMethods();
            for (Method method : methods) {
                System.out.println("\t\t" + method.getName());
            }
        }


        System.out.println("STEP 4: scan class annotations:");
        for (Class<?> cl : classList) {
            Annotation[] annotations = cl.getAnnotations();
            if (annotations != null) {
                for (Annotation a : annotations) {
                    if (a.annotationType().equals(Entity.class)) {
                        System.out.println("\t"+cl.getSimpleName()+" is entity!");
                    }
                }
            }
        }

        System.out.println("STEP 5: scan fields annotations:");

        for (Class<?> cl : classList) {
            Field[] fields = cl.getDeclaredFields();
            for (Field f : fields) {
                Annotation[] fannotations = f.getAnnotations();
                for (Annotation a : fannotations) {
                    if (a.annotationType().equals(Column.class)) {
                        System.out.println(String.format("\tField %s %s is attribute!", f.getType().getName(), f.getName()));
                    }
                }
            }
        }


        System.out.println("STEP 6: get superclass:");

    }
}

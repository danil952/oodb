package ru.icmit.oodb.lab6;

import ru.icmit.oodb.lab6.annotation.Entity;
import ru.icmit.oodb.lab6.annotation.OneToMany;
import ru.icmit.oodb.lab6.graph.*;
import ru.icmit.oodb.lab7.PathScan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Програма демонстрирует формирование графа сущностей и связей между ними
 */
public class Lab6Main2 {

    // Пакет в котором расположены классы-сущности
    public static String PATH_FOR_SCAN = "ru.icmit.oodb.lab6.domain";

    public static void main(String[] args) {

        GraphModel graph = new GraphModel();
        List<EntityNode> entityNodeList = new ArrayList<>();

        List<Edge> edges = new ArrayList<>();

        /* Сканируем пакет PATH_FOR_SCAN для поиска классов-сущностей  */
        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        if (classList != null)
            //classList.stream().filter(c -> classIsEntity(c)).forEach();

        System.out.println("STEP 2: scan class fields:");
        for (Class<?> cl : classList) {
            /* Сканируем поля классов */
            System.out.println("\tFields of class " + cl.getName());
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
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
        int id=0;
        for (Class<?> cl : classList) {
            Annotation[] annotations = cl.getAnnotations();
            if (annotations != null) {
                for (Annotation a : annotations) {
                    if (a.annotationType().equals(Entity.class)) {
                        System.out.println("\t"+cl.getSimpleName()+" is entity!");
                        EntityNode node = new EntityNode();
                        node.setEntityClass(cl);
                        node.setEntityName(cl.getSimpleName());
                        entityNodeList.add(node);
                        List<EntityAttribute> list = new ArrayList<>();
                        EntityAttribute idAttr= new EntityAttribute();
                        idAttr.setAttributeType("id");
                        idAttr.setAttributeValue(Integer.toString(id));
                        list.add(idAttr);
                        node.setAtributes(list);
                        id=id+1;
                    }
                }
            }
        }

        System.out.println("STEP 5: scan fields annotations:");

        for (Class<?> cl : classList) {
            Field[] fields = cl.getDeclaredFields();
            for (Field f : fields) {
                Edge e = new Edge();
                Annotation[] fannotations = f.getAnnotations();
                for (Annotation a : fannotations) {
                    if (a.annotationType().equals(OneToMany.class)) {
                        Type type = f.getGenericType();
                        String nodeTargetName="";
                        String[] bits;
                        if (type instanceof ParameterizedType) {
                            ParameterizedType pType = (ParameterizedType) type;
                            bits=pType.getActualTypeArguments()[0].getTypeName().split("\\.");
                            nodeTargetName=bits[bits.length-1];
                        }
                        System.out.println(cl.getSimpleName()+" "+nodeTargetName);
                        for (EntityNode n:entityNodeList) {
                            if(n.getEntityName().equals(cl.getSimpleName()))
                            {
                                e.setNodeSource(n);
                            }
                            if(n.getEntityName().equals(nodeTargetName))
                            {
                                e.setNodeTarget(n);
                            }
                            e.setRelationType(RelationType.valueOf(a.annotationType().getSimpleName()));

                        }
                    }
                }
                if(e.getNodeTarget()!=null)
                {
                    edges.add(e);
                }
            }
        }

        graph.setEdges(edges);
        graph.setEntityNodeList(entityNodeList);
        graph.printGraph();

    }

    private static boolean classIsEntity(Class<?> c) {
        Annotation[] annotations = c.getAnnotations();
        for (Annotation a : annotations) {
            if (a.equals(Entity.class)) {
                return true;
            }
        }
        return false;
    }

    private static void addEntityToGraph(Class<?> c, GraphModel graph) {
    }


}

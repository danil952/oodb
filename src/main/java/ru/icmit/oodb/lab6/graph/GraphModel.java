package ru.icmit.oodb.lab6.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphModel {

    private List<EntityNode> entityNodeList = new ArrayList<>();

    private List<Edge> edges = new ArrayList<>();

    public void addEntity(Class<?> c) {

    }

    public List<EntityNode> getEntityNodeList() {
        return entityNodeList;
    }

    public void setEntityNodeList(List<EntityNode> entityNodeList) {
        this.entityNodeList = entityNodeList;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void printGraph()
    {
        String s="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\" \n" +
                "    schemaLocation=\"http://graphml.graphdrawing.org/xmlns\">\n" +
                "  <graph edgedefault=\"undirected\" id=\"G\">\n";
        for (EntityNode n:entityNodeList) {
            s=s+"<node "+n.getAtributes().get(0).getAttributeType()+"="+"\"n"+n.getAtributes().get(0).getAttributeValue()+"\'>";
            s=s+"\n</node>\n";

        }
        int i=0;
        for(Edge e: edges)
        {
            s=s+"<edge id=\"e"+i+"\" source=\"n"+e.getNodeSource().getAtributes().get(0).getAttributeValue()+"\" ";
            s=s+"target=\"n"+e.getNodeTarget().getAtributes().get(0).getAttributeValue()+"\" ";
            s=s+"relationship=\""+e.getRelationType()+"\"/>\n";
            i=i+1;
        }
        s=s+"\n</graph>\n" +
                "</graphml>";
        System.out.println(s);
    }
}

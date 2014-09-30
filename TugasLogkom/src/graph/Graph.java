/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author victoria.anugrah
 */
public class Graph {
    ArrayList<Edge> edgeList;
    
    public Graph(){
        edgeList = new ArrayList<Edge>();
    }
    
    /**
     * Add edge to the graph
     * @param e the edge
     */
    public void add(Edge e){
        edgeList.add(e);
    }
    
    public void addRect(int a, int b)
    {
        Edge tmp = new Edge(a,b);
        edgeList.add(tmp);
    }
    
    public ArrayList<Edge> getEdgeList()
    {
        return edgeList;
    }
    
    /**
     * Remove an edge that contains rectangle r from the graph
     * @param r the rectangle
     */
    //public void remove(Rectangle r){
    //    for (Edge e : edgeList){
    //        if (e.contains(r)){
    //            edgeList.remove(e);
    //        }
    //    }
   // }
    
}

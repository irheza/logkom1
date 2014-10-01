/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

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
     * Contains an edge that contains rectangle n from the graph
     * @param n
     * @return 
     */
    public boolean contains(int n){
        for (Edge e : edgeList){
            if (e.contains(n)){
                return true;
            }
        }
        return false;
    }
    
}

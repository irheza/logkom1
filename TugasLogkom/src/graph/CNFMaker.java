/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.util.ArrayList;

/**
 *
 * @author immanuel.rhesa
 */
public class CNFMaker {
    Graph graph;
    public CNFMaker(Graph graph)
    {
        this.graph = graph;
    }
    public ArrayList<Integer[]> makeCNF()
    {
        ArrayList<Edge> terhubung = graph.getEdgeList();
        ArrayList<Integer[]> tmp = new ArrayList<Integer[]>();
        for(int i=0; i<terhubung.size();i++)
        {
            int a = terhubung.get(i).r1;
            int b = terhubung.get(i).r2;
            String a0s= ""+a+"0";
            int a0 = Integer.parseInt(a0s);
            String a1s= ""+a+"1";
            int a1 = Integer.parseInt(a1s);
            String b0s= ""+b+"0";
            int b0 = Integer.parseInt(b0s);
            String b1s= ""+b+"1";
            int b1 = Integer.parseInt(b1s);
            Integer[] clause1 = {-a0, -b0, -a1,-b1};
            Integer[] clause2 = {-a0, -b0, a1,b1};
            Integer[] clause3 = {a0, b0, -a1,-b1};
            Integer[] clause4 = {a0, b0, a1,b1};
            tmp.add(clause1);
            tmp.add(clause2);
            tmp.add(clause3);
            tmp.add(clause4);
        }
       
        return tmp;
    }
}

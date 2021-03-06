/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author immanuel.rhesa
 */
public class CNFMaker {

    Graph graph;
    int jumlahvariabel;

    public CNFMaker(Graph graph, int jumlahvariabel) {
        this.graph = graph;
        this.jumlahvariabel = jumlahvariabel;
    }

    public int getJumlahVariabel() {
        return jumlahvariabel;
    }

    public ArrayList<int[]> makeCNF() {
        ArrayList<Edge> terhubung = graph.getEdgeList();
        ArrayList<int[]> listClause = new ArrayList<int[]>();
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        Set<Integer> set = new TreeSet<>();

        for (Edge e : terhubung) {
            set.add(e.r1);
            set.add(e.r2);
        }
        
        Iterator it = set.iterator();

        for (int k = 0; it.hasNext(); k++) {
            int key = (Integer) it.next();
            hm.put(key, k);
            System.out.println("Key: " + key + " value: " + k);
        }

        for (int i = 0; i < terhubung.size(); i++) {            
            int a = terhubung.get(i).r1;
            int b = terhubung.get(i).r2;
            int a0 = 2*hm.get(a)+1;
            int a1 = a0+1;
            int b0 = 2*hm.get(b)+1;
            int b1 = b0+1;

            int[] clause1 = {-a0, -b0, -a1, -b1};
            int[] clause2 = {-a0, -b0, a1, b1};
            int[] clause3 = {a0, b0, -a1, -b1};
            int[] clause4 = {a0, b0, a1, b1};

            listClause.add(clause1);
            listClause.add(clause2);
            listClause.add(clause3);
            listClause.add(clause4);
        }
        return listClause;
    }
}

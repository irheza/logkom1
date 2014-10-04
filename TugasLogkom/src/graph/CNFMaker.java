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
        ArrayList<int[]> tmp = new ArrayList<int[]>();
        int var = 1; 
        int a0 = var++;
        int a1 = var++;
        int b0 = var++;
        int b1 = var++;
        System.out.println("terhubung: " + terhubung.size());
        for (int i = 0; i < terhubung.size(); i++) {
//            int a = terhubung.get(i).r1;
//            int b = terhubung.get(i).r2;

//            String a0s= ""+a+"0";
//            int a0 = Integer.parseInt(a0s);
//            String a1s= ""+a+"1";
//            int a1 = Integer.parseInt(a1s);
//            String b0s= ""+b+"0";
//            int b0 = Integer.parseInt(b0s);
//            String b1s= ""+b+"1";
//            int b1 = Integer.parseInt(b1s);

            int[] clause1 = {-a0, -b0, -a1, -b1};
            int[] clause2 = {-a0, -b0, a1, b1};
            int[] clause3 = {a0, b0, -a1, -b1};
            int[] clause4 = {a0, b0, a1, b1};

            tmp.add(clause1);
            tmp.add(clause2);
            tmp.add(clause3);
            tmp.add(clause4);

            a0++;
            a1++;
            b0++;
            b1++;
        }
        return tmp;
    }
}

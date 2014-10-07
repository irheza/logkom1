/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

/**
 *
 * @author immanuel.rhesa
 */
public class Edge {
    int r1;
    int r2;
    
    public Edge(int r1, int r2){
        this.r1 = r1;
        this.r2 = r2;
    }
    
    public boolean contains(int r){
        return (this.r1 == r || this.r2 == r);
    }
}

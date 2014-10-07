/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.ArrayList;

/**
 *
 * @author V Anugrah
 */
public class Minisat {
    CNFMaker cnf;
    ArrayList<int[]> clauses;
    
    public Minisat(CNFMaker cnf){
        this.cnf = cnf;
        clauses = cnf.makeCNF();
    }
    
    public void addClause(int[] clause){
        clauses.add(clause);
    }
    
    public int[] negated(int[] clause){
        int[] newClause = new int[clause.length-1];
        
        for (int i = 0; i < clause.length-1; i++){           
                newClause[i] = clause[i] * -1;
        }
        return newClause;
    }
    
    public int[] solve() throws Exception{
          MinisatInputMaker minisat = new MinisatInputMaker();

          int[]value = minisat.runAllMinisat(clauses, cnf.getJumlahVariabel());
          if(value.length<=0)
          {
              throw new Exception("This problem is unsolvable.");
          }
        return value;
    }
}
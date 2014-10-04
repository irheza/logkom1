/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.util.ArrayList;
import minisat.MinisatInputMaker;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

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
    
    public int[] solve() throws ContradictionException, TimeoutException, Exception{
//        final int MAXVAR = 10000;
//        final int MAXCLAUSES = clauses.size();
//        int[] value = {};
//        ISolver solver = SolverFactory.newDefault();
//        solver.newVar(MAXVAR);
//        solver.setExpectedNumberOfClauses(MAXCLAUSES);
//        
//        for (int[] clause : clauses){
//            solver.addClause(new VecInt(clause));
//        }
//        
//        IProblem problem = solver;
//        Reader reader = new DimacsReader(solver);
//        
//        if (problem.isSatisfiable()) {
//            System.out.println("dapet");
//            value = problem.model();
//            String result = reader.decode(value);
//            System.out.println(result);
//        }
//        else{
//            throw new Exception("This problem is unsolvable.");
//        }
          MinisatInputMaker minisat = new MinisatInputMaker();
          System.out.println("Isi clauses:");
          for(int i=0; i<clauses.size();i++)
          {
              for(int j=0; j<clauses.get(i).length;j++)
              {
                  System.out.print(clauses.get(i)[j]+" ");
              }
              System.out.println("");
          }
          int[]value = minisat.runAllMinisat(clauses, cnf.getJumlahVariabel());
          if(value.length<=0)
          {
              throw new Exception("This problem is unsolvable.");
          }
        return value;
    }
}

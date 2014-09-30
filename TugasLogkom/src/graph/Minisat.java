/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.util.ArrayList;
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
    public int[] solve() throws ContradictionException, TimeoutException{
        final int MAXVAR = 10000;
        final int MAXCLAUSES = clauses.size();
        int[] value = {};
        ISolver solver = SolverFactory.newDefault();
        solver.newVar(MAXVAR);
        solver.setExpectedNumberOfClauses(MAXCLAUSES);
        
        for (int[] clause : clauses){
            solver.addClause(new VecInt(clause));
        }
        
        IProblem problem = solver;
        Reader reader = new DimacsReader(solver);
        
        if (problem.isSatisfiable()) {
            System.out.println("dapet");
            value = problem.model();
            String result = reader.decode(value);
            System.out.println(result);
        }
        else{
            System.out.println("gagal");
        }
        return value;
    }
}

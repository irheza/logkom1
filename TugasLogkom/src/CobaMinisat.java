/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Rhesa
 */
public class CobaMinisat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TimeoutException, ContradictionException {
        final int MAXVAR = 1000000;
        final int NBCLAUSES = 3;

        ISolver solver = SolverFactory.newDefault();

        // prepare the solver to accept MAXVAR variables. MANDATORY for MAXSAT solving
        solver.newVar(MAXVAR);

        solver.setExpectedNumberOfClauses(NBCLAUSES);
        // Feed the solver using Dimacs format, using arrays of int
        // (best option to avoid dependencies on SAT4J IVecInt)
        //for (int i = 0; i < NBCLAUSES; i++) {
            int[] clause = {-10, -20, -11,-21}; // get the clause from somewhere
            int[] clause2 = {-10, -20, 11,21}; 
            int[] clause3 = {10, 20, -11,-21}; 
            int[] clause4 = {10, 20, 11,21}; 
            
            int[] clause5 = {-10, -30, -11,-31}; // get the clause from somewhere
            int[] clause6 = {-10, -30, 11,31}; 
            int[] clause7 = {10, 30, -11,-21}; 
            int[] clause8 = {10, 30, 11,31}; 
            
            int[] clause9 = {-20, -30, -21,-31}; // get the clause from somewhere
            int[] clause10 = {-20, -30, 21,31}; 
            int[] clause11 = {20, 30, -21,-21}; 
            int[] clause12 = {20, 30, 21,31}; 
            // the clause should not contain a 0, only integer (positive or negative)
            // with absolute values less or equal to MAXVAR
            // e.g. int [] clause = {1, -3, 7}; is fine
            // while int [] clause = {1, -3, 7, 0}; is not fine 
            solver.addClause(new VecInt(clause)); // adapt Array to IVecInt
            solver.addClause(new VecInt(clause2));
            solver.addClause(new VecInt(clause3));
            solver.addClause(new VecInt(clause4));
            solver.addClause(new VecInt(clause5));
            solver.addClause(new VecInt(clause6));
            solver.addClause(new VecInt(clause7));
            solver.addClause(new VecInt(clause8));
            solver.addClause(new VecInt(clause9));
            solver.addClause(new VecInt(clause10));
            solver.addClause(new VecInt(clause11));
            solver.addClause(new VecInt(clause12));
            

        //}

        // we are done. Working now on the IProblem interface
        IProblem problem = solver;
        Reader reader = new DimacsReader(solver);
        if (problem.isSatisfiable()) {
            System.out.println("ho dapet");
            System.out.println(reader.decode(problem.model()));
        } else {
            System.out.println("gagal");
        }
    }

}

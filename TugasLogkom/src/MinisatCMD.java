
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import minisat.MinisatInputMaker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rhesa
 */
public class MinisatCMD {
    public static void main(String[]args) throws IOException, InterruptedException
    {
       
         ArrayList<int[]> CNF = new ArrayList<int[]>();
  
         int[] a = {1,-5,4};
         int[] b = {-1,5,3,4};
         int[] c = {-3,-4};
         //int[]a = {1};
         //int[]b = {-1};
         CNF.add(a);
         CNF.add(b);
         CNF.add(c);
         MinisatInputMaker maker = new MinisatInputMaker();
         String inpu = maker.createInput(CNF, 5);
         maker.writeMinisatInput(inpu);
         maker.runMinisat();
         
         System.out.println(maker.checkSAT());
         int[] hasil = maker.hasilMinisat();
         for(int i=0;i<hasil.length;i++)
         {
             System.out.println(hasil[i]);
         }
         
    }
}

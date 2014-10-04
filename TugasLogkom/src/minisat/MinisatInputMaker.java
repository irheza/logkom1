/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minisat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Rhesa
 */
public class MinisatInputMaker {
    public String createInput(ArrayList<int[]> input, int jumlahvariabel)
    {
        
         String msinput = "p cnf "+jumlahvariabel*2+" "+input.size()+"\n";
         for(int i=0;i<input.size();i++)
         {
             int[] tp = input.get(i);
             for(int j=0;j<tp.length;j++)
             {
                 msinput=msinput+tp[j]+" ";
             }
             msinput=msinput+"0\n";
         }
         return msinput;
    }
    public void writeMinisatInput(String minisatinput) throws IOException
    {
         File file = new File("D:/tes1.cnf");
         if(!file.exists())
        {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(minisatinput);
        bw.close();
    }
    public void runMinisat() throws IOException, InterruptedException
    {
        Runtime rt = Runtime.getRuntime();
        File filetemp = new File("D:/out1.out");
        if(filetemp.exists())
        {
            filetemp.delete();
            
        }
        Process proc = rt.exec("minisat D:/tes1.cnf D:/out1.out");
        proc.waitFor();
        
    }
    
    
    public boolean checkSAT() throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("D:/out1.out"));
        String line = reader.readLine();
        if(line.trim().equalsIgnoreCase("unsat"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public int[] hasilMinisat() throws IOException
    {
        ArrayList<Integer> tmp = new ArrayList<Integer>();

        if(checkSAT())
        {
           BufferedReader readerlagi = new BufferedReader(new FileReader("D:/out1.out"));
           String sat =  readerlagi.readLine();
           String[] hasilsplit = readerlagi.readLine().split("\\s");
           for(int i=0; i<hasilsplit.length;i++)
           {
               tmp.add(Integer.parseInt(hasilsplit[i]));
           }
        }
        int[] hasil = new int[tmp.size()];
        for(int i=0;i<tmp.size();i++)
        {
            hasil[i] = tmp.get(i);
        }
        return hasil;

    }
    
    public int[] runAllMinisat(ArrayList<int[]> input, int banyakvariabel) throws IOException, InterruptedException
    {
        String cnf = createInput(input,banyakvariabel);
        writeMinisatInput(cnf);
        runMinisat();
        int[] hasil = hasilMinisat();
        return hasil;
        
    }
    
}

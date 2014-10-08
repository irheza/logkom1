/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import main.Runner;
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
    /**
     * Menghasilkan string format MINISAT untuk ditulis ke file
     * @param input list of clause
     * @param jumlahvariabel jumlah variabel
     * @return 
     */
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
    /**
     * Menulis input string ke file
     * @param minisatinput string yang ditulis ke file
     * @throws IOException 
     */
    public void writeMinisatInput(String minisatinput) throws IOException
    {
         File file = new File(Runner.pilihan+"/tes1.cnf");
         if(!file.exists())
        {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(minisatinput);
        bw.close();
    }
    /**
     * Jalankan proses minisat dan tulis ke file out1.out
     * @throws IOException
     * @throws InterruptedException 
     */
    public void runMinisat() throws IOException, InterruptedException
    {
        Runtime rt = Runtime.getRuntime();
        File filetemp = new File(Runner.pilihan+"/out1.out");
        if(filetemp.exists())
        {
            filetemp.delete();
            
        }
        Process proc = rt.exec("minisat "+Runner.pilihan+"/tes1.cnf "+Runner.pilihan+"/out1.out");
        proc.waitFor();
        
    }
    
    /**
     * Cek apakah problem CNF bersifat SAT atau UNSAT
     * @return false jika UNSAT, true otherwise
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public boolean checkSAT() throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(Runner.pilihan+"/out1.out"));
        String line = reader.readLine();
         reader.close();
        if(line.trim().equalsIgnoreCase("unsat"))
        {
            return false;
        }
        else
        {
            return true;
        }
       
    }
    /**
     * Ambil hasil minisat dari string
     * @return array of integer hasil minisat
     * @throws IOException 
     */
    public int[] hasilMinisat() throws IOException
    {
        ArrayList<Integer> listAngkaBaca = new ArrayList<Integer>();

        if(checkSAT())
        {
           BufferedReader reader = new BufferedReader(new FileReader(Runner.pilihan+"/out1.out"));
           String SatOrUnsat =  reader.readLine();
           String[] hasilsplit = reader.readLine().split("\\s");
           for(int i=0; i<hasilsplit.length;i++)
           {
               listAngkaBaca.add(Integer.parseInt(hasilsplit[i]));
           }
           reader.close();
        }
        int[] hasil = new int[listAngkaBaca.size()];
        for(int i=0;i<listAngkaBaca.size();i++)
        {
            hasil[i] = listAngkaBaca.get(i);
        }
        return hasil;

    }
    
    /**
     * Diberikan input array list of array integer dan jumlah variabel, 
     * mengembalikan jawaban
     * @param listClause array list of array yang berisi clause2
     * @param banyakvariabel banyak variabel yang dipakai
     * @return solusi dari CNF
     * @throws IOException
     * @throws InterruptedException 
     */
    public int[] runAllMinisat(ArrayList<int[]> listClause, int banyakvariabel) throws IOException, InterruptedException
    {
        int[] hasil;
        if(!listClause.isEmpty())
        {
            String cnf = createInput(listClause,banyakvariabel);
            writeMinisatInput(cnf);
            runMinisat();
            hasil = hasilMinisat();
        }
        else
        {
            hasil = new int[]{0};
          
        }
        return hasil;       
    }
}
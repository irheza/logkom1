package main;


import gui.MainGUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author V Anugrah
 */
public class Runner {
    public static String drivePilihan;
    public static void main(String[] args) throws ContradictionException, TimeoutException {
        Object[] possibilities = {"D:", "E:", "X:"};
      
        JFrame jFrame = new JFrame();
        drivePilihan = (String)JOptionPane.showInputDialog(
                    jFrame,
                    "Pilih lokasi penyimpanan file Minisat:\n",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "D:");
        if(drivePilihan==null)
        {
            System.exit(0);
        }
        
        jFrame.setTitle("Tugas 1 Logika Komputasional");
        jFrame.setSize(1000, 700);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        MainGUI mg = new MainGUI();
        jFrame.getContentPane().add(mg);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }    
}
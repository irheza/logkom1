/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.JFrame;

/**
 *
 * @author V Anugrah
 */
public class MapViewer{
    JFrame thisFrame;
    public MapViewer(){
        thisFrame = new JFrame();
        thisFrame.setSize(400, 400);
        thisFrame.setTitle("Tugas Logkom");
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        thisFrame.setVisible(true);
        thisFrame.setLocationRelativeTo(null);        
    }    
}

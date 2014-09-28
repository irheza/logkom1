/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author V Anugrah
 */
public class InputViewer {
    JFrame mainFrame;
    JPanel mainPanel;
    
    public InputViewer(JFrame other){
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        createPanel(other);
        createFrame();
    }
    public void createFrame(){
        mainFrame.setSize(400, 200);
        mainFrame.setTitle("Tugas Logkom");
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
    
    public void createPanel(final JFrame other){
        JButton ok = new JButton("ok");
        ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                other.setVisible(true);
            }
            
        });
        mainPanel.add(ok);
    }
}

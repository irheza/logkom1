/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author V Anugrah
 */
public class MapViewer {

    JFrame thisFrame;
    JPanel mainPanel; // panel utama untuk menampung semua
    Rectangle r1;

    public MapViewer() {
        createPanel();
        thisFrame = new JFrame();
        thisFrame.add(mainPanel);
        thisFrame.setSize(400, 400);
        thisFrame.setTitle("Tugas Logkom");
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.setLocationRelativeTo(null);
    }

    public void createPanel() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);        
        final MyRectangle mr = new MyRectangle(50,50,Color.RED); 
        final MyMouseListener ml = new MyMouseListener();
        mainPanel.addMouseListener(ml);
        
//        mr.addMouseListener(ml);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JLabel("halo"), BorderLayout.NORTH);
        mainPanel.add(mr, BorderLayout.CENTER);
        
        JButton freeze = new JButton("freeze");
        mainPanel.add(freeze, BorderLayout.SOUTH);
        freeze.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                mr.removeMouseListener(ml);
            }
        });
    }
    private class MyMouseListener implements MouseListener{
        
        @Override
        public void mouseClicked(MouseEvent me) {
            System.out.println("Click me " + me.getX() + " " + me.getY());
        }

        @Override
        public void mousePressed(MouseEvent me) {
            System.out.println("Press me");
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            System.out.println("Release me");
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            System.out.println("Enter me");
        }

        @Override
        public void mouseExited(MouseEvent me) {
            System.out.println("Exit me");
        }
    }
    
    
    private class MyRectangle extends JComponent{
        Rectangle rect1;
        Rectangle rect2;
        int x, y;
        Color c;
        
        public MyRectangle(int x, int y, Color c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
        
        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            rect1 = new Rectangle(x, y, 100, 100);
                        
            g2.setColor(c);
            g2.fill(rect1);
            
            rect2 = new Rectangle(100,100, 200, 50);
            g2.setColor(Color.YELLOW);
            g2.fill(rect2);
        }
    }
}

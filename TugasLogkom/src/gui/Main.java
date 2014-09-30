package gui;

import graph.*;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JPanel implements MouseMotionListener {

    private static final int recW = 70;
    private static final int MAX = 100;
    private Rectangle[] rect = new Rectangle[MAX];
    private int numOfRecs = 0;
    private int currentSquareIndex = -1;
    private boolean addRecStatus = true;
    private Graph graph = new Graph();

    public Main() {
        JButton ok = new JButton("ok");
          ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                addRecStatus = false;
                for(int i=0; i<numOfRecs;i++)
                {
                    for(int j=i+1;j<numOfRecs;j++)
                   {
                      
                       if(rect[i].intersects(rect[j]))
                       {
                          graph.addRect(i, j);
                          System.out.println(i+ " dengan "+j);
                       }
                   }
                }
                CNFMaker cnf = new CNFMaker(graph);
                ArrayList<Integer[]> tmp = cnf.makeCNF();
                for(int i=0; i<tmp.size(); i++)
                  {
                      Integer[] hasil = tmp.get(i);
                      System.out.print("{ ");
                      for(int j=0; j<hasil.length;j++)
                      {
                         System.out.print(hasil[j]+" ");
                      }
                      System.out.println(" }");
                  }
                
                
            }  
        });
        this.add(ok);

        addMouseListener(new MouseAdapter() {

        @Override

        public void mousePressed(MouseEvent evt) {

           if(addRecStatus)
           {
                int x = evt.getX();

                int y = evt.getY();

                currentSquareIndex = getRec(x, y);
           
            if (currentSquareIndex < 0 && addRecStatus) // not inside a square

            {

                add(x, y);

            }
           }

        }

      @Override

      public void mouseClicked(MouseEvent evt) {

          int x = evt.getX();

          int y = evt.getY();

          if (evt.getClickCount() >= 2) {

             remove(currentSquareIndex);

          }

      }

        });

        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int i = 0; i < numOfRecs; i++) {

             ((Graphics2D) g).draw(rect[i]);

        }
    }

    public int getRec(int x, int y) {

        for (int i = 0; i < numOfRecs; i++) {

            if (rect[i].contains(x, y)) {

                return i;

            }

        }

        return -1;
    }

    public void add(int x, int y) {

        if (numOfRecs < MAX) {
            rect[numOfRecs] = new Rectangle(x, y, recW, recW);       
            currentSquareIndex = numOfRecs;
            numOfRecs++;
            repaint();
        }
    }

    @Override
    public void remove(int n) {
        if(addRecStatus)
        {
            if (n < 0 || n >= numOfRecs) {

             return;

            }
            numOfRecs--;
            rect[n] = rect[numOfRecs];

            if (currentSquareIndex == n) {

                  currentSquareIndex = -1;

            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
    int x = event.getX();
    int y = event.getY();
        if (getRec(x, y) >= 0) {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if(addRecStatus){
            int x = event.getX();
            int y = event.getY();
            if (currentSquareIndex >= 0) {
                Graphics graphics = getGraphics();
                graphics.setXORMode(getBackground());
                ((Graphics2D) graphics).draw(rect[currentSquareIndex]);
                rect[currentSquareIndex].x = x;
                rect[currentSquareIndex].y = y;
                ((Graphics2D) graphics).draw(rect[currentSquareIndex]);
                graphics.dispose();
            }
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        
        // jFrame.add(ok);
        jFrame.setTitle("");
        jFrame.setSize(1000, 700);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
         });
         Container cPane = jFrame.getContentPane();
        // cPane.add(ok);
         cPane.add(new Main());
         
         jFrame.setVisible(true);
         }
}
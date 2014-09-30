package gui;

import graph.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

public class MainGUI extends JPanel implements MouseMotionListener {

    private static final int recW = 70;
    private static final int MAX = 100;
    private Rectangle[] rect = new Rectangle[MAX];
    private int numOfRecs = 0;
    private int currentSquareIndex = -1;
    private boolean addRecStatus = true;
    private Graph graph = new Graph();
    
    private boolean colorStatus = false;
    private int[] listColor = {};

    public MainGUI() {
        JButton ok = new JButton("ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecStatus = false;
                for (int i = 0; i < numOfRecs; i++) {
                    for (int j = i + 1; j < numOfRecs; j++) {

                        if (rect[i].intersects(rect[j])) {
                            graph.addRect(i+1, j+1);
                            System.out.println((i+1) + " dengan " + (j+1));
                        }
                    }
                }
                CNFMaker cnf = new CNFMaker(graph);
                ArrayList<int[]> tmp = cnf.makeCNF();
                for (int i = 0; i < tmp.size(); i++) {
                    int[] hasil = tmp.get(i);
                    System.out.print("{ ");
                    for (int j = 0; j < hasil.length; j++) {
                        System.out.print(hasil[j] + " ");
                    }
                    System.out.println(" }");
                }
                Minisat m = new Minisat(cnf);
                try {
                    listColor = m.solve();
                    colorStatus = true;
                    repaint();
                } catch (ContradictionException ex) {
                    System.out.println("Contradiction exception");
                } catch (TimeoutException ex) {
                    System.out.println("Timeout exception");
                }
            }
        });
        this.add(ok);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                if (addRecStatus) {
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
        
        Color c = Color.black;
        
        for (int i = 0; i < numOfRecs; i++) {
            Graphics2D g2 = ((Graphics2D) g);
            g2.setColor(c);
            g2.draw(rect[i]);
            
            // decode color
            if (colorStatus){
                EncodedColor e = new EncodedColor(listColor[i*2], listColor[i*2+1]);
                c = e.getColor();
                g2.setColor(c);
                g2.fill(rect[i]);
            }
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
        if (addRecStatus) {
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
        if (addRecStatus) {
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
}

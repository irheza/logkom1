package gui;

import graph.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGUI extends JPanel implements MouseMotionListener {

    private static final int recW = 70;
    private static final int MAX = 100;
    private Rectangle[] rect = new Rectangle[MAX];
    private int numOfRecs = 0;
    private int currentSquareIndex = -1;
    private boolean addRecStatus = true;
    private Graph graph = new Graph();

    private boolean isColored = false;
    private boolean isRectangle = false;
    private int[] listColor = {};
    private Minisat m = null;

    public MainGUI() {
        JPanel topButtonPanel = new JPanel();
        JPanel bottomButtonPanel = new JPanel();

        this.setLayout(new BorderLayout());
        topButtonPanel.setLayout(new GridLayout(1, 6));
        bottomButtonPanel.setLayout(new GridLayout(1, 5));

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecStatus = false;
                              
                for (int i = 0; i < numOfRecs; i++) {
                    for (int j = i + 1; j < numOfRecs; j++) {
                        if (rect[i].intersects(rect[j])) {
                            graph.addRect(i + 1, j + 1);
                            System.out.println((i + 1) + " dengan " + (j + 1));
                        }
                    }
                }
                
                CNFMaker cnf = new CNFMaker(graph, graph.getJumlahVariabel());
                System.out.println("jumlah variabel :" + graph.getJumlahVariabel());
//                ArrayList<int[]> tmp = cnf.makeCNF();
//                for (int i = 0; i < tmp.size(); i++) {
//                    int[] hasil = tmp.get(i);
//                    System.out.print("{ ");
//                    for (int j = 0; j < hasil.length; j++) {
//                        System.out.print(hasil[j] + " ");
//                    }
//                    System.out.println(" }");
//                }
                m = new Minisat(cnf);
                try {
                    // get solution
                    listColor = m.solve();
                    for(int i=0; i<listColor.length;i++)
                    {
                        System.out.println(listColor[i]);
                    }
                    isColored = true;
                    repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Unsolvable", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton restart = new JButton("Restart");
        JButton show = new JButton("Show other solution");

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        // coba ganti warna
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (m != null && isColored) {
                    try {
                        // masukkan daftar warna yang boleh
                        int[] newClause = m.negated(listColor);
                        m.addClause(newClause);
                        listColor = m.solve();
                        repaint();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Unsolvable", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        topButtonPanel.add(new JPanel());
        topButtonPanel.add(new JPanel());
        topButtonPanel.add(ok);
        topButtonPanel.add(restart);
        topButtonPanel.add(new JPanel());
        topButtonPanel.add(new JPanel());

        bottomButtonPanel.add(new JPanel());
        bottomButtonPanel.add(new JPanel());
        bottomButtonPanel.add(show);
        bottomButtonPanel.add(new JPanel());
        bottomButtonPanel.add(new JPanel());

        JPanel westBP = new JPanel();
        westBP.setLayout(new GridLayout(8, 1));
        JButton recButton = new JButton("Rectangle");
        JButton squareButton = new JButton("Square");

        recButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRectangle = true;
            }
        });
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRectangle = false;
            }
        });

        westBP.add(recButton);
        westBP.add(squareButton);

        this.add(topButtonPanel, BorderLayout.NORTH);
        this.add(bottomButtonPanel, BorderLayout.SOUTH);
        this.add(westBP, BorderLayout.WEST);
        this.add(new JPanel(), BorderLayout.EAST);
        this.setBackground(Color.white);

        this.addMouseListener(new MouseAdapter() {
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

    /**
     * Mengembalikan MainGUI seperti semula (kosong)
     */
    public void reset() {
        rect = new Rectangle[MAX];
        numOfRecs = 0;
        currentSquareIndex = -1;
        addRecStatus = true;
        graph = new Graph();

        isColored = false;
        listColor = new int[1];
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0, j = 0; i < numOfRecs; i++) {
            Graphics2D g2 = ((Graphics2D) g);
            Color c = Color.black;
            g2.setColor(c);
            g2.draw(rect[i]);

            // decode color
            if (isColored) {
                // if graph contains i+1 karena index graph one-based
                if (graph.contains(i + 1)) {                    
                    EncodedColor e = new EncodedColor(listColor[j], listColor[j + 1]);
                    System.out.println("rectangle " + (i + 1) + " diwarnain " + e.toString());
                    c = e.getColor();
                    g2.setColor(c);
                    g2.fill(rect[i]);
                    j += 2;
                } else {
                    c = Color.red;
                    g2.setColor(c);
                    g2.fill(rect[i]);
                }
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
            if (isRectangle) {
                rect[numOfRecs] = new Rectangle(x, y, 100, recW);
            } else {
                rect[numOfRecs] = new Rectangle(x, y, recW, recW);
            }
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


import graph.Minisat;
import gui.MainGUI;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
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
    public static void main(String[] args) throws ContradictionException, TimeoutException {
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
        MainGUI mg = new MainGUI();
        Container cPane = jFrame.getContentPane();
        cPane.add(mg);        

        jFrame.setVisible(true);
    }    
}

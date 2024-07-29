package Main;

import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Windows extends JFrame {

    private JFrame frame;

    public Windows(Scene scene) {
        frame = new JFrame();

        frame.setTitle("Runner");
        //frame.setSize(200, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scene);
        frame.setVisible(true);
        setLocationRelativeTo(null); // centre l'ecran du jeu sur notre ecran
        frame.pack(); // Permet de resize la fenetre du jeu selon le preferedSize de la scene
        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                scene.getGame().pause();
                // Permet de mettre le jeu en pause lorsque la fenetre n'est pas focus (evite
                // que le perso bouge tout seul)
            }

            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
            }
        });

        Menu menu = new Menu();
        JMenuBar menuBar = menu.createMenuBar();

        setJMenuBar(menuBar);
    }
}

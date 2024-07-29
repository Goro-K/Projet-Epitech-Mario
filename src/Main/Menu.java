package Main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Menu extends JFrame {
    public Menu() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setJMenuBar(createMenuBar());
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu newRound = new JMenu("New game");
        menuBar.add(newRound);

        JMenu havePause = new JMenu("Pause");
        menuBar.add(havePause);

        JMenu mute = new JMenu("Mute");
        menuBar.add(mute);

        return menuBar;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Menu window = new Menu();
        window.setVisible(true);
    }
}

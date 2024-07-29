package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import javax.swing.*;

import Inputs.KeyboardInputs;

public class Scene extends JPanel { // panel = contenu du Window

    private Game game;
    private BufferedImage backgroundImage;

    public Scene(Game game) {
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this)); // Ajout du keyListener
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1180, 500);
        setPreferredSize(size);
    }

    public void updateGame() {
    }

    public void paintComponent(Graphics g) { // g de permet de "peindre" des elements dans la fenetre
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        game.render(g);
    }

    public Game getGame() {
        return game;
    }

}

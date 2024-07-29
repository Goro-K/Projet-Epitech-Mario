package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.Scene;

public class KeyboardInputs implements KeyListener {

    private Scene scene;

    public KeyboardInputs(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                scene.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_Q:
                scene.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                scene.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                scene.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_UP:
                scene.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_LEFT:
                scene.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                scene.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_DOWN:
                scene.getGame().getPlayer().setDown(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                scene.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_Q:
                scene.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                scene.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                scene.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_UP:
                scene.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_LEFT:
                scene.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                scene.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_DOWN:
                scene.getGame().getPlayer().setDown(false);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}

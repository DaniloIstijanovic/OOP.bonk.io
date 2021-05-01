package oop.bonk.io;

import oop.bonk.io.panels.MainMenuPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (MainMenuPanel.playButton.isInsideButton(mx, my)) {
            Pokret.notMain();
        } else if (MainMenuPanel.optButton.isInsideButton(mx, my)) {
            System.out.println("kliknuto je options dugme");
        } else if (MainMenuPanel.quitButton.isInsideButton(mx, my)) {
            System.out.println("kliknuto je exit dugme");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
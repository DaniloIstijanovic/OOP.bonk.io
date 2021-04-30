package oop.bonk.io;

import oop.bonk.io.frames.MainMenuFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (MainMenuFrame.playButton.isInsideButton(mx, my)) {
            Pokret.notMain();
        } else if (MainMenuFrame.optButton.isInsideButton(mx, my)) {
            System.out.println("kliknuto je options dugme");
        } else if (MainMenuFrame.quitButton.isInsideButton(mx, my)) {
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
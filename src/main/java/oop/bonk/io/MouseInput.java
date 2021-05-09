package oop.bonk.io;

import oop.bonk.io.panels.MainMenuPanel;
import oop.bonk.io.Pokret;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import oop.bonk.io.panels.Options;
public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (MainMenuPanel.playButton.isInsideButton(mx, my)) {
        	Pokret.NotMain();
            //Pokret pk = new Pokret();
        } else if (MainMenuPanel.optButton.isInsideButton(mx, my)) {
        	Options opt = new Options();
            System.out.println("kliknuto je options dugme");
        } else if (MainMenuPanel.quitButton.isInsideButton(mx, my)) {
             System.exit(0);
           
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
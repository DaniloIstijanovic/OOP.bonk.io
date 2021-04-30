package oop.bonk.io;

import oop.bonk.io.renderers.MainMenuRenderer;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mx >= MainMenuRenderer.PlayButton.x && mx <= MainMenuRenderer.PlayButton.x + MainMenuRenderer.PlayButton.width) {
            if(my >= MainMenuRenderer.PlayButton.y && my <= MainMenuRenderer.PlayButton.y + MainMenuRenderer.PlayButton.height) {
                Pokret.notMain();
            }

        }

        if(mx >= Main.ySize / 2 + 120 && mx <= Main.ySize /2 + 220) {
            if(my >= 250 && my <= 300) {
                //OptButton pressed

            }

        }

        if(mx >= Main.ySize / 2 + 120 && mx <= Main.ySize /2 + 220) {
            if(my >= 350 && my <= 400) {
                //QuitButton pressed

            }

        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
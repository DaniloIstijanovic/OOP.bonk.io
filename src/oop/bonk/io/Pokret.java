package oop.bonk.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.FlowView;


public class Pokret extends JPanel implements ActionListener, KeyListener{

    Timer tm = new Timer(5, this);
    int x = 0;
    int y = 0;
    int x2 = 0;
    int y2 = 0;
    int velX = 0;
    int velY = 0;
    int gravitacija = 1;

    

    public Pokret(){
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 30);
    }

    public void actionPerformed(ActionEvent e){
        if(x < 0){
            velX = 0;
            x = 0;
        }
        if(y < 0){
            velY = 0;
            y = 0;
        }

        if(x > 930){
            velX = 0;
            x = 930;
        }
        if(y > 792){
            velY = 0;
            y = 791;
        }
        
        if(x < 212 && y > 559){
            if( y < 610){
            velY = 0;
            y = 558;
            }
        }
        if(x < 212 && y < 650){
            if( y > 559){
            velY = 0;
            y = 650;
            }
        }

        if(x > 280 && x < 610 && y > 380){
            if( y < 440){
            velY = 0;
            y = 379;
            }
        }
        if(x > 280 && x < 610 && y < 450){
            if( y > 380){
            velY = 0;
            y = 450;
            }
        }

        if(x > 655 && y > 545){
            if( y < 560){
            velY = 0;
            y = 544;
            }
        }
        if(x > 655 && y < 600){
            if( y > 545){
            velY = 0;
            y = 600;
            }
        }

        x = x + velX;
        y = y + velY + gravitacija;
        repaint();
    }

    public void keyPressed(KeyEvent e){
        int c = e.getKeyCode();

        if(c == KeyEvent.VK_LEFT){
            velX = -5;
            velY = 0;
        }
        if(c == KeyEvent.VK_RIGHT){
            velX = 5;
            velY = 0;
        }
        if(c == KeyEvent.VK_UP){
            velX = 0;
            velY = -5;
        }
        if(c == KeyEvent.VK_DOWN){
            velX = 0;
            velY = 5;
        }
    
    }

    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){
        velX = 0;
        velY = 0;
    }


    public static void main(String[] args){
        Pokret p = new Pokret();
        JFrame jf = new JFrame();
        jf.setTitle("Pokret");
        jf.setSize(1000, 1000);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(p);
    }

}
